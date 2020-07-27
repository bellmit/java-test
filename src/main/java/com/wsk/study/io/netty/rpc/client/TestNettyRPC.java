package com.wsk.study.io.netty.rpc.client;

/**
 * Create By zdw on 2019/7/24
 * Client消费方
 */
public class TestNettyRPC {
    public static void main(String[] args) {
        //第一次远程调用
        HelloNetty helloNetty = (HelloNetty) NettyRPCProxy.create(HelloNetty.class);
        System.out.println(helloNetty.hello());

        //第二次远程调用
        HelloRPC helloRPC = (HelloRPC) NettyRPCProxy.create(HelloRPC.class);
        System.out.println(helloRPC.hello("RPC小周"));
    }
}
