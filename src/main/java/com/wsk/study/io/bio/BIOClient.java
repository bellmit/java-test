package com.wsk.study.io.bio;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @Description:
 * @Auther: wsk
 * @Date: 2020/7/27 16:16
 * @Version: 1.0
 */
public class BIOClient {
    public static void main(String[] args) throws IOException {
        while (true) {
            //创建连接对象Socket
            Socket socket = new Socket("127.0.0.1", 9999);
            //从连接中获取输出流，并且像服务端写消息
            OutputStream outputStream = socket.getOutputStream();
            System.out.println("------------------请输入你的想说的话：");
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();//得到输入的消息
            //往连接中写入消息
            outputStream.write(msg.getBytes());

            //从连接中取出输入流，并接收服务端消息
            InputStream inputStream = socket.getInputStream();//阻塞
            //构建缓冲区
            byte[] bytes = new byte[1024];
            //把服务器的回应消息写入到缓冲中
            inputStream.read(bytes);
            System.out.println("对方的回应是：" + new String(bytes));

            //关闭连接
            socket.close();
        }
    }
}
