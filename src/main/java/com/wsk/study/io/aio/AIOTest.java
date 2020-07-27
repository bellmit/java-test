package com.wsk.study.io.aio;

/**
 * @Description:  JDK 7 引入了Asynchronous I/O，AIO 即NIO2.0，叫做异步不阻塞的IO。AIO 引入异步通道的概念，采用了Proactor
 * 模式，简化了程序编写，一个有效的请求才启动一个线程，它的特点是先由操作系统完成后才通知服务端程序启动线程去处理，一般适用于连接数较多且连接时间较长的应用。                                     
 * 目前AIO 还没有广泛应用，并且也不是本课程的重点内容，这里暂不做讲解。
 * @Auther: wsk
 * @Date: 2020/7/27 17:09
 * @Version: 1.0
 */
public class AIOTest {
}
