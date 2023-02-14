package com.wentong.metrics;

public interface ApplicationStartUp {

    ApplicationStartUp DEFAULT = new DefaultApplicationStartUp("default");

    ApplicationStartUp start(String name);

    ApplicationStartUp start();

    ApplicationStartUp stop();

}
