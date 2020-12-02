package com.wsk.study.commonsclass;

import java.math.BigDecimal;

/**
 * @description:
 * @author: wsk
 * @date: 2020/11/24 19:28
 * @version: 1.0
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        String s1 = "2.1";
        String s2 = "2";
        String s3 = "2.2323343";

        String s4 = "2.1";

        BigDecimal b1=new BigDecimal(s1);
        BigDecimal b2=new BigDecimal(s2);
        BigDecimal b3=new BigDecimal(s3);

        BigDecimal b4=new BigDecimal(s4);

        System.out.println(""+b1.compareTo(b4));
        System.out.println(""+b2.compareTo(b4));
        System.out.println(""+b3.compareTo(b4));


    }
}
