package com.concurrent.visiblity;

import sun.awt.windows.ThemeReader;

/**
 * Created by szm on 2016/10/5.
 */
public class VisibilityTest {
	private static boolean read;
	private static int number;

	private static class ReadThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!read) {
				System.out.println(read);
			}
			System.out.println(number);
		}
	}

	private static class WriterThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			read = true;
			number = 100;
		}
	}
	public static void main(String[] args) {
		new ReadThread().start();
		new WriterThread().start();
	}
}
