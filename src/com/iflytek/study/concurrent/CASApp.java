package com.iflytek.study.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 */
public class CASApp {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10); // Main Memory
        boolean flag = atomicInteger.compareAndSet(10, 2019);
        System.out.println(flag + " .... " + atomicInteger.get());

        flag = atomicInteger.compareAndSet(10, 2020);
        System.out.println(flag + " .... " + atomicInteger.get());

    }
}
