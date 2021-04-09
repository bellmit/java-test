package com.wsk.study.utils.springassert;

import org.springframework.util.Assert;

import java.util.Collections;

/**
 * @description: spring 工具类Assert断言，用于校验参数
 * @author: wsk
 * @date: 2021/1/8 11:13
 * @version: 1.0
 */
public class AssertTest {


    public static void main(String[] args) {
        String str1 = "1";
        String str2 = "3";
        String str3 = "";
        String str4 = null;
        Assert.notEmpty(Collections.singleton(str1),"不能为空1");
        Assert.notEmpty(Collections.singleton(str2),"不能为空2");
        Assert.notEmpty(Collections.singleton(str3),"不能为空3");
        Assert.notEmpty(Collections.singleton(str4),"不能为空4");
    }
}
