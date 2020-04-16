package com.wsk.study.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 并发工具类
 */
public class CyclicBarrierApp {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(7, ()->{
            System.out.println("召唤神龙...");
        });

        for (int i = 1; i < 10; i++) {
            final int index = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 集齐龙珠"+index);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }




    }
}
