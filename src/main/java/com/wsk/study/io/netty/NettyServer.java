package com.wsk.study.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Create By zdw on 2019/7/23
 * 服务端程序类
 */
public class NettyServer {
    public static void main(String[] args) throws Exception {
        //1、创建一个线程组用来处理网络事件（接受客户端连接）
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //2、创建一个线程组用来处理网络事件（处理通道 I/O 读写事件）
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        //3、创建服务端启动助手来配置参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)//4、设置两个线程组
                .channel(NioServerSocketChannel.class)//5、使用NioServerSocketChannel作为服务器端的通道实现
                .option(ChannelOption.SO_BACKLOG,128)//6、设置线程队列中等待的个数
                .childOption(ChannelOption.SO_KEEPALIVE,true)//7、保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() {//8、创建一个通道初始化对象
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //9、往Pipeline 链中添加自定义的业务处理handler，NettyServerHandler服务器端自定义业务类
                        socketChannel.pipeline().addLast(new NettyServerHandler());
                        System.out.println("----------Server is Ready----------");
                    }
                });
        //10.启动服务器端并绑定端口，等待接受客户端连接(非阻塞)
        ChannelFuture future = serverBootstrap.bind(9999).sync();
        System.out.println("===========Server is Starting============");

        //11、关闭通道，关闭线程池
        future.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
