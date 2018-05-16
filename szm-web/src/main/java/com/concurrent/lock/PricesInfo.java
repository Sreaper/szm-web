package com.concurrent.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author songzhimao
 * @date 2018/2/19
 */
public class PricesInfo {
    private volatile double price1;
    private  volatile  double price2;
    private ReadWriteLock lock;


    public PricesInfo(){
        price1=1.0;
        price2=2.0;
        lock=new ReentrantReadWriteLock();
    }

    public double getPrice1() {
        lock.readLock().lock();
        double value=price1;
//        System.out.printf("%s: Price 1: %f\n" ,Thread.currentThread().getName(),value);
        lock.readLock().unlock();
        return value;
    }

    public double getPrice2() {
        lock.readLock().lock();
        double value=price2;
//        System.out.printf("%s: Price 2: %f\n" ,Thread.currentThread().getName(),value);
        lock.readLock().unlock();
        return value;
    }

    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        this.price1=price1;
        this.price2=price2;
        lock.writeLock().unlock();
    }


}
