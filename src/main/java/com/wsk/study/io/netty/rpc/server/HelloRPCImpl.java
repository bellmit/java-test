package com.wsk.study.io.netty.rpc.server;

/**
 * @Description:
 * @Auther: wsk
 * @Date: 2020/7/27 19:39
 * @Version: 1.0
 */
public class HelloRPCImpl implements HelloRPC {
    @Override
    public String hello(String name) {
        return "Hello，"+name;
    }
}
