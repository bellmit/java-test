package com.wsk.study.ruleprocess.janino;

import org.codehaus.commons.compiler.IScriptEvaluator;
import org.codehaus.janino.ScriptEvaluator;

/**
 * 脚本中对自定义类调用
 */
public class JaninoTester07 {

    public static void main(String[] args) {
        try {
            IScriptEvaluator se = new ScriptEvaluator();
            se.setReturnType(String.class);
            se.cook("import com.wsk.study.ruleprocess.janino.BaseClass;\n"
                    + "import com.wsk.study.ruleprocess.janino.DerivedClass;\n"
                    + "BaseClass o=new DerivedClass(\"1\",\"join\");\n"
                    + "return o.toString();\n");
            Object res = se.evaluate(new Object[0]);
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
