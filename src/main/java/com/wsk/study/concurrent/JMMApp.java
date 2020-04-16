package com.wsk.study.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 讲师：若泽(PK哥)
 * 交流群：126181630
 */
public class JMMApp {

    public static void main(String[] args) throws Exception {
        Data data = new Data(); //共享数据

//        for (int i = 0; i < 20; i++) {
//            new Thread(()->{
//                for (int j = 0; j < 1000; j++) {
//                    data.increase();
//                }
//            },"JMMApp" + i).start();
//        }
//
//        while(Thread.activeCount() > 2) {
//            Thread.yield();
//        }

//        System.out.println(Thread.currentThread().getName() + ", number is:" + data.number);
//        System.out.println(Thread.currentThread().getName() + ", number is:" + data.atomicInteger);



        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.set();
            System.out.println(Thread.currentThread().getName() + ", Set number :" + data.number);
        },"JMMApp").start();

        while (data.number == 0){
//.....
        }

        System.out.println(Thread.currentThread().getName() + ", Final number :" + data.number);

    }
}

// volatile
class Data {
    int number = 0;
    public  void increase(){
        number++;
    }

    public void set(){
        this.number = 1000;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void increase2(){
       atomicInteger.getAndIncrement();
    }
}
