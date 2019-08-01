package com.iflytek.study.concurrent;

/**
 * DCL + volatile  ==>  双重校验锁+volatile 非常高性能的单例模式
 */
public class SingletonApp {

    // 懒汉式  饿汉式
    private volatile static SingletonApp instance = null;

    private SingletonApp(){
        System.out.println(Thread.currentThread().getName() + "~~~~~~SingletonApp ~~~~~");
    }

    // DCL：double check lock
    public static  SingletonApp getInstance() {
         int a = 0;
        if(instance == null) { // null
            synchronized (SingletonApp.class){
                if(instance == null) {
                    instance = new SingletonApp();
                }
            }
        }
        return instance;
    }

        public static void main(String[] args) {
//        System.out.println(SingletonApp.getInstance() == SingletonApp.getInstance() );
//        System.out.println(SingletonApp.getInstance() == SingletonApp.getInstance() );
//        System.out.println(SingletonApp.getInstance() == SingletonApp.getInstance() );

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                SingletonApp.getInstance();
            },"SingletonApp"+i).start();
        }
    }
}
