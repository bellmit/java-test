package com.wsk.study.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Auther: wsk
 * @Date: 2019/12/16 20:03
 * @Version: 1.0
 */
public class StringTest {
    public static void main(String[] args) {

        //正则
        String str = "from  iit_employee_variation;     \n" +
                "select";
        String[] strings = str.split("; *\\t*(\\n|\\r\\n)");
        System.out.println("hello");
        System.out.println("111111111111111111111111111111111111");



        String resultSQL = "from  iit_employee_variation;${ }";
        Pattern p=Pattern.compile("\\$\\{.+\\}");
        Matcher matcher = p.matcher(resultSQL);
        System.out.println(matcher.find());
        System.out.println("222222222222222222222222222222222222");

        String resultSQL2 = "from  iit_employee_variation;\\#{key}";
        resultSQL2 = resultSQL2.replaceAll("\\#\\{key\\}", "test");
        System.out.println(resultSQL2);
        System.out.println("33333333333333333333333333333333333");

        byte[] bytes = new byte[0];


    }
}
