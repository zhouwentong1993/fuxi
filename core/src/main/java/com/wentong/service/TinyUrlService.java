package com.wentong.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.wentong.client.HTTPClientUtil;
import com.wentong.client.RedisClientUtil;
import com.wentong.mapper.TinyUrlMapper;
import com.wentong.pool.ThreadPools;
import com.wentong.queue.UrlSaverProducer;
import com.wentong.vo.TinyUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.wentong.constants.Constants.URL_PREFIX;

@Service
@Slf4j
public class TinyUrlService {

    private final TinyUrlMapper tinyUrlMapper;

    public TinyUrlService(TinyUrlMapper tinyUrlMapper) {
        this.tinyUrlMapper = tinyUrlMapper;
    }

    public String transUrl(String sourceUrl) {
        String shortUrl = HTTPClientUtil.getByPath("http://localhost:8080/number/generator?sourceUrl=" + sourceUrl);
        // 这里可以通过消息队列的方式处理，将保存请求发到消息队列里面。避免堵住本地消息。
        // 消费端可以通过批量写入的方式进行优化。
        CompletableFuture.runAsync(() -> {
            TinyUrl tinyUrl = new TinyUrl();
            tinyUrl.setUrl(shortUrl);
            tinyUrl.setSourceUrl(sourceUrl);
            tinyUrl.setCreateTime(new Date());
            tinyUrl.setUpdateTime(new Date());
            UrlSaverProducer.put(tinyUrl);
            FileUtil.appendString(shortUrl, "url.txt", "UTF-8");
        }, ThreadPools.SAVER_EXECUTOR);
        return shortUrl;
    }

    public String visit(String tinyUrl) {
        log.info("请求短链地址：{}", tinyUrl);
        String sourceUrl = RedisClientUtil.get(tinyUrl);
        if (StrUtil.isBlank(sourceUrl)) {
            Map<String, Object> map = new HashMap<>();
            map.put("url", URL_PREFIX + tinyUrl);
            List<TinyUrl> tinyUrls = tinyUrlMapper.selectByMap(map);
            if (CollUtil.isEmpty(tinyUrls)) {
                RedisClientUtil.set(tinyUrl, "monitor", 10, TimeUnit.SECONDS);
                return StrUtil.EMPTY;
            } else {
                TinyUrl tinyUrl1 = tinyUrls.get(0);
                String sourceUrl1 = tinyUrl1.getSourceUrl();
                RedisClientUtil.set(tinyUrl, sourceUrl1, 1, TimeUnit.MINUTES);
                return sourceUrl1;
            }
        } else {
            return sourceUrl;
        }
    }
}
