package com.szm.metrics;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Reservoir;
import com.codahale.metrics.SlidingTimeWindowReservoir;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author songzhimao
 * @date 2017/11/8
 */
public class HistogramTest extends Base {
//    static final Histogram his = metric.histogram("com.pp.histogram.score");
    static List<Integer> scores = Arrays.asList(60, 75, 80, 62, 90, 42, 33, 95, 61, 73);

    public static void main(String[] args) {
        metric.register(MetricRegistry.name("ss", "TotalResults"),
                new Histogram(new SlidingTimeWindowReservoir(1, TimeUnit.MINUTES)));
        Histogram his1 = metric.histogram("ss.TotalResults");
        MyConsoleReport.startReport();
        new Thread(() -> {
            for(int i=0;i<1000;i++) {
                scores.forEach((score) -> {
                    his1.update(score);
                    milliSecondSleep(new Random().nextInt(500) * 2);
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        secondSleep(10);
    }
}
