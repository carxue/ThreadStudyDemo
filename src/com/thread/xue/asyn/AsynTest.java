package com.thread.xue.asyn;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AsynTest {
	public static void main(String[] args) {

		Map<String, SyncroObject> map = new ConcurrentHashMap<String, SyncroObject>();
		SyncroObject so1 = new SyncroObject("小明",23);
		SyncroObject so2 = new SyncroObject("小红",56);
		map.put(so1.getName(), so1);
		map.put(so2.getName(), so2);
		map.put("aa", null);
		
		Map<String, SyncroObject> maps = Collections.unmodifiableMap(map);
		SyncroObject aa = maps.get("小明");
		System.out.println(aa.getName()+":"+aa.getAge());
		aa.setAge(100);aa.setName("哈哈");
		SyncroObject bb = maps.get("小明");
		System.out.println(bb.getName()+":"+bb.getAge());
		
		
		
	}

	private static void printInfo(int num) {
		System.out.println(Integer.toBinaryString(num));
	}

	public static void asyn() {
		int i = 0;
		int j = 0;
		System.out.println("i走你" + i);
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("内部i" + i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();
		System.out.println("j走你" + j);
		new Thread() {
			@Override
			public void run() {
				for (int j = 0; j < 100; j++) {
					System.out.println("内部j" + j);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();
		System.out.println("结束");
	}
}
