package com.wsk.study.io.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Create By zdw on 2019/7/23
 * 网络聊天客户端的业务处理类，继承的是SimpleChannelInboundHandler类，String泛型代表的是消息类型
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
    //通道就绪事件
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("Client：【"+channel.localAddress().toString().substring(1)+"】用户上线了");
    }

    //通道未就绪事件
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("Client：【"+channel.localAddress().toString().substring(1)+"】用户掉线了");
    }

    // 通道数据读取事件
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        //读取服务器端的数据消息，打印到控制台
        System.out.println(s.trim());
    }

    //异常发生事件
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
