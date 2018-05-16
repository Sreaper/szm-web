package com.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自旋锁
 * @author songzhimao
 * @date 2017/6/22
 */
public class CLHLock implements Lock{
    private final AtomicReference<QNode> tail;
    private final ThreadLocal<QNode> myPred;
    private final ThreadLocal<QNode> myNode;

    public CLHLock() {
        this.tail = new AtomicReference(new QNode());
        this.myPred = new ThreadLocal();
        this.myNode = new ThreadLocal(){
            @Override
            protected QNode initialValue() {
                return new QNode();
            }
        };
    }

    public void lock() {
        QNode qNode = myNode.get();
        QNode pred = tail.getAndSet(qNode);
        qNode.isLocked = true;
        myPred.set(pred);
        while (pred.isLocked) {

        }
    }

    public void unlock() {
        QNode qNode = myNode.get();
        qNode.isLocked = false;
        myNode.set(myPred.get());
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public Condition newCondition() {
        return null;
    }

    private static class QNode {
        volatile boolean isLocked;
    }

    public static void main(String[] args) {
        final CLHLock lock = new CLHLock();
        for (int i =0;i<-1;i++) {
            System.out.println("测试下");
            new Thread(new Runnable() {
                public void run() {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }
            }).start();
        }
    }
}
