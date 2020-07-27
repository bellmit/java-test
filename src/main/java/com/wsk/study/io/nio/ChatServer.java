package com.wsk.study.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Create By zdw on 2019/7/22
 * 网络聊天案例的服务端
 */
public class ChatServer {
    private ServerSocketChannel serverSocketChannel;//监听通道，老大
    private Selector selector;//选择器对象
    private static final int PORT=9999;//监听的端口号

    public ChatServer(){//构造方法，得到一些必需的对象
        try{
            //1、得到监听通道对象
            serverSocketChannel = ServerSocketChannel.open();
            //2、得到选择器对象
            selector = Selector.open();
            //3、设置为非阻塞方式
            serverSocketChannel.configureBlocking(false);
            //4、绑定端口
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            //5、将监听通道注册到选择器，并设置为监听连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            //调用方法，打印日志信息
            printInfo("Chat Server is Ready.........");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //6、业务逻辑处理的方法
    public void start() throws Exception{
        while (true){
            //6.1、不停的监听客户端通道，看是否有连接，如果有连接就进行业务处理，否则就干点别的事情
            int select = selector.select(2000);//2秒钟就监听一次，得到的是连接个数
            if(select==0){
                System.out.println("Server:没有客户端找我， 我就干别的事情");
                continue;
            }
            //6.2、得到所有的SelectionKey，得到通道里面的事件
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                //得到下一个连接关系，进行业务处理
                SelectionKey selectionKey = keyIterator.next();
                if(selectionKey.isAcceptable()){//如果是连接请求事件
                    //接收连接请求，得到通道
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //把通道注册到选择器中,并设置监听事件为 读数据 事件
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    //打印提示信息，参照QQ的好友上线功能，提示的是客户端的IP和端口号
                    System.out.println(socketChannel.getRemoteAddress().toString().substring(1)+"：上线了");
                }
                if(selectionKey.isReadable()){//如果是读数据事件
                    //读取客户端发送来的消息，并把接收到的消息广播到其他客户端，其他客户端都能收到消息
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();//得到客户端通道
                    //定义缓冲区
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    //把客户端消息读到缓冲区中
                    int count = socketChannel.read(byteBuffer);//返回的是读到数据的长度
                    if(count>0){//读到了数据
                        //把数据转成字符串
                        String msg = new String(byteBuffer.array());
                        //控制台打印消息
                        printInfo(msg);

                        //广播消息到其他客户端
                        System.out.println("服务器端广播了消息.........");
                        //得到客户端所有的通道，判断客户端的通道不是发送消息给服务端的通道
                        for(SelectionKey key : selector.keys()){
                            Channel targetChannel = key.channel();//得到客户端的通道
                            //判断该客户端通道类型是不是SocketChannel，并且该客户端不是发送消息的客户端
                            if(targetChannel instanceof SocketChannel && targetChannel!=socketChannel){
                                SocketChannel destChannel = (SocketChannel) targetChannel;
                                //把消息放到缓冲区中
                                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                                //把缓冲区数据写进通道中
                                destChannel.write(buffer);
                            }
                        }
                    }
                }
                //6.3处理完本次的连接关系之后，一定要把key从集合中移除，避免重复处理
                keyIterator.remove();
            }
        }
    }

    private void printInfo(String str) { //往控制台打印消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("[" + sdf.format(new Date()) + "] -> " + str);
    }

    //main方法用来启动服务器端
    public static void main(String[] args) throws Exception{
        new ChatServer().start();
    }
}
