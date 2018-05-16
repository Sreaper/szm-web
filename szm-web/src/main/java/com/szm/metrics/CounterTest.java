package com.szm.metrics;

import com.codahale.metrics.Counter;

import java.util.Random;

/**
 * @author songzhimao
 * @date 2017/11/8
 */
public class CounterTest extends Base {
    final static Counter exec = metric.counter("com.pp.counter.invoke");

    public static void main(String[] args) {
        MyConsoleReport.startReport();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    exec.inc();
                    milliSecondSleep(new Random().nextInt(500) * 2);
                }
            }
        }).start();
        secondSleep(3);
    }
}
