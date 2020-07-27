package com.wsk.study.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Create By zdw on 2019/7/22
 * NIO的网络客户端
 */
public class NIOClient {
    public static void main(String[] args) throws Exception {
        //1、得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //2、设置为非阻塞方式
        socketChannel.configureBlocking(false);
        //3、得到服务端的IP地址和端口号
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",9999);
        //4、连接服务器端
        if(!socketChannel.connect(address)){//表示没有连接上
            while(!socketChannel.finishConnect()){//finishConnect表示再次连接，如果没有连接上，那么就可以在这里处理一些其他的业务逻辑
                System.out.println("Client连接服务端的同时，我还可以处理一些其他的事情");
            }
        }
        //向缓冲区中存入要发送的数据
        String msg = "Hello，NIOServer";
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        //发送数据
        socketChannel.write(byteBuffer);
        //这是为了客户端连接不断开
        System.in.read();
    }
}
