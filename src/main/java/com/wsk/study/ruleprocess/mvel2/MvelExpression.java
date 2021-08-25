package com.wsk.study.ruleprocess.mvel2;

import org.mvel2.MVEL;

/**
 * @author jyk
 * @since 1.0.0, 2020/10/7
 */
public class MvelExpression<T> extends Expression {

    @Override
    public T eval(Context ctx) {
        try {
            return (T) MVEL.eval(getExpression(), ctx.getBody());
        } catch (Exception e) {
            ctx.setException(e);
            return null;
        }
    }
}
