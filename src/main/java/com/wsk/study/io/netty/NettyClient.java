package com.wsk.study.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Create By zdw on 2019/7/23
 * 客户端程序
 */
public class NettyClient {
    public static void main(String[] args) throws Exception {
        //1、创建一个 EventLoopGroup 线程组
        EventLoopGroup group = new NioEventLoopGroup();
        //2、创建客户端启动助手
        Bootstrap bootstrap = new Bootstrap();
        //3、设置参数
        bootstrap.group(group)//3、设置EventLoopGroup线程组
                .channel(NioSocketChannel.class)//4、设置NioSocketChannel作为客户端通道实现
                .handler(new ChannelInitializer<SocketChannel>() {//5、创建一个通道初始化对象
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //6.往Pipeline 链中添加自定义的业务处理handler
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                        System.out.println("-----------Client is Ready------------");
                    }
                });
        //7、启动客户端，等待连接上服务器端（非阻塞）
        ChannelFuture future = bootstrap.connect("127.0.0.1", 9999).sync();
        //8、等待连接关闭（非阻塞）
        future.channel().closeFuture().sync();
    }
}
