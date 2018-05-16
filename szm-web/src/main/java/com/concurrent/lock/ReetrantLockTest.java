package com.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author songzhimao
 * @date 2018/2/19
 */
public class ReetrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        for (int i =0;i<10;i++) {
            reentrantLock.lock();
        }
        ReenChild reenChild = new ReenChild(reentrantLock);
        new Thread(reenChild).start();

        for (int j=0;j<10;j++) {
            System.out.println(reentrantLock.getHoldCount()+"===============");
            reentrantLock.unlock();
            Thread.sleep(500L);
        }

        System.out.println(111);
    }

    static class ReenChild implements Runnable {
        private ReentrantLock reentrantLock;
        public ReenChild(ReentrantLock reentrantLock) {
            this.reentrantLock = reentrantLock;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                while (!reentrantLock.tryLock(1, TimeUnit.SECONDS)){
                    System.out.println("还没有获得到锁");
                }
                System.out.println("get lock");
                reentrantLock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {

            }


        }
    }

}
