package com.wsk.study.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description: 测试线程安全的 时间类型转换
 * @Auther: wsk
 * @Date: 2020/5/10 23:10
 * @Version: 1.0
 */
public class DateFormatUtilsTest {
    public static void main(String[] args) throws Exception {
        //(DateFormatUtils.format(date,string) 和simpledateformat类中的format（）方法
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        System.out.println(DateFormatUtils.format(new Date(), "yyyyMMdd"));

        //作用类似于：simpledateformat类中的parse（）方法
        String[] pattern = new String[]{"yyyy-MM", "yyyyMM", "yyyy/MM",
                "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd",
                "yyyyMMddHHmmss",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy/MM/dd HH:mm:ss"};
        System.out.println(DateUtils.parseDate("2011-11-11", pattern));



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-2 );
        System.out.println(sdf.format(calendar.getTime()));
    }
}
