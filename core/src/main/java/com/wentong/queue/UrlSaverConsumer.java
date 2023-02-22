package com.wentong.queue;

import com.google.common.collect.Lists;
import com.wentong.thread.ServiceThread;
import com.wentong.vo.TinyUrl;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 对队列进行消费，进行批量插入操作
 */
public class UrlSaverConsumer extends ServiceThread {

    private static final int BATCH_SIZE = 1000;
    List<TinyUrl> list = Lists.newArrayListWithCapacity(BATCH_SIZE);
    private static CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void run() {
        while (!isStopped()) {
            try {
                latch.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getServiceName() {
        return "urlSaverConsumer";
    }
}
