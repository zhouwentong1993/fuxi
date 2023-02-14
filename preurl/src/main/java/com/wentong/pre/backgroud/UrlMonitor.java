package com.wentong.pre.backgroud;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.wentong.constants.Constants;
import com.wentong.pre.gather.UrlGather;
import com.wentong.pre.service.DataService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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


    // 定时任务，每隔五秒执行一次。扫描文件夹，如果文件数量小于 5 个，就生成文件。
    @PostConstruct
    public void init() {
        log.info("UrlMonitor init");
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("UrlMonitor run start");
            List<String> strings = FileUtil.listFileNames(Constants.BASE_DIR);
            if (CollUtil.isEmpty(strings)) {
                log.info("没有文件");
                for (int i = 0; i < Constants.MIN_FILE_SIZE; i++) {
                    DataService.add(urlGather.gather());
                }
            } else {
                if (strings.size() < Constants.MIN_FILE_SIZE) {
                    log.info("文件数量不足");
                    for (int i = 0; i < Constants.MIN_FILE_SIZE - strings.size(); i++) {
                        DataService.add(urlGather.gather());
                    }
                }
            }
            log.info("UrlMonitor run end");
        }, 0, 5, TimeUnit.SECONDS);
    }

}
