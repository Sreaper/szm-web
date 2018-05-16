package com.concurrent.volitile;

import com.jd.middleware.toolkit.concurrent.NamedThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author songzhimao
 * @date 2018/5/1
 */
public class TestVolitile {
    public static ExecutorService putExecutor = new ThreadPoolExecutor(10, 10, 1000 * 60,
    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(50),
            new NamedThreadFactory("PutExecutor"));


    public static void main(String[] args) throws InterruptedException {
        StubThread stubThread = new StubThread();
        Sub sub = stubThread.getSub();
        for (int i =0;i<2;i++) {
            putExecutor.submit(stubThread);
        }
        Thread.sleep(1000);
//            thread.setRunning(false);
        sub.setFlag(false);
        stubThread.setSub(sub);

        System.out.println("已经赋值为false");
        putExecutor.submit(stubThread);
    }



}
class StubThread extends Thread {

    private volatile boolean isRunning = true;
    private Sub sub =new Sub();


    public Sub getSub() {
        return sub;
    }

    public void setSub(Sub sub) {
        this.sub = sub;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        System.out.println("进入run了");

//        while (isRunning) {
//        }
        while (sub.isFlag()) {
        }
        System.out.println("线程被停止了！");
    }

}
