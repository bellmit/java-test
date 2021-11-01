package com.wsk.study.collection;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 将集合中元素转为数组
 * @Auther: wsk
 * @Date: 2020/5/9 10:56
 * @Version: 1.0
 */
public class ListToArrary {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("wsk");
        arrayList.add("test");
        arrayList.add("123");
        System.out.println(""+9/2);
        String[] array1 = arrayList. toArray(new String[arrayList.size()]);
        String[] array2 = arrayList.toArray(new String[0]);

        System.out.println(array1.length);
        System.out.println(array2.length);
        compute(1);

        System.out.println(DigestUtils.md5Hex("ads_points_account_group").substring(8, 24));
    }

    private static void compute(int i) {
        try {
            System.out.printf(""+10/i);
        }catch (Exception e){
            throw  e;
        }

    }
}
