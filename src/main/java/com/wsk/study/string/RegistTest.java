package com.wsk.study.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Auther: wsk
 * @Date: 2019/12/16 20:03
 * @Version: 1.0
 */
public class RegistTest {
    public static void main(String[] args) {

        //正则
        String str = "from  iit_employee_variation;     \n" +
                "select";
        String[] strings = str.split("; *\\t*(\\n|\\r\\n)");
        System.out.println("hello");
        System.out.println("---------------------------------------------------");

        String resultSQL = "from  iit_employee_variation;${ }";
        Pattern p=Pattern.compile("\\$\\{.+\\}");
        Matcher matcher = p.matcher(resultSQL);
        System.out.println(matcher.find());
        System.out.println("---------------------------------------------------");

        String resultSQL2 = "from  iit_employee_variation;\\#{key}";
        resultSQL2 = resultSQL2.replaceAll("\\#\\{key\\}", "test");
        System.out.println(resultSQL2);
        System.out.println("---------------------------------------------------");


        String resultSQL31 = "from  iit_employee_variation 你ha ere dad  ";
        String resultSQL32 = "from  i2it_employee_variation 你ha ere dad  ";
        String resultSQL33 = "from  iit_employee_variation 你ha er1e dad  ";
        String resultSQL34 = "from  i2it_employee_variation 你ha e1re dad  ";
        boolean flag1 = resultSQL31.matches("[\\s\\S]*(from  iit|你ha ere )[\\s\\S]*");
        boolean flag2 = resultSQL33.matches("[\\s\\S]*(from  iit|你ha ere )[\\s\\S]*");
        boolean flag3 = resultSQL32.matches("[\\s\\S]*(from  iit|你ha ere )[\\s\\S]*");
        boolean flag4 = resultSQL34.matches("[\\s\\S]*(from  iit|你ha ere )[\\s\\S]*");
        System.out.println(flag1);
        System.out.println(flag2);
        System.out.println(flag3);
        System.out.println(flag4);
        System.out.println("---------------------------------------------------");


        String test1 = "from  iit_employee_variation 你ha ere dad  ";
        String test2 = "from";
        String test3 = "from  ";
        boolean testflag1 = test1.matches("\\s*");
        boolean testflag2 = test2.matches("\\s*");
        boolean testflag3 = test3.matches("\\s*");
        System.out.println(testflag1);
        System.out.println(testflag2);
        System.out.println(testflag3);
        System.out.println("---------------------------------------------------");

    }
}
