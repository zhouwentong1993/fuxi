package com.wentong.saver;

import com.wentong.mapper.TinyUrlMapper;
import com.wentong.vo.TinyUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MySQLSaver implements Saver {

    @Autowired
    private TinyUrlMapper tinyUrlMapper;

    @Override
    public void save(String url) {
        TinyUrl tinyUrl = new TinyUrl();
        tinyUrl.setUrl(url);
        tinyUrl.setCreateTime(new Date());
        tinyUrl.setUpdateTime(new Date());
        tinyUrlMapper.insert(tinyUrl);
    }

    @Override
    public void saveBatch(List<String> urls) {

    }
}
