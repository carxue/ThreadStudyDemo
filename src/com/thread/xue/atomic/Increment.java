package com.thread.xue.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Increment {
	private final AtomicInteger l = new AtomicInteger(0);
	private Integer i = new Integer(1);
	
	public Integer getAtomicCount(){
		return l.incrementAndGet();
	}
	
	public synchronized Integer getCount(){
		return i++;
	}
	
	public static void main(String[] args) {
		Increment in = new Increment();System.out.println(in.hashCode());
		for(int i=0;i<2;i++){
			IncrementThread t = new IncrementThread(in);
			t.start();
		}
	}
}
