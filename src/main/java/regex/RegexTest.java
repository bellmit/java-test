package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Auther: wsk
 * @Date: 2020/9/9 15:33
 * @Version: 1.0
 */
public class RegexTest {

    static final String DDL_REGEX = "^create[\\s]+table[\\s]+if[\\s]+not[\\s]+exists[\\s]+([A-Za-z0-9_-]+)\\." +
            "([A-Za-z0-9_-]+)[\\s]*(as\\b)?[\\s\\S]+";
    private transient static final Pattern COL_PATTERN = Pattern.compile(DDL_REGEX, Pattern.CASE_INSENSITIVE);


    private transient static final String COL_REGEX2 = "^DECIMAL\\(([\\S]*),([\\S]*)\\)";
    private transient static final Pattern COL_PATTERN2 = Pattern.compile(COL_REGEX2);



    public static void main(String[] args) {
        String expression = "CREATE  TABLE IF\t NOT \tEXISTS  aaaa.bbb" +
                "  (fdsafsdfjsdopfjpoasdifpoifpodsfsdjfpsdjfsjdfjsdfjl";
        Matcher matcher = COL_PATTERN.matcher(expression);
//        if(matcher.matches()){
        if (matcher.find()) {
            String database = matcher.group(1);
            String tableName = matcher.group(2);
            String isas = matcher.group(3);
            System.out.println("匹配结果：" + database);
            System.out.println("匹配结果：" + tableName);
            System.out.println("匹配结果：" + isas);
        } else {

        }

//        String expression2 = "DECIMAL(10,7)";
//        Matcher matcher2 = COL_PATTERN2.matcher(expression2);
//        matcher2.find();
//        System.out.println("匹配结果：" + matcher2.group(1));
//        System.out.println("匹配结果：" + matcher2.group(2));
    }
}
