package com.wentong.queue;

import com.wentong.vo.TinyUrl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class UrlQueue {

    // 弄一个比较大的队列
    public static final BlockingQueue<TinyUrl> QUEUE = new ArrayBlockingQueue<>(100 * 10000);

}
