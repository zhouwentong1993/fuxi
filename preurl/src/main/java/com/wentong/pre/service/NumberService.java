package com.wentong.pre.service;

import com.wentong.pre.loader.Loader;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wentong.constants.Constants.URL_PREFIX;

/**
 * 数字生成服务
 */
@Service
@Slf4j
public class NumberService {

    @Autowired
    private Loader loader;

    @PostConstruct
    public void init() {
        List<String> load = loader.load();
        DataService.add(load);
        log.info("load {} numbers into memory", load.size());
    }

    public void loadData(List<String> data) {
        DataService.add(data);
    }

    @SneakyThrows
    public String getNumber(String sourceUrl) {
        String number = DataService.get();
        return URL_PREFIX + number;
    }

}
