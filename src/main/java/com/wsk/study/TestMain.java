package com.wsk.study;

import com.wsk.study.threadPoll.ThreadPollExecutorTest;
import com.wsk.study.threads.thread.MyThread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: skwang2@iflytek.com
 * @CreateDate: 2018/12/7 11:27
 * @Version: 1.0
 **/
public class TestMain {

    public static void main(String[] args) {

        //继承Thread类，重写run方法
        //test();

        //new Thread类，传入Runnable接口的匿名对象
        //MyThread2 myThread2 = new MyThread2();
        //myThread2.test();

        //Executors.newCachedThreadPool 创建有缓存的线程池
        //CachedThreadPoolTest cachedThreadPoolTest = new CachedThreadPoolTest();
        //cachedThreadPoolTest.test();

        //Exectors.newFixedThreadPool 创建有线程上限的线程池
        //FixedThreadPoolTest fixedThreadPoolTest = new FixedThreadPoolTest();
        //fixedThreadPoolTest.test();

        //Executors,newScheduledThreadPool 创建能周期调度任务的线程池
        //ScheduledThreadPoolTest scheduledThreadPoolTest = new ScheduledThreadPoolTest();
        //scheduledThreadPoolTest.test();
        //scheduledThreadPoolTest.test2();

        // Executors.newSingleThreadExecutor 创建一个单线程的线程池，
        //SingleThreadExecutorTest singleThreadExecutorTest = new SingleThreadExecutorTest();
        //singleThreadExecutorTest.test();

        //ThreadPollExecutor
        ThreadPollExecutorTest threadPollExecutorTest = new ThreadPollExecutorTest();
        threadPollExecutorTest.test();

    }

    private static void test() {

        //自定义线程类测试
        List<MyThread> myThreads = new ArrayList<MyThread>();
        for(int i=0;i<5;i++){
            MyThread myThread = new MyThread(i);
            myThread.start();
            myThreads.add(myThread);
        }

        for(MyThread thread : myThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(String.format("结束测试，测试时间%s",System.currentTimeMillis()));
    }
}
