package com.wsk.study.flink;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: Flink 任务日志分析:统计计算耗时时间
 * @Auther: wsk
 * @Date: 2020/4/1 11:36
 * @Version: 1.0
 */
public class FlinkHistoryLogAnalysis {

    public static Map<String, Long> content= new HashMap<>();

    public static void main(String[] args) {
        File file = new File(args[0]);
        File[] files = file.listFiles();
        int count = 0 ;
        BufferedReader reader = null;
        for(File f :files){
            try {
                reader = new BufferedReader(new FileReader(f));
                String tempStr;
                while ((tempStr = reader.readLine()) != null) {
                    Object parse = JSONObject.parse(tempStr);
                    if(Objects.nonNull(parse)){
                        JSONArray objects = (JSONArray) ((JSONObject) parse).get("archive");
                        for(Object o : objects){
                            Object path = ((JSONObject) o).get("path");
                            if("/jobs/overview".equals(path.toString())){
                                String json = ((JSONObject) o).get("json").toString();
                                JSONObject jsonObject = (JSONObject)((JSONArray)(JSONObject.parseObject(json).get("jobs"))).get(0);
                                String jid = jsonObject.get("jid").toString();
                                String name = jsonObject.get("name").toString();
                                if(StringUtils.startsWith(name,"geshui")){
                                    long strartTime = (Long) jsonObject.get("start-time");
                                    long endTime = (Long) jsonObject.get("end-time");
                                    String status = (String) jsonObject.get("state");
                                    long durition = (endTime - strartTime) / 1000;
                                    content.put(jid,durition);
                                    if(durition>2*60){
                                        FileChannel input = new FileInputStream(f).getChannel();
                                        FileChannel output =
                                                new FileOutputStream(new File(args[1]+"/"+f.getName())).getChannel();
                                        output.transferFrom(input, 0, input.size());
                                        input.close();
                                        output.close();
                                    }
                                    if("FAILED".equals(status)){
                                        FileChannel input = new FileInputStream(f).getChannel();
                                        FileChannel output =
                                                new FileOutputStream(new File(args[2]+"/"+f.getName())).getChannel();
                                        output.transferFrom(input, 0, input.size());
                                        input.close();
                                        output.close();
                                    }
                                    if((strartTime+3*24*60*60*1000)>System.currentTimeMillis()){
                                        FileChannel input = new FileInputStream(f).getChannel();
                                        FileChannel output =
                                                new FileOutputStream(new File(args[3]+"/"+f.getName())).getChannel();
                                        output.transferFrom(input, 0, input.size());
                                        input.close();
                                        output.close();
                                    }
                                }

                                break;
                            }
                        }
                    }
                }
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
            if(count%100==0){
                System.out.println("已读取文件："+count+" 个");
            }
        }
        System.out.println("已读取文件："+count+" 个");
        if(reader!=null){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AtomicInteger normalCount = new AtomicInteger();
        AtomicInteger overCount_2 = new AtomicInteger();
        AtomicInteger overCount_5 = new AtomicInteger();
        AtomicInteger overCount_8 = new AtomicInteger();
        content.forEach((jobid, time) -> {
            if(time > 8* 60){
                overCount_8.getAndIncrement();
                System.out.println("超时作业：jobid="+jobid+" 调度时间="+time);
            }else if(time>=5*60 && time<8*60){
                overCount_5.getAndIncrement();
            } else if(time>=2*60 && time<5*60){
                overCount_2.getAndIncrement();
            }else{
                normalCount.getAndIncrement();
            }

        });
        System.out.println("[0,2):"+normalCount.get());
        System.out.println("[2,5):"+overCount_2.get());
        System.out.println("[5,8):"+overCount_5.get());
        System.out.println("[8,~):"+overCount_8.get());

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
