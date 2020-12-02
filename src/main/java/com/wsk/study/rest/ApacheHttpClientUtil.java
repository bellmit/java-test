package com.wsk.study.rest;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @description:
 * @author: wsk
 * @date: 2020/10/12 14:10
 * @version: 1.0
 */
@Slf4j
public class ApacheHttpClientUtil {


    public static void post(JSONObject jsonObj, String url){
        HttpPost httpPost = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            httpPost = new HttpPost(url);

            //设置超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60 * 1000)
                    .setConnectionRequestTimeout(60 * 1000)
                    .setSocketTimeout(60 * 1000)
                    .setRedirectsEnabled(true)
                    .build();
            httpPost.setConfig(requestConfig);

            // 构造消息头
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("Connection", "Close");
            String sessionId = getSessionId();
            httpPost.setHeader("SessionId", sessionId);

            // 构建消息实体
            StringEntity entity = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");

            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            HttpResponse response;
            response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != HttpStatus.SC_OK){
                log.error("请求出错: "+statusCode);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if(httpPost != null){
                try {
                    httpPost.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        // 完全ok的。从生产获取的

    }

    public static String getSessionId(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }
}
