package com.wsk.study.io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description: nio 操作文件的FileChannel并不支持非阻塞操作，故nio最多用于网络操作
 * @Auther: wsk
 * @Date: 2020/7/27 16:27
 * @Version: 1.0
 */
public class FileChannelTest {
    public static void main(String[] args) throws Exception {
//        writeToLocalFile();
//        readFromLocalFile();
        copyFile();
    }

    //把数据写到本地文件中
    public static void writeToLocalFile() {
        String msg = "嗨，你好啊，今天是周五了";
        try {
            //创建本地目录
            String fileFloader = "/Users/skwang/Downloads/iotest/";
            File floader = new File(fileFloader);
            if (!floader.exists()) {
                floader.mkdirs();
            }
            //创建本地文件
            String filePath = fileFloader + "hello.txt";
            File file = new File(filePath);
            //判断本地文件是否存在
            if (!file.exists()) {
                file.createNewFile();//不存在就创建
            }
            //构建本地文件的文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            //得到该文件输出流的通道
            FileChannel fileChannel = fileOutputStream.getChannel();
            //构建缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //把消息存储到缓冲区中
            buffer.put(msg.getBytes());
            //翻转缓冲区，重置位置到初始位置
            buffer.flip();
            //把缓冲区的数据写到文件通道中
            fileChannel.write(buffer);
            //释放资源
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //把本地文件中的数据读出来
    public static void readFromLocalFile(){
        try {
            //本地文件路径
            String localFilePath = "/Users/skwang/Downloads/iotest/hello.txt";
            //得到文件的输入流
            FileInputStream fileInputStream = new FileInputStream(localFilePath);
            //得到本地文件通道
            FileChannel fileChannel = fileInputStream.getChannel();
            //构建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //把输入流中数据读到缓冲区中
            fileChannel.read(byteBuffer);
            //直接输出缓冲区中的数据
            System.out.println(new String(byteBuffer.array()));
            //释放资源
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //复制文件
    public static void copyFile() throws Exception {
        //输出到本地文件路径
        String localFilePath_out = "/Users/skwang/Downloads/iotest/hello_copy.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(localFilePath_out);

        //要复制的文件路径
        String localFilePath_in = "/Users/skwang/Downloads/iotest/hello.txt";
        FileInputStream fileInputStream = new FileInputStream(localFilePath_in);
        //从两个流中取出对应的通道
        FileChannel outputStreamChannel = fileOutputStream.getChannel();//输出文件的通道
        FileChannel inputStreamChannel = fileInputStream.getChannel();//输入文件的通道
        //通过api直接把输入流中的 数据  复制到输出流的文件中
        //outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());//从哪个通道里面复制对应的数据到目标文件中
        inputStreamChannel.transferTo(0,inputStreamChannel.size(),outputStreamChannel);//把该通道中的数据复制到对应的目录通道中
        //关闭连接
        outputStreamChannel.close();
        inputStreamChannel.close();
    }
}
