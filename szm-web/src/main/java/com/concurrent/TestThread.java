package com.concurrent;

/**
 * Created by songzhimao on 2016/10/10.
 */
public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("测试下");
                }
            }
        }).start();
//        Thread.sleep(3000);
        System.out.println("主线程执行完成");
    }

}
