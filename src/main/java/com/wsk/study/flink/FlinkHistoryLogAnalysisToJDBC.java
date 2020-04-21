package com.wsk.study.flink;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wsk.study.jdbc.JDBCUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: Flink 任务日志分析:统计计算耗时时间
 * @Auther: wsk
 * @Date: 2020/4/1 11:36
 * @Version: 1.0
 */
public class FlinkHistoryLogAnalysisToJDBC {

    public static Map<String, Long> content = new HashMap<>();

    public static void main(String[] args) {
        File file = new File(args[0]);
        File[] files = file.listFiles();
        int count = 0;
        BufferedReader reader = null;
        StringBuilder sql = new StringBuilder("insert into iitcore_flink_history(jid,name,state,start_time,end_time," +
                "duration,last_modification,customer_ids,exceptions,exception_time)values(?,?,?,?,?,?,?,?,?,?)");
        Connection connection = JDBCUtil.getConnection("root", "servyou", "jdbc:mysql://10.199.140" +
                ".143:3306/dev_test?useUnicode=true&characterEncoding=utf-8", "com.mysql.jdbc.Driver");
        PreparedStatement preparedStatement = null;
        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            for (File f : files) {
                String fileName = f.getName();
                String jid = "";
                String name = "";
                String state = "";
                String startTime = "";
                String endTime = "";
                int durition = 0;
                String lastModification = "";
                String exceptions = "";
                String custids = "";
                String exceptionTime;

                try {
                    reader = new BufferedReader(new FileReader(f));
                    String tempStr;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    while ((tempStr = reader.readLine()) != null) {
                        Object parse = JSONObject.parse(tempStr);
                        if (Objects.nonNull(parse)) {
                            JSONArray objects = (JSONArray) ((JSONObject) parse).get("archive");
                            for (Object o : objects) {
                                Object path = ((JSONObject) o).get("path");
                                if ("/jobs/overview".equals(path.toString())) {
                                    String json = ((JSONObject) o).get("json").toString();
                                    JSONObject jsonObject =
                                            (JSONObject) ((JSONArray) (JSONObject.parseObject(json).get("jobs"))).get(0);
                                    jid = jsonObject.get("jid").toString();
                                    name = jsonObject.get("name").toString();
                                    state = (String) jsonObject.get("state");
                                    startTime = format.format(new Date((Long) jsonObject.get("start-time")));
                                    endTime = format.format(new Date((Long) jsonObject.get("end-time")));
                                    durition = (Integer) jsonObject.get("duration");
                                    lastModification = format.format(new Date((Long) jsonObject.get("last" +
                                            "-modification")));
//                                    sql.append("(");
//                                    sql.append("'" + jid + "',");
//                                    sql.append("'" + name + "',");
//                                    sql.append("'" + state + "',");
//                                    sql.append("'" + startTime + "',");
//                                    sql.append("'" + endTime + "',");
//                                    sql.append("'" + durition + "',");
//                                    sql.append("'" + lastModification + "',");
                                    statement.setString(1, jid);
                                    statement.setString(2, name);
                                    statement.setString(3, state);
                                    statement.setString(4, startTime);
                                    statement.setString(5, endTime);
                                    statement.setInt(6, durition);
                                    statement.setString(7, lastModification);

                                    continue;
                                }
                                if (path.toString().endsWith("/plan")) {
                                    custids = ((JSONObject) o).get("json").toString();

                                    JSONObject ooo = JSONObject.parseObject(custids);
                                    JSONArray array = ooo.getJSONObject("plan").getJSONArray("nodes");
                                    if (array != null && array.size() > 0) {
                                        for (int i = 0; i < array.size(); i++) {
                                            String desc = array.getJSONObject(i).get("description").toString();
                                            if (desc != null && desc.startsWith("Source: HBaseTableSource") && desc.contains("customer_id")) {
                                                desc = desc.substring(desc.indexOf("where=["));
                                                desc = desc.substring(0, desc.indexOf("]") + 1);
                                                if (desc.contains("customer_id")) {
                                                    desc = desc.replace("'", "").replace("\\", "\\\\");
                                                    custids = desc;
                                                    break;
                                                } else {
                                                    continue;
                                                }
                                            }
                                        }
                                    } else {
                                        System.err.println("======" + custids);

                                    }
                                    statement.setString(8, custids);
//                                    sql.append("'" + custids + "',");
                                    continue;
                                }
                                if (path.toString().endsWith("/exceptions")) {
                                    JSONObject expObj = ((JSONObject) o).getJSONObject("json");
                                    exceptions = expObj.get("root-exception") == null ? null : expObj.get("root" +
                                            "-exception").toString();
                                    exceptionTime = expObj.get("timestamp") == null ? null :
                                            format.format(new Date((Long) expObj.get("timestamp")));
                                    statement.setString(9, exceptions);
                                    statement.setString(10, exceptionTime);
//                                    if (exceptions == null) {
//                                        sql.append("" + exceptions + ",");
//                                    } else {
//                                        sql.append("'" + exceptions + "',");
//                                    }
//                                    if (exceptionTime == null) {
//                                        sql.append("" + exceptionTime + "");
//                                    } else {
//                                        sql.append("'" + exceptionTime + "'");
//                                    }
//                                    sql.append(")");
                                    break;
                                }


                            }
                        }
                    }
                    //System.err.println(sql);
                    boolean execute = statement.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                count++;
                if (count % 100 == 0) {
                    System.out.println("已读取文件：" + count + " 个");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("已读取文件：" + count + " 个");

        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class OverView {
        private String jid;

        private Long startTime;

        private Long endTime;

        public String getJid() {
            return jid;
        }

        public void setJid(String jid) {
            this.jid = jid;
        }

        public Long getStartTime() {
            return startTime;
        }

        public void setStartTime(Long startTime) {
            this.startTime = startTime;
        }

        public Long getEndTime() {
            return endTime;
        }

        public void setEndTime(Long endTime) {
            this.endTime = endTime;
        }
    }

}
