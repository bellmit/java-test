package com.wsk.study.simple;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @description: 为了保证类型安全，引入了泛型的通配符，泛型上限 <? extends E></> 下限测试<? super E></>;
 * 存取原则(PECS,Producer Extends，Consumer Super):
 *          数据结构只做存储数据即consumer数据则用super，如果仅用作取数即Producer数据则用extends
 *
 * @author: wsk
 * @date: 2021/4/9 14:28
 * @version: 1.0
 */
public class PecsTest {
    public static void main(String[] args) {
//
//        shangxian();
//
//        xiaxian();

        pecs();

    }

    private static void pecs() {
        /**
         *PECS: jdk1.5就不提倡使用强制转化，下限设计出来就是用作存储数据的，不建议又用作取数,
         * 如下的案例：copy的目标集合是，源集合的同一类型或者是其父类即可编译通过，其并不能推出copy中T的类型是什么。
         *
         */
        List<People> peopleList3 = new ArrayList<>();
        peopleList3.add(new People("w"));
        peopleList3.add(new People("s"));
        peopleList3.add(new People("k"));
//        List<People> peopleList4 = new ArrayList<>();
//        peopleList4.add(new People());
//        peopleList4.add(new People());
//        peopleList4.add(new People());
//        Collections.copy(peopleList4, peopleList3);
//        peopleList4.forEach(c -> System.out.println(c.getName()));

        //存数据的可以是其父类型
        List<World> worldList = new ArrayList<>();
        worldList.add(new World());
        worldList.add(new World());
        worldList.add(new World());
        Collections.copy(worldList, peopleList3);
    }

    private static void xiaxian() {
        /**
         * 下限，即存储的数是E的父类或其本身（存储时存储的是子类以及本身对象）,适合只写，不读的场景
         */
        List<? super People> peopleList2 = new ArrayList<>();
        //申明时
        peopleList2 = new ArrayList<World>();
        peopleList2 = new ArrayList<People>();
//        peopleList2 = new ArrayList<Teacher>(); 编译不通过
        //写入数据，因为未具体指定父类类型，故无法写入其父类对象，但是可以由于子类能自动向上转型，可写入该类的子类以及本身对象
//        peopleList2.add(new World());编译不同过
        peopleList2.add(new People());
        peopleList2.add(new Teacher());
        //读取数据时由于不确定存储的是什么类型，则默认是Object，具体类型需要自己强制向下转化
        Object object1 = peopleList2.get(0);
        Teacher teacher = (Teacher)peopleList2.get(1);
    }

    private static void shangxian() {
        /**
         * 上限，即存储的数是E的子类或其本身,适合只读，不写入的场景
         */
        List<? extends People> peopleList = new ArrayList<>();
        //申明时
//    peopleList = new ArrayList<World>(); 编译不通过
        peopleList = new ArrayList<People>();
        peopleList = new ArrayList<Teacher>();
//        无法写入数据，因为并不知道具体哪一种子类类型
//        peopleList.add(new World());
//        peopleList.add(new People());
//        peopleList.add(new Teacher());

//        读取可以，可向上转为子类的父类
        People people = peopleList.get(0);
    }
}

class World { }

@Data
class People extends World {
    private  String name;

    public People() {
    }

    public People(String name) {
        this.name = name;
    }
}

class Teacher extends People {
    public Teacher() {
    }
    public Teacher(String name) {
        super(name);
    }
}

