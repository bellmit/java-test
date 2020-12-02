package com.wsk.study.string;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: skwang2@iflytek.com
 * @CreateDate: 2019/1/21 11:37
 * @Version: 1.0
 **/
public class StringUtilTest {


    public final static Logger logger = LoggerFactory.getLogger(StringUtilTest.class);

    public static void main(String[] args) {
//        logger.info("--------------------isBlank test----------------------");
//        String s1 = "";
//        String s2 = null;
//        String s3 = "str";
//
//        logger.info("测试1：{}"+StringUtils.isBlank(s1));
//        logger.info("测试2：{}"+StringUtils.isBlank(s2));
//        logger.info("测试3：{}"+StringUtils.isBlank(s3));
//        List<String> strings1 = new ArrayList<>();
//        int i= 0;
//        while (true){
//            strings1.add("123");
//            i++;
//            if (i%100000000==0) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        logger.info("--------------------CollectionUtils test--------------------------");
        //List<String> strings = new ArrayList<>();
        //strings.add("s1");
        //strings.add("s2");
        //strings.add("s3");
        //strings.add("s4");
        //strings.add("s5");
        //List<String> strings2 = new ArrayList<>();
        //logger.info("测试4：{}"+ CollectionUtils.isEmpty(strings));
        //logger.info("测试5：{}"+ CollectionUtils.isEmpty(strings2));
        //logger.info("测试6：{}"+ CollectionUtils.isEmpty(null));
//
//
//        logger.info("-----------------replace test---------------------");
//        String str6 = "flink_name_hello";
//        System.out.println( StringUtils.replace(str6, "_", "/_"));

//        logger.info("--------------------StringUtils.isNumeric test--------------------------");
//
//        String s1 = "";
//        String s2 = " ";
//        String s3 = "null";
//        String s4 = "   ";
//        String s5 = "-1";
//        String s6 = "-1.1";
//        String s7 = "0";
//        String s8 = "1";
//        String s9 = "1.1";
//
//
//       System.out.println( StringUtils.isNumeric(s1));
//       System.out.println( StringUtils.isNumeric(s2));
//       System.out.println( StringUtils.isNumeric(s3));
//       System.out.println( StringUtils.isNumeric(s4));
//       System.out.println( StringUtils.isNumeric(s5));
//       System.out.println( StringUtils.isNumeric(s6));
//       System.out.println( StringUtils.isNumeric(s7));
//       System.out.println( StringUtils.isNumeric(s8));
//       System.out.println( StringUtils.isNumeric(s9));

//        logger.info("--------------------StringUtils.isNumeric test--------------------------");
//        String s1 = null;
//        System.out.println("%" + s1);
        logger.info("--------------------StringUtils.Substring test--------------------------");
        String s1 = "decimal(10,2)";
        System.out.println(StringUtils.substring(s1,0,s1.indexOf("(")));
        int i = 0;
        int h = 10/i;
    }
}
