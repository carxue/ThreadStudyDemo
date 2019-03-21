package com.thread.xue.atomic15.renference;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class RenferenceTest {
	public static void main(String[] args) throws InterruptedException {
		Person tom = new Person("tom",10);
		Person jack = new Person("jack",12);
		Person lucy = new Person("lucy",18);
		AtomicReference<Person> atomic = new AtomicReference<Person>(tom);
		System.out.println(atomic.get());
		CountDownLatch latch = new CountDownLatch(2);
		
		Thread t1 = new Thread(){
			@Override
			public void run() {
				atomic.compareAndSet(tom, jack);
				latch.countDown();
			}
		};
		Thread t2 = new Thread(){
			@Override
			public void run() {
				atomic.compareAndSet(tom, lucy);
				latch.countDown();
			}
		};
		t1.start();t2.start();
		latch.await();
		System.out.println(atomic.get());
		
	}
}

