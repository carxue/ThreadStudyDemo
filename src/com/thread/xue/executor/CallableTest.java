package com.thread.xue.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *测试ExecutorService执行任务Callable返回生产的结果Future，
 *shutdownNow关闭ExecutorService将停止接收新的任务和线程池中还在等待执行的任务
 *正真执行的任务如果执行了wait sleep 也可以被关闭，如果是正常执行者不能强制关闭会等待
 *该任务执行完毕，他和shutdown一样通过调用Thread的interrupt来中断线程任务
 *ExecutorService必须显示的调用shutdown不然会在jvm中一直执行
 */
public class CallableTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(100);
		Callable<Long> a = new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				System.out.println("a is starting");
				long ret = MyTask.a();
				System.out.println("a will be ending");
				return ret;
			}

		};
		Callable<Long> b = new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				System.out.println("b is starting");
				long ret = MyTask.b();
				System.out.println("b will be ending");
				return ret;
			}

		};
		Callable<Long> c = new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				System.out.println("c is starting");
				long ret = MyTask.c();
//				Thread.currentThread().interrupt();
				System.out.println("c will be ending");
				return ret;
			}

		};
		LinkedBlockingQueue<Callable<Long>> blockqueue = new LinkedBlockingQueue<Callable<Long>>(10);
		blockqueue.offer(a);blockqueue.offer(c);blockqueue.offer(b);
		
		List<Callable<Long>> list = new ArrayList<Callable<Long>>();
		list.add(a);list.add(b);list.add(c);
		List<Future<Long>> listFuture = executor.invokeAll(blockqueue,1,TimeUnit.MILLISECONDS);
		for(Future f:listFuture){
			System.out.println(f.get()+"任务是否执行完毕:"+f.isDone());
		}
		Thread.currentThread().interrupt();
		System.out.println("取消第二个任务是否成功"+listFuture.get(2).cancel(true));
		System.out.println("over");
		executor.shutdownNow();
		
//		Future<Long> future = executor.submit(a);
//		System.out.println("a任务是否执行完毕:"+future.isDone());
//		executor.shutdownNow();
//		System.out.println("a任务调度的结果为:"+future.get());
		
		
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

	public static long b() {
		long ret = 0;
		for (int i = 0; i < Integer.MAX_VALUE/2; i++) {
			ret += i;
		}
		return ret;
	}

	public static long c() {
		long ret = 0;
		for (int i = 0; i < Integer.MAX_VALUE/3; i++) {
			ret += i;
		}
		return ret;
	}
}
