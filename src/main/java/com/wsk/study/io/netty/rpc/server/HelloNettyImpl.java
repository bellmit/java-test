package com.wsk.study.io.netty.rpc.server;

/**
 * @Description:
 * @Auther: wsk
 * @Date: 2020/7/27 19:38
 * @Version: 1.0
 */
public class HelloNettyImpl implements HelloNetty {
    @Override
    public String hello() {
        return "Hello Netty";
    }
}
