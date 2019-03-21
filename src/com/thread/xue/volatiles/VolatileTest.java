package com.thread.xue.volatiles;

import java.util.concurrent.CountDownLatch;

/**
 * count++ 不是原子性操作所以volatile不能保证同步
 *
 */
public class VolatileTest {
	public volatile Integer count=0;

	public static void main(String[] args) throws InterruptedException {
		int length = 10000;
		CountDownLatch latch = new CountDownLatch(length);
		VolatileTest test = new VolatileTest();
		System.out.println("======"+test.count);
		for(int i=0;i<length;i++){
			MyThread thread = new MyThread(test,latch);
			thread.start();
		}
		latch.await();
		System.out.println("###########"+test.count);
	}
}

class MyThread extends Thread {
	private VolatileTest vo;
	private CountDownLatch latch;

	public MyThread(VolatileTest vo,CountDownLatch latch) {
		this.vo = vo;
		this.latch=latch;
	}

	@Override
	public void run() {
		vo.count++;
		latch.countDown();
	}
}