package com.wentong.pre.service;

import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class DataService {

    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void add(String url) {
        queue.add(url);
    }

    public static void add(List<String> url) {
        queue.addAll(url);
    }

    @SneakyThrows
    public static String get() {
        return queue.poll(500, TimeUnit.MILLISECONDS);
    }

}
