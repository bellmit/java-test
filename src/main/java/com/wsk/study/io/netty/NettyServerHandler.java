package com.wsk.study.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Create By zdw on 2019/7/23
 * 自定义服务器端业务处理类
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    //读取数据事件
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server："+ctx);
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("客户端发来的消息："+byteBuf.toString(CharsetUtil.UTF_8));
    }

    //数据读取完毕事件
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("我就是穷到没钱了啊",CharsetUtil.UTF_8));
        //将数据写到ChannelPipeline 中当前ChannelHandler 的下一个ChannelHandler 开始处理（出站）
    }

    //异常发生事件
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();//关闭上下文
    }
}
