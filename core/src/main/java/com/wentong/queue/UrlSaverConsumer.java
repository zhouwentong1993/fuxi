package com.wentong.queue;

import com.google.common.collect.Lists;
import com.wentong.saver.TinyUrlService;
import com.wentong.thread.ServiceThread;
import com.wentong.vo.TinyUrl;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 对队列进行消费，进行批量插入操作
 */
@Service
@Slf4j
public class UrlSaverConsumer extends ServiceThread {

    private static final int BATCH_SIZE = 5000;

    private final TinyUrlService tinyUrlService;

    List<TinyUrl> list = Lists.newArrayListWithCapacity(BATCH_SIZE);

    public UrlSaverConsumer(TinyUrlService tinyUrlService) {
        this.tinyUrlService = tinyUrlService;
    }

    @PostConstruct
    public void init() {
        start();
    }

    @Override
    public void run() {
        log.info("UrlSaverConsumer started");
        while (!isStopped()) {
            long startTime = System.currentTimeMillis();
            while (list.size() < BATCH_SIZE || System.currentTimeMillis() - startTime < 2000) {
                try {
                    TinyUrl tinyUrl = UrlQueue.QUEUE.poll(1, TimeUnit.SECONDS);
                    if (tinyUrl != null) {
                        list.add(tinyUrl);
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
            tinyUrlService.saveBatch(list);
            log.info("save {} urls", list.size());
            list.clear();
            // 休息 5 秒，每次批量插入 5000 条数据
            waitForRunning(2000);
        }
    }

    @Override
    public String getServiceName() {
        return "urlSaverConsumer";
    }
}
