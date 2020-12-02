package com.wsk.study.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: wsk
 * @date: 2020/11/17 19:07
 * @version: 1.0
 */
public class AutomicTest {
    public static void main(String[] args) {
        AtomicReference<Integer> errorResult = new AtomicReference<>();
        System.out.println("输出1："+errorResult.get());

        errorResult.set(1);
        System.out.println("输出2："+errorResult.get());


    }
}
