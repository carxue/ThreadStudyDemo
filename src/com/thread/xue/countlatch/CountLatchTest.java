package com.thread.xue.countlatch;

import java.util.concurrent.CountDownLatch;

public class CountLatchTest {
	
	
	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(10);
		Account a = new Account("carlxue",10d);
		for(int i=0;i<10;i++){
			Shopping s= new Shopping(a,10d,"huawei"+i);
			s.setStartGate(startGate);
			s.setEndGate(endGate);
			s.start();
		}
		long start = System.nanoTime();
		startGate.countDown();
		endGate.await();
		long end = System.nanoTime();
		System.out.println(end-start);
	}
}
