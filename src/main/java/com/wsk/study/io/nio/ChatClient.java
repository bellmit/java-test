package com.wsk.study.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Create By zdw on 2019/7/22
 * 网络聊天客户端程序
 */
public class ChatClient {
    private SocketChannel socketChannel;//客户端网络通道
    private static final String ADDRESS = "127.0.0.1";//服务器端IP
    private static final int PORT=9999;//服务器端端口号
    private String username;//客户端的聊天用户名

    public ChatClient() throws Exception {//构造方法中得到一些必须的对象
        //1、得到客户端通道
        socketChannel = SocketChannel.open();
        //2、设置为非阻塞方法
        socketChannel.configureBlocking(false);
        //3、提供服务器端的IP地址和端口号
        InetSocketAddress address = new InetSocketAddress(ADDRESS,PORT);
        //4、连接服务器端，这里会不停的请求服务器端，是非阻塞的，当连接不上的时候可以做点其他的事情
        if(!socketChannel.connect(address)){
            //如果没有连接上，就需要不断的重新调用finishConnect进行连接
            while(!socketChannel.finishConnect()){
                System.out.println("Client客户端在连接过程中，我可以干点别的事情");
            }
        }
        //5、得到客户端的IP地址和端口号，作为聊天用户名
        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println("----------Client（"+username+") is Ready-----------");
    }

    //向服务器端发送数据
    public void sendMsgToServer(String msg) throws Exception{
        //假设当客户端发送的消息是bye的时候，就关闭当前的客户端连接
        if("bye".equalsIgnoreCase(msg)){
            socketChannel.close();
            return;
        }
        //向服务器端发送消息，拼装消息
        msg = username+"说了："+msg;
        //发送数据
        socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
    }

    //从服务器端接收收取
    public void receiveMsgFromServer() throws Exception {
        //得到缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //把服务器端返回的数据读到缓冲区
        int read = socketChannel.read(byteBuffer);
        if(read>0){
            //打印消息到控制台
            System.out.println(new String(byteBuffer.array()).trim());
        }
    }
}
