package com.concurrent.synchronize;

/**
 * Created by szm on 2016/10/6.
 */
public class SynchronizeDemo {
	public void method() {
		synchronized (this) {
			System.out.println("method1 start");
			synchronized (this) {
				System.out.println("method1 start1");
			}
		}

	}

	public static void main(String[] args) {
		final SynchronizeDemo sd = new SynchronizeDemo();
		new Thread(new Runnable() {
			public void run() {
				sd.method();
			}
		}).start();
		System.out.println(111);
	}
}
