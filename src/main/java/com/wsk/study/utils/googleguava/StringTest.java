package com.wsk.study.utils.googleguava;

import com.google.common.base.Strings;

/**
 * @description: guava的string工具类
 * @author: wsk
 * @date: 2021/1/8 10:45
 * @version: 1.0
 */
public class StringTest {
    public static void main(String args[]){

        //1、补右全（Strings.padEnd方法）
        String a="12345";
        String b= Strings.padEnd(a, 10, 'x');
        System.out.println(b);
        //输出：12345xxxxx

        //2、补左全（Strings.padStart）
        String c=Strings.padStart(a, 10, 'x');
        System.out.println(c);
        //输出：xxxxx12345

        //3、校验空值和null
        String d="";
        String f=null;
        boolean e=Strings.isNullOrEmpty(d);
        boolean h=Strings.isNullOrEmpty(f);
        System.out.println(e);
        System.out.println(h);
        //输出：true,true

        //4、如果为null 转为""
        String m=null;
        String n=Strings.nullToEmpty(m);
        System.out.println(n);
        //输出：

        //5、如果为"" 转为null
        String j="";
        String k=Strings.emptyToNull(j);
        System.out.println(k);
        //输出：null

        //6、重复字符串（Strings.repeat）
        String o="123";
        String p=Strings.repeat(o, 3);
        System.out.println(p);
        //输出：123123123

        //7、获取a,b左公共部分字符串（左边第一个公共部分）
        String r="abcdsfsfs";
        String s="accdc3sfsd";
        String t=Strings.commonPrefix(r, s);
        System.out.println(t);
        //输出：a

        //8、获取a,b右公共部分字符串
        String w="faaxyz";
        String x="fwefxyz";
        String z=Strings.commonSuffix(w, x);
        System.out.println(z);
        //输出：xyz
    }
}
