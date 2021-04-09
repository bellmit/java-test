package com.wsk.study.utils.googleguava;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * @description:
 * @author: wsk
 * @date: 2021/1/8 10:52
 * @version: 1.0
 */
public class PreconditionsTest {
    public static void main(String[] args) {
        String str1 = "1";
        String str2 = "3";
        String str3 = "";
        String str4 = null;
        Preconditions.checkNotNull(str1,"不能为空");
        Preconditions.checkNotNull(str2,"不能为空");
        Preconditions.checkNotNull(Strings.emptyToNull(str3),"不能为空");
        Preconditions.checkNotNull(str4,"不能为空");


    }
}
