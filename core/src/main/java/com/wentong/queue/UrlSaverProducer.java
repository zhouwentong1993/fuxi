package com.wentong.queue;

import com.wentong.vo.TinyUrl;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 向队列中添加数据
 */
@Slf4j
public class UrlSaverProducer {

    public static void put(TinyUrl tinyUrl) {
        // 需要指定超时时间，避免长时间阻塞
        try {
            UrlQueue.QUEUE.offer(tinyUrl, 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("put error", e);
        }
    }

}
