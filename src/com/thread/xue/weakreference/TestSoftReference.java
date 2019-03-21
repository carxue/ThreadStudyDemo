package com.thread.xue.weakreference;

import java.lang.ref.SoftReference;

public class TestSoftReference {
	public static void main(String[] args) {
		Car a = new Car(22000, "silver");
		a.str = "Hello, reference";
		SoftReference<Car> sr = new SoftReference<Car>(a);
		a = null;
		int i = 0;
		while (sr.get() != null) {
			System.out.println(String.format("Get str from object of SoftReference: %s, count: %d", sr.get().str, ++i));
			if (i % 10 == 0) {
				System.gc();
				System.out.println("System.gc() was invoked!");
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

			}
		}
		System.out.println("object a was cleared by JVM!");
	}
}
