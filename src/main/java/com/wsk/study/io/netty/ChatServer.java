package com.wsk.study.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Create By zdw on 2019/7/23
 * 网络聊天的服务端
 */
public class ChatServer {
    private int port;//服务器监听的端口号
    public ChatServer(int port){
        this.port=port;
    }

    //任务方法
    public void run() {
        //创建两个线程组，用来执行客户端的连接和IO读写操作
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();//连接操作
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();//读写 IO操作
        try {
            //创建服务器端启动助手，用来设置启动参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)//设置两个线程组
                    .channel(NioServerSocketChannel.class)//设置服务器端的通道实现为NioServerSocketChannel
                    .childHandler(new ChannelInitializer<SocketChannel>() {//设置通道的初始化实现对象
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();//得到pipeline链
                            pipeline.addLast("decoder",new StringDecoder());//往pipeline中添加一个字符串解码器
                            pipeline.addLast("encoder",new StringEncoder());//往pipeline中添加一个字符串编码器
                            pipeline.addLast("handler",new ChatServerHandler());//往pipeline链中添加一个自定义的业务处理器
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG,128)//设置线程队列中等待的个数
                    .childOption(ChannelOption.SO_KEEPALIVE,true);//保持活动连接状态

            System.out.println("Netty Chat Server is Starting -------");
            //启动服务器端并绑定端口，等待接受客户端连接（非阻塞）
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            //关闭通道，
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭线程池
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("Netty Chat Server is closed...............................");
        }
    }

    public static void main(String[] args) {
        new ChatServer(9999).run();
    }
}
