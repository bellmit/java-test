package com.wsk.study.ruleprocess.mvel2;

/**
 * @author jyk
 * @since 1.0.0, 2020/10/7
 */
public abstract class Expression<T> {

    /**
     * mvel表达式
     */
    private String expression;

    public abstract T eval(Context ctx);

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
