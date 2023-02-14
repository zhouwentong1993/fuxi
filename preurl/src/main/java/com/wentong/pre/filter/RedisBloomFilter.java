package com.wentong.pre.filter;

// todo: 用 redis 实现布隆过滤器，这样可以实现分布式扩容
public class RedisBloomFilter implements Filter{
    @Override
    public boolean filter(String url) {
        return false;
    }
}
