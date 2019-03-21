package com.thread.xue.eight8.beforeexecute;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * beforeExecute、afterExecute方法在线程任务执行前和后无论是否异常都会进行的操作可以做日志记录，
 * 如果beforeExecute抛异常后面的都不会执行，受保护的方法需要继承重写
 *
 */
public class BeforePoolTest extends ThreadPoolExecutor{
	
	private static final Logger logger = Logger.getLogger("BeforePoolTest");

	public BeforePoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	@Override
	protected void beforeExecute(Thread t, Runnable r) {System.out.println(3 / 0);
		logger.info("before方法执行前"+r.toString());
	}
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		logger.info("after方法执行后");
	}
	@Override
	protected void terminated() {
		logger.info("terminated执行完毕");
	}
	
	public static void main(String[] args) {
		Task t = new Task();
		System.out.println(t.toString());
		BeforePoolTest beforePool = new BeforePoolTest(5, 10, 1000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(5));
		beforePool.execute(t);
		beforePool.shutdown();
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
