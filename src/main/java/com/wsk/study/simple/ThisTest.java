package com.wsk.study.simple;

/**
 * @description: this supper 使用场景测试当方法未被重写时，supper与this调用结果相同，如下getClass
 * 	构造方法中this调用其它构造方法：
 * 		1）只能在构造方法中通过this来调用其他构造方法，普通方法中不能使用；
 * 		2）不能通过this递归调用构造方法，即不能在一个构造方法中通过this直接或间接调用该构造方法本身。
 * 		3）通过this调用其他构造方法必须放在构造方法的第一行中执行。由于super调用父类的构造函数也必须放在构造方法的第一行中执行，因此，通过this和super调用构造方法不能同时出现一个构造方法中
 * 	非构造方法中的this关键字：
 * 		this.xxx; 访问类中的成员变量xxx
 * 		this.yyy(paras…); 访问类中的成员方法yyy
 * 		this; 当前类对象的引用
 * 	继承关系下父类的this关键字
 * 		1) this(paras…); 无论子类是否有相同参数的构造方法，this(paras…);访问的始终是父类中的构造方法。
 * 		2）this.xxx：无论子类是否有覆盖了该成员变量，this.xxx;访问的始终是父类中的成员变量。
 * 		3) this.yyy(paras…); 访问类中的成员方法yyy，如果子类重写了该成员方法，则this.yyy(paras…);访问的是子类的成员方法
 * 		4) this： this始终代表的是子类的对象。
 * @author: wsk
 * @date: 2021/4/9 19:36
 * @version: 1.0
 */

public class ThisTest {
    public static void main(String[] args) {
        Child child = new Child();
        child.show();
    }
}

class Parent {
    private Parent mSelf;
    Parent(){
        mSelf = this;
    }
    public void show() {
        System.out.println(this.getClass().getName());
        System.out.println(super.getClass().getName());
        System.out.println(mSelf.getClass().getName());
    }
}

class Child extends Parent {
    public void show() {
        System.out.println(this.getClass().getName());
        System.out.println(super.getClass().getName());
        super.show();
    }
}
