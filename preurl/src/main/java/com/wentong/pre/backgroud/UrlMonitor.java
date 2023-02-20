package com.wentong.pre.backgroud;

import com.wentong.constants.Constants;
import com.wentong.pre.gather.UrlGather;
import com.wentong.pre.service.DataService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UrlMonitor {

    private final UrlGather urlGather;

    public UrlMonitor(UrlGather urlGather) {
        this.urlGather = urlGather;
    }


    // 定时任务，每隔五秒执行一次。扫描数量，如果数量不足 500w，就生成数据。
    // 按照 QPS 2w/s 算，可以支撑 4 分钟的使用量。 每隔 5s 扫描一下，每次补充 100w 数据，基本保证补充线程与获取线程不互相影响。
    @PostConstruct
    public void init() {
        log.info("UrlMonitor init");
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("UrlMonitor run start");
            int size = DataService.size();
            if (size < Constants.MIN_DATA_SIZE) {
                log.info("数据不足");
                // 每次补充 100w
                DataService.add(urlGather.gather());
            }
            log.info("UrlMonitor run end");
        }, 0, 5, TimeUnit.SECONDS);
    }

}
