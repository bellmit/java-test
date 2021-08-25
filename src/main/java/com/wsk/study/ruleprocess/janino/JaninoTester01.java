package com.wsk.study.ruleprocess.janino;

import org.codehaus.commons.compiler.IScriptEvaluator;
import org.codehaus.janino.ScriptEvaluator;

/**
 * 简单的脚本计算
 */
public class JaninoTester01 {

    public static void main(String[] args) {
        try {
            String content="System.out.println(\"Hello world\");";
            IScriptEvaluator evaluator = new ScriptEvaluator();
            evaluator.cook(content);
            evaluator.evaluate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
