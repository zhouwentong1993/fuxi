package com.wentong.thread;

public abstract class ServiceThread implements Runnable{

    private volatile boolean stopped = false;

    private Thread thread;

    abstract String getServiceName();

    // 用父类的 start 修饰子类的 start 方法
    public void start() {
        thread = new Thread(this, getServiceName());
        thread.setDaemon(true);
        thread.start();
    }


}
