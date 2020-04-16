package com.wsk.study.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Auther: wsk
 * @Date: 2019/10/10 12:55
 * @Version: 1.0
 */
public class Test {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Test.class);
        logger.info("hello");
    }
}
