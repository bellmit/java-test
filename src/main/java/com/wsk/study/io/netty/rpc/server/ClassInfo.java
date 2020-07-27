package com.wsk.study.io.netty.rpc.server;

/**
 * @Description:
 * @Auther: wsk
 * @Date: 2020/7/27 19:40
 * @Version: 1.0
 */
public class ClassInfo {
    private static final long serialVersionUID = -4250090690735951773L;

    private String className;//类名
    private String methodName;//方法名
    private Class<?>[] types;//参数类型
    private Object[] objects;//参数列表

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getTypes() {
        return types;
    }

    public void setTypes(Class<?>[] types) {
        this.types = types;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }
}
