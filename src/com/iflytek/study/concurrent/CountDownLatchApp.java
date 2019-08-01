package com.iflytek.study.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 */
public class CountDownLatchApp {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 步骤完成");
                latch.countDown();
                }, "CountDownLatchApp"+i).start();
        }

        latch.await();
        System.out.println(Thread.currentThread().getName() + " 火箭发射");
    }
}
