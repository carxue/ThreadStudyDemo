package com.thread.xue.eight8;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * 抛异常策略，测试丢消息，策略异常未捕获异常无法处理，不是runnable抛出，
 *
 */
public class DiscardMessageTest {
	public static void main(String[] args) {
		singleCallable();
	}

	public static void singleCallable() {
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 0, TimeUnit.MILLISECONDS,
//				new LinkedBlockingQueue<Runnable>(3));
		ExecutorService executor = Executors.newScheduledThreadPool(5);
//		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		for (int i = 0; i < 10; i++) {
			MyCall call = new MyCall();
			call.setI(i);
			try{executor.execute(call);}catch(RejectedExecutionException e){System.out.println(e.getMessage());}
		}
		executor.shutdown();
	}
}

class MyCall implements Runnable {
	private int i;

	public void setI(int i) {
		this.i = i;
	}

	@Override
	public void run(){
		Thread.currentThread().setUncaughtExceptionHandler(new UncaughtException());
		long ret = MyTask.a();
		System.out.println(i + " will be ending"+ret);
	}
}

class MyTask {
	public static long a() {
		long ret = 0;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			ret += i;
		}
		return ret;
	}

}

class UncaughtException implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("异常捕获"+e.getMessage());
	}
	
}