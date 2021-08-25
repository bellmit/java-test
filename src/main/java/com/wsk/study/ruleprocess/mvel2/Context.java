package com.wsk.study.ruleprocess.mvel2;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 处理器上下文
 *
 * @author jyk
 * @since 1.0.0, 2020/10/7
 */
@Getter
@Setter
public class Context {

    /**
     * 数据头部，可存放过程中的变量
     */
    Map headers;

    /**
     * 每个processor的输出
     */
    Object body;

    /**
     * processor执行异常
     */
    Exception exception;

    /**
     * 系统上下文
     */
    SystemContext system;

    public Context(Object body) {
        this.body = body;
    }
}
