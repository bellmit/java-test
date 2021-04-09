package com.wsk.study.simple;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @description: 流式编程测试，测试是否是1条数据贯穿流的始末
 * @author: wsk
 * @date: 2021/3/31 21:37
 * @version: 1.0
 */
public class StreamTest {

    private static Integer size = 10000;

    public static void main(String[] args) {
        ArrayList<Integer> lists = Lists.newArrayListWithCapacity(size);
        for (int i= 0; i< size; i++) {
            lists.add(i);
        }
        long count = lists.stream().map(c -> {
            System.out.println("1 号算子，序号：" + c);
            return c;
        }).map(c -> {
            System.out.println("2 号算子，序号：" + c);
            return c;
        }).count();
    }
}
