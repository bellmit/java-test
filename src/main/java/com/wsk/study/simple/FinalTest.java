package com.wsk.study.simple;

import lombok.Data;

/**
 * @description: 测试final修饰的局部变量赋值给另一个变量后，第二变量是否受到 final影响
 * @author: wsk
 * @date: 2020/10/12 18:52
 * @version: 1.0
 */
public class FinalTest {

    public static void main(String[] args) {
        final Person person = new Person("wsk");
        Person person1 = person;
        System.out.println("----1----:"+person);
        person1.setName("zwr");
        System.out.println("----2----:"+person1);
        System.out.println("----3----:"+person);

        System.out.println();
        System.out.println();
        System.out.println();

        final int count =1 ;
        int count2 = count;
        count2 = 3;
        System.out.println("----1----:"+count);
        System.out.println("----2----:"+count2);
        System.out.println("----3----:"+count);


    }
}


@Data
class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
