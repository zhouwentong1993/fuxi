package com.wentong.queue;

import com.wentong.vo.TinyUrl;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 向队列中添加数据
 */
public class UrlSaverProducer {

    @SneakyThrows
    public void put(TinyUrl tinyUrl) {
        // 需要指定超时时间，避免长时间阻塞
        UrlQueue.QUEUE.offer(tinyUrl, 1, TimeUnit.SECONDS);
    }

}
