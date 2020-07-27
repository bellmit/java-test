package com.wsk.study.io.netty.rpc.client;

import com.wsk.study.io.netty.rpc.server.ClassInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Create By zdw on 2019/7/24
 */
public class NettyRPCProxy {
    //根据接口创建代理对象
    public static Object create(Class target){
        return Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //封装ClassInfo
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassName(target.getName());
                classInfo.setMethodName(method.getName());
                classInfo.setObjects(args);
                classInfo.setTypes(method.getParameterTypes());

                //开始用Netty 发送数据
                EventLoopGroup group = new NioEventLoopGroup();
                ResultHandler resultHandler = new ResultHandler();
                try {
                    Bootstrap bootstrap = new Bootstrap();
                    bootstrap.group(group)
                            .channel(NioSocketChannel.class)
                            .handler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel socketChannel) throws Exception {
                                    ChannelPipeline pipeline = socketChannel.pipeline();
                                    pipeline.addLast("encoder",new ObjectEncoder());
                                    pipeline.addLast("decoder",new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                                    pipeline.addLast("handler",resultHandler);
                                }
                            });
                    ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();
                    //写数据到服务器
                    channelFuture.channel().writeAndFlush(classInfo).sync();
                    //关闭连接
                    channelFuture.channel().closeFuture().sync();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    group.shutdownGracefully();
                }
                return resultHandler.getResponse();//返回结果
            }
        });
    }
}
