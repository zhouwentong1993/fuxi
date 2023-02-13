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
    public void start(String name) {
        this.name = name;
        stopwatch = Stopwatch.createStarted();
        System.out.println("metrics of " + name + " start");
    }

    @Override
    public void start() {
        System.out.println("metrics of " + name + " start");
        stopwatch.start();
    }

    @Override
    public void stop() {
        stopwatch.stop();
        System.out.println("metrics of[" + name + "],cost time: " + stopwatch.elapsed(timeUnit) + ")");
    }
}
