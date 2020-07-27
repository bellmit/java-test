package com.wsk.study.io.bio;

import java.io.*;
import java.net.*;

/**
 * @Description:
 * @Auther: wsk
 * @Date: 2020/7/27 16:14
 * @Version: 1.0
 */
public class BIOServer {

    public static void main(String[] args) throws Exception {
        //创建一个ServerSocket来监听9999端口
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            //监听客户端，得到连接请求
            Socket socket = serverSocket.accept();//阻塞
            //从连接中取出输入流来获取客户端消息
            InputStream inputStream = socket.getInputStream();//阻塞
            //构建缓冲区
            byte[] bytes = new byte[1024];
            //把输入流中的数据读到缓冲区中
            inputStream.read(bytes);
            //获取客户端的IP地址
            String clientIp = socket.getInetAddress().getHostAddress();
                System.out.println(clientIp + "：客户端发送了消息：" + new String(bytes));

            //从连接中取出输出流，并给客户端回应消息
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("我也是啊".getBytes());

            //关闭连接
            socket.close();
        }
    }
}
