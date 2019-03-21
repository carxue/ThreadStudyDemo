package com.thread.xue.seven.interrupt;

public class Interrupt1 {
	public static void main(String[] args) throws InterruptedException {
		MyThread2 t2 = new MyThread2();
		MyThread1 t1 = new MyThread1(t2);
		t2.start();t1.start();
	}
}

class MyThread1 extends Thread{
	private Thread otherThread;
	
	public MyThread1(Thread otherThread) {
		this.otherThread = otherThread;
	}

	@Override
	public void run() {
		otherThread.interrupt();
		System.out.println("MyThread1开始计算");
		long ret = 0;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			ret += i;
		}
		System.out.println("MyThread1计算结果:"+ret);
	}
}

class MyThread2 extends Thread{
	@Override
	public void run() {
		System.out.println("MyThread2开始计算");
		long ret = 0;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			ret += i;
		}
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("MyThread2技术结果:"+ret);
	}
}