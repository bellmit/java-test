package com.wsk.study.keyword;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Description: java8 双冒号测试。 JDK8中有双冒号的用法，就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下。
 *                类名::方法名 等价于 x -> 方法（x）等价于 增强for(){},
 *                都可以用双冒号代替
 *
 *
 * @Auther: wsk
 * @Date: 2020/8/5 20:09
 * @Version: 1.0
 */
public class DoubleColonTest {
    public static void  printValur(String str){
        System.out.println("print value : "+str);
    }
    public static void main(String[] args) {
        List<String> al = Arrays.asList("a", "b", "c", "d");
        al.sort(Comparator.comparing(String::length));
        //最老的写法
        for(String a :al){
            DoubleColonTest.printValur(a);
        }
        //老的写法
        al.forEach(s -> {DoubleColonTest.printValur(s);});
        //使用双目运算符号
        al.forEach(DoubleColonTest::printValur);
        //下面的方法和上面等价的
        Consumer<String> methodParam = DoubleColonTest::printValur; //方法参数
        al.forEach(x -> methodParam.accept(x));//方法执行accept
    }
}
