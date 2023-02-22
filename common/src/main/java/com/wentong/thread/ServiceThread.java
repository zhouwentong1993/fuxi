package com.wentong.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ServiceThread implements Runnable {

    private volatile boolean stopped = true;

    private Thread thread;
    protected final CountDownLatch2 waitPoint = new CountDownLatch2(1);
    protected volatile AtomicBoolean hasNotified = new AtomicBoolean(false);

    public abstract String getServiceName();

    // 用父类的 start 修饰子类的 start 方法
    public void start() {
        stopped = false;
        thread = new Thread(this, getServiceName());
        thread.setDaemon(true);
        thread.start();
    }

    public boolean isStopped() {
        return stopped;
    }

    public void shutdown() {
        stopped = true;
        thread.interrupt();
    }

    // 等待多久，再次执行。
    // 这里利用了重写的 CountDownLatch2 的 reset 功能
    protected void waitForRunning(long interval) {

        if (hasNotified.compareAndSet(true, false)) {
            onWaitEnd();
            return;
        }

        waitPoint.reset();
        try {
            waitPoint.await(interval, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            hasNotified.set(false);
            waitPoint.countDown();
            onWaitEnd();
        }
    }

    protected void onWaitEnd() {
    }

}
