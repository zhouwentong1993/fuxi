package com.wentong.metrics;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class DefaultApplicationStartUp implements ApplicationStartUp {

    private String name;
    private Stopwatch stopwatch;
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;


    public DefaultApplicationStartUp(String name) {
        this.name = name;
        stopwatch = Stopwatch.createUnstarted();
    }

    public DefaultApplicationStartUp(String name, TimeUnit timeUnit) {
        this.name = name;
        this.timeUnit = timeUnit;
        stopwatch = Stopwatch.createUnstarted();
    }

    @Override
    public ApplicationStartUp start(String name) {
        this.name = name;
        stopwatch = Stopwatch.createStarted();
        log.info("metrics of [" + name + "] start");
        return this;
    }

    @Override
    public ApplicationStartUp start() {
        log.info("metrics of [" + name + "] start");
        stopwatch.start();
        return this;
    }

    @Override
    public ApplicationStartUp stop() {
        stopwatch.stop();
        log.info("metrics of [" + name + "],cost time: " + stopwatch.elapsed(timeUnit) + ")");
        return this;
    }
}
