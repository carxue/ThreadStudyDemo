package com.thread.xue.eight8;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程的饱和策略AbortPolicy放弃抛异常、DiscardPolicy放弃不抛异常、
 * DiscardOldestPolicy放弃队列最前面的然后重新尝试执行任务(重复这个过程)、
 * CallerRunsPolicy调用该runnable的run方法直接执行该任务不在走线程池处理
 * corePoolSize:线程池保存的最低线程数
 * maximumPoolSize:线程池可以创建的最大线程数，当core核心线程数和队列保存都使用完时有新任务则创建新的线程数不错过max值
 * keepAliveTime:超过core核心数的线程如果没有任务执行需空闲最多保存多久销毁
 * handler:超过max和队列总和而是被阻塞时所使用的处理程序
 */
public class ThreadPoolExecutorTest {

	public static void main(String[] args) {
		ThreadPoolExecutor tPool = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS,
				new LinkedBlockingDeque<>(5));
		//饱和策略
		tPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		for (int i = 0; i < 20; i++) {
			MyCall1 call = new MyCall1();
			call.setI(i);
			try {
				tPool.submit(call);
			} catch (Exception e) {
				System.out.println("======"+e.getMessage());
			}
		}
		tPool.shutdown();
		

	}

}

class MyCall1 implements Callable<String> {
	private int i;

	public void setI(int i) {
		this.i = i;
	}

	@Override
	public String call() throws Exception {
		long ret = MyTask1.a();
		System.out.println(i + " will be ending");
		return i + ":===" + ret;
	}
}

class MyTask1 {
	public static long a() {
		long ret = 0;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			ret += i;
		}
		return ret;
	}

	public static long b() {
		long ret = 0;
		for (int i = 0; i < Integer.MAX_VALUE / 2; i++) {
			ret += i;
		}
		return ret;
	}

	public static long c() {
		long ret = 0;
		for (int i = 0; i < Integer.MAX_VALUE / 3; i++) {
			ret += i;
		}
		return ret;
	}
}