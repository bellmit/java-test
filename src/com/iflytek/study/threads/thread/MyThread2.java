package com.iflytek.study.threads.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description: 创建Thread对象，传入runable接口对象的匿名内部类， 构建多线程
 * @Author: skwang2@iflytek.com
 * @CreateDate: 2018/12/10 9:54
 * @Version: 1.0
 **/
public class MyThread2 {

    public void test() {

        List<Thread> myThreads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Random random = new Random();
                    long time = random.nextInt(9) + 1;
                    try {
                        Thread.sleep((time * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("你好，我是线程 " + Thread.currentThread().getName() + ",序号 " + finalI + ",沉睡了 " +
                            time + "秒");
                }
            });
            thread.start();
            myThreads.add(thread);
        }

        for (Thread thread : myThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(String.format("结束测试，测试时间%s", System.currentTimeMillis()));
    }

}
