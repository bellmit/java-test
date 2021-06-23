package com.wsk.study.exception;

import com.wsk.study.log4j.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description:
 * @Auther: wsk
 * @Date: 2020/4/24 11:46
 * @Version: 1.0
 */
public class ExceptionMessageTest {

    final static  Logger LOG = LoggerFactory.getLogger(ExceptionMessageTest.class);
    public static void main(String[] args) {
        try {
            chu(10,0);
        }catch (Throwable e){
            LOG.info("--------getmessage--------");
            LOG.info(e.getMessage());
            LOG.info("--------to string--------");
            LOG.info(e.toString());
            LOG.info("--------printstce--------");
            e.printStackTrace();
            LOG.info("--------get printstce--------");
            String stackTrace = getStackTrace(e);
            LOG.info(stackTrace);
        }

    }

    private static void chu(int i, int i1) {
        try {
            int result = i/i1;
        }catch (ArithmeticException e){
            LOG.error("计算报错1",e);
        }
        catch (Exception e){
            LOG.error("计算报错2",e);
            throw new RuntimeException("计算错误",e);
        }
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try
        {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally
        {
            pw.close();
        }
    }
}
