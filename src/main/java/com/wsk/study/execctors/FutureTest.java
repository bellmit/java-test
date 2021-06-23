package com.wsk.study.execctors;

import com.google.common.collect.Queues;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Queue;
import java.util.concurrent.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.integration.util.CallerBlocksPolicy;

/**
 * @description: 线程的Future测试
 * @author: wsk
 * @date: 2021/6/2 14:54
 * @version: 1.0
 */
@Slf4j
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                20, 20, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100),
                new ThreadFactoryBuilder().setNameFormat("node-refresh-%s").setDaemon(true).build(),
                new CallerBlocksPolicy(TimeUnit.MINUTES.toMillis(2)));
        ConcurrentLinkedQueue<Future<String>> taskResults = Queues.newConcurrentLinkedQueue();
        for (int number = 0; number < 10; number++) {
            int finalNumber = number;
            taskResults.add(executor.submit(() -> {
                try {
                    randanSlee(finalNumber);
                } catch (Exception e) {
                    return finalNumber+"发生了异常";
                }
                return "";
            }));
        }
        awaitAndCheckResults(taskResults);
        executor.shutdown();
    }

    private static void randanSlee(int number) {
        int i = (int) (Math.random() * 10);
        try {
            Thread.sleep(i * 1000);
            log.info("线程" + number + "，沉睡了" + i + "秒");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static void awaitAndCheckResults(Queue<Future<String>> results) throws ExecutionException, InterruptedException {
        String finalException = null;
        while (true) {
            Future<String> result = results.poll();
            if (result == null) {
                // All jobs done.
                break;
            }
            String e = result.get();
            if (StringUtils.isNotEmpty(e)) {
                finalException = e;
            }
        }
        if (finalException != null) {
            System.out.println(finalException);
        }
    }
}
