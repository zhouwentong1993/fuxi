package com.wentong.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPools {

    public static final ThreadPoolExecutor SAVER_EXECUTOR = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), new ThreadFactoryBuilder().setNameFormat("tiny-url-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());

}
