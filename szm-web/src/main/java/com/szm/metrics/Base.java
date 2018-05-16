package com.szm.metrics;

import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

/**
 * @author songzhimao
 * @date 2017/11/8
 */
public class Base {
    protected static MetricRegistry metric = MetricConstant.REGISTER;

    protected static void secondSleep(long value) {
        try {
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected static void milliSecondSleep(long value) {
        try {
            TimeUnit.MILLISECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
