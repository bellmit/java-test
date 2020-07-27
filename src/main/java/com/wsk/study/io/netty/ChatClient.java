package com.wsk.study.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * Create By zdw on 2019/7/23
 * 网络聊天的客户端代码
 */
public class ChatClient {
    private final String host; //服务器端IP 地址
    private final int port; //服务器端端口号
    public ChatClient(String host,int port){
        this.host = host;
        this.port = port;
    }

    public void run(){
        //创建一个客户端的线程池组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端的启动助手，进行参数设置
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)//设置线程组
                    .channel(NioSocketChannel.class)//设置客户端的通道实现为NioSocketChannel
                    .handler(new ChannelInitializer<SocketChannel>() {//定义通道初始化器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();//得到pipeline链
                            pipeline.addLast("decoder",new StringDecoder());//往pipeline中添加字符串解码器
                            pipeline.addLast("encoder",new StringEncoder());//往pipeline中添加字符串编码器
                            pipeline.addLast("handler",new ChatClientHandler());//往pipeline中添加自定义的业务处理器
                        }
                    });
            //启动客户端，等待连接服务器端（非阻塞）
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            System.out.println("--------Client："+channel.localAddress().toString().substring(1)+"--连接成功---------");

            //控制台读取客户端手动录入的消息，然后发送给服务器端，服务器端进行消息的广播
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()){
                String msg = scanner.nextLine();//得到客户录入的消息
                channel.writeAndFlush(msg+"\r\n");//向服务器端发送数据
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChatClient("127.0.0.1",9999).run();
    }
}
