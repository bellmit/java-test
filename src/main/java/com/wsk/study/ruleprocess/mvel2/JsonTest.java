package com.wsk.study.ruleprocess.mvel2;

import java.util.HashMap;
import java.util.Map;

/**
 * 提取Json字符串中的字段
 */
public class JsonTest {
    public static void main(String[] args) {
        String expression = "com.alibaba.fastjson.JSON.parseObject(value).getString(\"tax_number\")";
        MvelExpression<String> mvelExpression = new MvelExpression<>();
        mvelExpression.setExpression(expression);
        Map<String, Object> body = new HashMap<>(1);
        body.put("value","{\"customer_id\":\"837862506613\",\"full_name\":\"15988820659\",\"tax_number\":\"681b972aec7243709a625f0051436f40\"}");
        Context context = new Context(body);
        try {
            String columnValue = mvelExpression.eval(context);
            System.out.printf(""+columnValue);
        } catch (Exception e) {
            throw e;
        }
    }
}
