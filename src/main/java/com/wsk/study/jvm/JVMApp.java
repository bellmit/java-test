package com.wsk.study.jvm;

/**
 */
public class JVMApp {

    public static void main(String[] args) throws InterruptedException {


        // 内存/64
        long totalMemory = Runtime.getRuntime().totalMemory();

        // 内存/4
        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println("totalMemory:" + (totalMemory/(double)1024/1024));
        System.out.println("maxMemory:" + maxMemory/(double)1024/1024);

        Thread.sleep(4000000);
        //byte[] b = new byte[50 * 1024 * 1024];
    }
}
