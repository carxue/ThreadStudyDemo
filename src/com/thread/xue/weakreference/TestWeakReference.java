package com.thread.xue.weakreference;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class TestWeakReference {
	public static Map<Integer,Car> m = new HashMap<Integer,Car>();
	
	public static void main(String[] args) {
		
		for(int i=0;i<Integer.MAX_VALUE;i++){
			Car car = new Car(22000, "silver");
			System.out.println(car.toString());
			m.put(i, car);
		}
//		weakTest1();
//		weakTest2();
		
		
	}

	public static void weakTest1() {

		Car car = new Car(22000, "silver");
		WeakReference<Car> weakCar = new WeakReference<Car>(car);
		int i = 0;
		car = null;
		while (true) {
			if (weakCar.get() != null) {
				i++;
				System.out.println("Object is alive for " + i + " loops - " + weakCar);
			} else {
				System.out.println("Object has been collected.");
				break;
			}
		}

	}

	public static void weakTest2() {
		Car a = new Car(22000, "silver");
		a.str = "Hello, reference";
		WeakReference<Car> weak = new WeakReference<Car>(a);
		a = null;
		int i = 0;
		while (weak.get() != null) {
			System.out
					.println(String.format("Get str from object of WeakReference: %s, count: %d", weak.get().str, ++i));
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
