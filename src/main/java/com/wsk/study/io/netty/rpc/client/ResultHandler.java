package com.wsk.study.io.netty.rpc.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Create By zdw on 2019/7/24
 * 客户端业务处理类
 */
public class ResultHandler extends ChannelInboundHandlerAdapter {

    private Object response;
    public Object getResponse() {
        return response;
    }
    //读取服务器端返回的数据(远程调用的结果)
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        response = msg;
        ctx.close();
    }
}
