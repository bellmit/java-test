package com.wsk.study.utils.googleguava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description: guava的不可变集合测试
 * @author: wsk
 * @date: 2021/1/8 11:28
 * @version: 1.0
 */
public class ImmutableTest {
    public static void main(String[] args) {
//        unmodifiableTest();
        guavaImmutableTest();
    }
    public static void unmodifiableTest(){
        /**
         * Collections.unmodifiableXXX系列方法来实现不可变集合,但不是真正的不可变集合，当原始集合修改后，不可变集合也发生变化
         */
        List<String> list=new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);
        List<String> unmodifiableList= Collections.unmodifiableList(list);
        System.out.println(unmodifiableList);
        List<String> unmodifiableList1=Collections.unmodifiableList(Arrays.asList("a","b","c"));
        System.out.println(unmodifiableList1);
        String temp=unmodifiableList.get(1);
        System.out.println("unmodifiableList [0]："+temp);
        list.add("baby");
        System.out.println("list add a item after list:"+list);
        System.out.println("list add a item after unmodifiableList:"+unmodifiableList);
        unmodifiableList1.add("bb");
        System.out.println("unmodifiableList add a item after list:"+unmodifiableList1);
        unmodifiableList.add("cc");
        System.out.println("unmodifiableList add a item after list:"+unmodifiableList);
    }

    public static void guavaImmutableTest(){

        List<String> list=new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println("list："+list);

        ImmutableList<String> imlist= ImmutableList.copyOf(list);
        System.out.println("imlist："+imlist);

        ImmutableList<String> imOflist=ImmutableList.of("peida","jerry","harry");
        System.out.println("imOflist："+imOflist);

        ImmutableSortedSet<String> imSortList= ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
        System.out.println("imSortList："+imSortList);

        list.add("baby");
        System.out.println("list add a item after list:"+list);
        System.out.println("list add a item after imlist:"+imlist);

        ImmutableSet<Color> imColorSet =
                ImmutableSet.<Color>builder()
                        .add(new Color(0, 255, 255))
                        .add(new Color(0, 191, 255))
                        .build();

        System.out.println("imColorSet:"+imColorSet);
    }

}
