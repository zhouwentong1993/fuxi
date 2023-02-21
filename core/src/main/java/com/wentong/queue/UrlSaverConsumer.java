package com.wentong.queue;

/**
 * 对队列进行消费，进行批量插入操作
 */
public class UrlSaverConsumer {

    private static volatile boolean start = false;

    public static synchronized void start() {
        start = true;
    }

    public static synchronized void stop() {
        start = false;
    }

    public static synchronized boolean isStart() {
        return start;
    }





}
