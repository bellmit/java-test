package com.wsk.study.execctors;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Exectors.newFixedThreadPool 创建指定线程数的线程池，超过该线程池的贤臣处于等待状态
 * @Author: skwang2@iflytek.com
 * @CreateDate: 2018/12/10 11:06
 * @Version: 1.0
 **/
public class FixedThreadPoolTest {

    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            long t1 = System.currentTimeMillis();
            executorService.execute(new Runnable() {
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
                            time + "秒，总共耗时：" + (System.currentTimeMillis() - t1));
                }
            });
        }
        System.out.println(String.format("所以测试任务已经启动！"));

        //shutdown() 平滑关闭，表示不再接受新的任务，等当前任务完成后关闭线程池
        executorService.shutdown();
        boolean flag = false;
        try {
            //主线程等待线程池结束所有任务，等待的时间是一定的
            flag = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("结束测试，测试时间%s，线程池是否在规定时间完成任务：%s", System.currentTimeMillis(), flag));
    }

}
