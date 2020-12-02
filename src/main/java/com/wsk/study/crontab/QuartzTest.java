package com.wsk.study.crontab;

import org.quartz.CronExpression;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Description: 获取当天crontab任务执行的时间点
 * @Auther: wsk
 * @Date: 2020/10/9 15:19
 * @Version: 1.0
 */
public class QuartzTest {

    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    public static SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ParseException {
        Date nextTime = df.parse(df2.format(new Date()) + " 00:00:00");
        Date to = new Date(nextTime.getTime() + 24 * 3600 * 1000);
        CronExpression expression;
        List<Date> crontimes = new ArrayList<>();
        expression = new CronExpression("0 0/30 * * * ?");
        for (; nextTime.getTime() <= to.getTime(); ) {
            nextTime = expression.getNextValidTimeAfter(nextTime);
            if (nextTime.getTime() >= to.getTime()) {
                break;
            }
            crontimes.add(nextTime);
        }
        for (int i = 0; i < crontimes.size(); i++) {
            System.out.println(df.format(crontimes.get(i)));
        }
    }

}
