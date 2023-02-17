package com.wentong.client;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public final class RedisClientUtil {

    static RedissonClient redissonClient;

    static {
        Config config = new Config();
        // 1. Create config object
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        redissonClient = Redisson.create(config);
    }

    public static String get(String key) {
        return (String) redissonClient.getBucket(key).get();
    }

    public static void set(String key, String value) {
        redissonClient.getBucket(key).set(value);
    }

    public static void set(String key, String value, long time, TimeUnit timeUnit) {
        redissonClient.getBucket(key).set(value, time, timeUnit);
    }

}
