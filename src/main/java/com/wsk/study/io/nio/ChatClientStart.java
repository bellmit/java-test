package com.wsk.study.io.nio;

import java.util.Scanner;

/**
 * Create By zdw on 2019/7/22
 * 聊天程序客户端启动类，启用多线程进行接收服务端消息
 */
public class ChatClientStart {
    public static void main(String[] args) throws Exception {
        ChatClient chatClient = new ChatClient();
        //开启线程，接收客户端消息
        new Thread(){
            @Override
            public void run() {
                while(true){
                    try{
                        chatClient.receiveMsgFromServer();
                        Thread.sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        //控制台录入消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String msg=scanner.nextLine();
            chatClient.sendMsgToServer(msg);//发送消息到服务器端
        }
    }
}
