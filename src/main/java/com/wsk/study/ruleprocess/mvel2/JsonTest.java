package com.wsk.study.ruleprocess.mvel2;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 提取Json字符串中的字段
 */
public class JsonTest {
    public static void main(String[] args) {
        String expression = "com.alibaba.fastjson.JSON.parseObject(this['ocecd_3@full_name']).getString(\"full_name\")".toLowerCase();
        MvelExpression<String> mvelExpression = new MvelExpression<>();
        mvelExpression.setExpression(expression);
        Map<String, Object> body = new HashMap<>(1);
        body.put("ocecd_2@extended_field", "{\"customer_id\":\"1297727684758\",\"customerName\":\"å¹¿ä¸åçæ³°å®ä¸æéå¬å¸\",\"taxNo\":\"91441723MA51B5EJ59\"}");
        Context context = new Context(body);
        try {
            String columnValue = mvelExpression.eval(context);
            System.out.printf(""+columnValue);
        } catch (Exception e) {
            throw e;
        }

        String str = "91650102660620459R";
        BigDecimal bigDecimal = new BigDecimal(str);
        System.out.println(bigDecimal.doubleValue());
    }
}
