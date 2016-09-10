package com.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by songzhimao on 16-2-18.
 */
public class AtomicBooleanTest {
    public static void main(String[] args) {
        Thread t2 = new Thread(new BarWorker("bb"));
        Thread t1 = new Thread(new BarWorker("aa"));
        t2.run();
        t1.run();
    }
    private static class BarWorker implements Runnable {

        private static AtomicBoolean exists = new AtomicBoolean(false);

        private String name;

        public BarWorker(String name) {
            this.name = name;
        }

        public void run() {
            if (exists.compareAndSet(false, true)) {  //当第一个线程设置为true后，另外的线程是进不来的

                System.out.println(name + " enter"+"currentvalue="+exists.get());
                try {
                    System.out.println(name + " working");
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    // do nothing
                }
                System.out.println(name + " leave");
                exists.set(false);
            } else {
                System.out.println(name + " give up");
            }
        }

    }
}
