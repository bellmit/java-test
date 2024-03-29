package com.wsk.study.ruleprocess.janino;

import java.io.StringReader;
import org.codehaus.janino.ClassBodyEvaluator;
import org.codehaus.janino.Scanner;

/**
 * 脚本中实现一个接口以供直接调用,动态生成代码，有代理的感觉了
 */
public class JaninoTester06 {

    public interface Foo {
        int bar(int a, int b);
    }

    public static void main(String[] args) throws Exception {
        Foo f = (Foo) ClassBodyEvaluator.createFastClassBodyEvaluator(
                new Scanner(null, new StringReader("public int bar(int a, int b) { return a + b; }")),
                Foo.class,                  // 实现的父类或接口
                (ClassLoader) null          // 这里设置为null表示使用当前线程的class loader
        );
        System.out.println("1 + 2 = " + f.bar(1, 2));

    }

}
