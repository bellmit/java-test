package com.wsk.study.commonsclass;

/**
 * 字节测试，节点编码10就是换行符
 */
public class ByteTest {
    public static void main(String[] args) {
        String s = new String(new byte[]{10});
        System.out.println(s);
        String c  = "\n";
        System.out.println(c.getBytes()[0]);
        // 10是换行符字节10进制的表示
        byte b = (byte) 10;
        System.out.println(b);
    }
}
