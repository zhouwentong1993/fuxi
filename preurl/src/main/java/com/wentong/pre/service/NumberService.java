package com.wentong.pre.service;

import cn.hutool.core.util.StrUtil;
import com.wentong.metrics.ApplicationStartUp;
import com.wentong.pre.loader.Loader;
import com.wentong.pre.mapper.TinyUrlMapper;
import com.wentong.pre.vo.TinyUrl;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.wentong.constants.Constants.URL_PREFIX;
import static com.wentong.pool.ThreadPools.SAVER_EXECUTOR;

/**
 * 数字生成服务
 * todo 需要有一个后台进程监听文件的数量，如果小于 5 个，生成文件。
 */
@Service
@Slf4j
public class NumberService {

    @Autowired
    private Loader loader;
    @Autowired
    private TinyUrlMapper tinyUrlMapper;

    @PostConstruct
    public void init() {

        ApplicationStartUp startUp = ApplicationStartUp.DEFAULT.start();

        List<String> load = loader.load();
        DataService.add(load);
        log.info("load {} numbers into memory", load.size());
        startUp.stop();
    }

    public void loadData(List<String> data) {
        DataService.add(data);
    }

    @SneakyThrows
    public String getNumber(String sourceUrl) {
        String number = DataService.get();
        CompletableFuture.runAsync(() -> {
            if (StrUtil.isNotBlank(number)) {
                TinyUrl tinyUrl = new TinyUrl();
                tinyUrl.setUrl(URL_PREFIX + number);
                tinyUrl.setSourceUrl(sourceUrl);
                tinyUrl.setCreateTime(new Date());
                tinyUrl.setUpdateTime(new Date());
                tinyUrlMapper.insert(tinyUrl);
            }
        }, SAVER_EXECUTOR);
        return URL_PREFIX + number;
    }

}
