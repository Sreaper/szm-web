package com.szm.metrics;

import com.codahale.metrics.Meter;

import java.util.Random;

/**
 * @author songzhimao
 * @date 2017/11/8
 */
public class MeterTest extends Base
{
    static final Meter requests = metric.meter("com.pp.meter.invoke");
    public static void main(String[] args)
    {
        MyConsoleReport.startReport();
        new Thread(()->{
            for(int i=1;i<=2;i++)
            {
                requests.mark();
                milliSecondSleep(new Random().nextInt(500)*2);
            }
        }).start();
        secondSleep(2);
    }
}
