package com.wsk.study.runtime;

/**
 */
public class RunTimeApp {

    public static void main(String[] args) throws InterruptedException {


        // 内存/64
        long totalMemory = Runtime.getRuntime().totalMemory();

        // 内存/4
        long maxMemory = Runtime.getRuntime().maxMemory();

        //可用线程数
        int processors = Runtime.getRuntime().availableProcessors();

        System.out.println("totalMemory:" + (totalMemory/(double)1024/1024));
        System.out.println("maxMemory:" + maxMemory/(double)1024/1024);
        System.out.println("maxMemory:" + processors);

        Thread.sleep(4000000);
        //byte[] b = new byte[50 * 1024 * 1024];
    }
}
