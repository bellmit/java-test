package com.wsk.study.commonsclass;

import org.apache.commons.lang3.StringUtils;

/**
 * @description:
 * @author: wsk
 * @date: 2020/12/3 18:43
 * @version: 1.0
 */
public class SplitTest {

    private static final String SEPARATOR_POINT = ".";

    public static void main(String[] args) {
        String tableName = "datark.test";
        if (StringUtils.contains(tableName, SEPARATOR_POINT)) {
            String[] strings = StringUtils.split(tableName,SEPARATOR_POINT);
            System.out.println("1");
        }
    }
}
