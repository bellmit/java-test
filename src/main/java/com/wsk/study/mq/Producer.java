package com.wsk.study.mq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * @description:
 * @author: wsk
 * @date: 2020/11/11 16:11
 * @version: 1.0
 */
public class Producer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("bigdata-rtc-producer");
        producer.setNamesrvAddr("10.199.150.168:9876;10.199.150.169:9876");
        producer.start();
        try {
            for (int i = 0; i < 1; i++) {
                JobMessageReceive messageReceive = new JobMessageReceive();
                messageReceive.setAction(JobAction.START);
                messageReceive.setJobId(6);
                Map<String, Object> params = new HashMap<>(8);
                params.put("periodStart","201901");
                params.put("customerIdList", Arrays.asList(new Long[]{12976567256634L}));
                params.put("periodEnd","201912");
                messageReceive.setParams(params);
                messageReceive.setReplyTopic("test%iitanalysis-queue-fsp-analysis-finished");
                messageReceive.setSender("fspAnalysisDetail");
                Message message = new Message("test%datark-rtc_job_message", JSON.toJSONString(messageReceive).getBytes());
                System.out.println("生产者发送消息:" + JSON.toJSONString(messageReceive));
                producer.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }

    /**
     * 发送启动作业信息
     */

}
