package com.wsk.study.commonsclass;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wsk
 * @date: 2020/12/1 14:58
 * @version: 1.0
 */
public class BreakTest {

    public static void main(String[] args) {
        List<Integer>  list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(0);
        list.add(3);

        for(Integer o :list){
            try {
                System.out.println("value :"+10/o);
            } catch (Exception e) {
                System.out.println("error :"+e.getMessage());
                break;
            }finally {
                System.out.println("final :"+o);
            }
        }
    }
}
