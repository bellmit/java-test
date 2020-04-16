package com.wsk.study.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * 并发工具类
 */
public class SemaphoreApp {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        for (int i = 1; i < 10; i++) {
            final int index = i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ":"+index+":"+ "开始占坑");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + ":"+index+":"+"占坑结束");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }




    }
}
