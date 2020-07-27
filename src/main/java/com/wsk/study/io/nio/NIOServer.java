package com.wsk.study.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: nio 操作文件的FileChannel并不支持非阻塞操作，故nio最多用于网络操作
 * @Auther: wsk
 * @Date: 2020/7/27 16:27
 * @Version: 1.0
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        //1\创建用来在服务器端监听新的客户端Socket 连接
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2\创建一个监听事件的选择器
        Selector selector = Selector.open();

        //3\绑定一个端口号
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //4\设置为非阻塞方式
        serverSocketChannel.configureBlocking(false);
        //5\把ServerSocketChannel对象注册给Selector，并设置监听事件为 连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6\业务操作
        while (true) {
            //6.1\监控所有注册的通道，当其中有IO 操作可以进行时，将对应的SelectionKey 加入到内部集合中并返回，参数用来设置超时时间
            int select = selector.select(2000);//该方法的返回值是监控到的客户端的个数
            if (select == 0) {//说明没有客户端连接，这个时候nio的优势体现出来了，我们可以干点别的事
                System.out.println("Server：没有客户端来连接我，我没事干，就可以在这里做点想做的事情了");
                continue;//继续下一次循环
            }
            //6.2\得到所有的SelectionKey，得到通道里面的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                //遍历每一个SelectionKey。判断通道里面的事件
                SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()) {//判断是不是连接事件
                    System.out.println("OP_ACCEPT");
                    //接受客户端连接，得到通道
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置客户端通道为非阻塞方式
                    socketChannel.configureBlocking(false);
                    //把客户端通道注册到选择器，并设置监听事件为 读数据 事件，设置共享的数据（只是一个空的ByteBuffer）
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) {//判断是不是读取客户端数据事件
                    System.out.println("OP_READ");
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();//得到通道
                    //得到客户端的共享数据
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    //把共享数据读取到通道中
                    socketChannel.read(byteBuffer);
                    System.out.println("客户端发来数据：" + new String(byteBuffer.array()));
                }
            }
            //6.3\手动从当前的集合移除key，避免重复处理
            keyIterator.remove();
        }
    }
}
