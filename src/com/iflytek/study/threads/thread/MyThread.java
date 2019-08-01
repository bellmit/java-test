package com.iflytek.study.threads.thread;

import java.util.Random;

/**
 * @Description: Thread 对象构建多线程之继承Thread接口
 * @Author: skwang2@iflytek.com
 * @CreateDate: 2018/12/7 10:54
 * @Version: 1.0
 **/
public class MyThread  extends Thread{

    private int index;

    public MyThread(int index){
        this.index = index;
    }

    @Override
    public void run() {

        Random random = new Random();
        long time = random.nextInt(9) + 1;
        try {
            Thread.sleep((time*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("你好，我是线程 "+Thread.currentThread().getName()+",序号 "+index+",沉睡了 "+time+"秒");
    }
}
