package com.wentong.metrics;

public interface ApplicationStartUp {

    ApplicationStartUp DEFAULT = new DefaultApplicationStartUp("default");

    void start(String name);

    void start();

    void stop();

}
