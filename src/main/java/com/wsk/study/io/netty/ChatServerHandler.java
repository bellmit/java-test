package com.wsk.study.io.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By zdw on 2019/7/23
 * 网络聊天服务器端的业务处理类，注意继承的是  SimpleChannelInboundHandler，他的泛型代表的是消息的数据类型
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static List<Channel> channels = new ArrayList<>();//存储已经就绪的通道

    //通道就绪，相当于上线了
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();//得到通道
        channels.add(channel);//添加到集合中
        System.out.println("Server：【"+channel.remoteAddress().toString().substring(1)+"】用户在线");
    }

    //通道未就绪，相当于掉线了
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.remove(channel);//从集合中移除
        System.out.println("Server：【"+channel.remoteAddress().toString().substring(1)+"】用户掉线了");
    }

    //读取数据
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel channel = channelHandlerContext.channel();//得到当前通道
        //把读取到的消息广播到其他就绪通道channels
        for(Channel targetChannel : channels){
            if(targetChannel != channel){//排除当前通道
                //把当前通道的消息  s 写给其他通道
                targetChannel.writeAndFlush("Server：用户：【"+channel.remoteAddress().toString().substring(1)+"】说："+s+"\n");
            }
        }
    }

    //发生异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("Server：【"+channel.remoteAddress().toString().substring(1)+"】出了异常："+cause.getMessage());
        ctx.close();
    }
}
