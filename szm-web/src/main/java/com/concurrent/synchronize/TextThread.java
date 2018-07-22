package com.concurrent.synchronize;

/**
 * Created by szm on 2016/10/5.
 */
public class TextThread {
	public static void main(String[] args) {
		final ServiceWorkerThread sw = new ServiceWorkerThread();
		new Thread(sw, "t1").start();
		new Thread(sw, "t2").start();
		new Thread(sw, "t3").start();
		new Thread(sw, "t4").start();
		;
	}
}

class ServiceWorkerThread implements Runnable {
	private int num = 100;
	private String lock = new String();

	public void run() {
		while (num > 0) {
			synchronized (lock) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" thread " + " this is num:" + num--);
				if (num == 0) {
					Thread innterThread = new Thread(new Runnable() {
						int count = 100;
						public void run() {
							while (count > 0) {
								System.out.println("enter inner class"+Thread.currentThread().getName());
								synchronized (lock) {
									System.out.println(Thread.currentThread().getName()+" count:"+count--);
								}
							}
						}
					}, "t5") ;
					innterThread.start();
					try {
						innterThread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public String getLock() {
		return lock;
	}
}