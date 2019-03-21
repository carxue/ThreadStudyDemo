package com.thread.xue.eight8.uncaught;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 为捕获异常处理器，线程run抛出的异常是无法通过try捕获的可以通过设置异常处理器来捕获做特殊处理比如日志
 * 线程池要想捕获异常必须通过execute提交线程任务不然无法捕获异常，通过在run方法中设置Thread.currentThread().setUncaughtExceptionHandler(new UncaughtException());
 * 来捕获异常，如果通过submit提交则异常被封装到future的get方法中包装
 *
 */
public class UnCaughtHandlerTest {
	public static void main(String[] args) throws InterruptedException{
//		try {
//			Thread thread = new Thread(new Task());
//			thread.start();
//		} catch (Exception e) {
//			System.out.println("===========Exception: " + e.getMessage());
//		}
		ThreadPoolExecutor tPool = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(5));
//		tPool.execute(new Task());
		Future<?> f = tPool.submit(new Task());
		try {
			f.get();
		} catch (ExecutionException e) {
			System.out.println("eeeeee"+e.getMessage());
		}
		tPool.shutdown();
		
		
	}
}

class Task implements Runnable {
	@Override
	public void run() {
		Thread.currentThread().setUncaughtExceptionHandler(new UncaughtException());
		System.out.println(3 / 2);
		System.out.println(3 / 0);
		System.out.println(3 / 1);
	}
}

class UncaughtException implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("异常捕获"+e.getMessage());
	}
	
}
