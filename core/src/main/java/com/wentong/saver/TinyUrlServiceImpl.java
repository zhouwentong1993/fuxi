package com.wentong.saver;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wentong.mapper.TinyUrlMapper;
import com.wentong.vo.TinyUrl;
import org.springframework.stereotype.Service;

@Service
public class TinyUrlServiceImpl extends ServiceImpl<TinyUrlMapper, TinyUrl> implements TinyUrlService {
}
