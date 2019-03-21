package com.thread.xue.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
	public static void main(String[] args) {
		final ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 20; i++) {
			Runnable run = new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
					System.out.println("newFixedThreadPool最大线程数测试");
				}
			};
			executor.execute(run);
		}
		executor.shutdown();
	}
}
