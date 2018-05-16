package com.szm.metrics;

import com.codahale.metrics.ConsoleReporter;

import java.util.concurrent.TimeUnit;

/**
 * @author songzhimao
 * @date 2017/11/8
 */
public class MyConsoleReport
{
    public static void startReport()
    {
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(MetricConstant.REGISTER)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.SECONDS)
                .build();
        //一秒钟执行一次
        reporter.start(1, TimeUnit.SECONDS);
    }
}
