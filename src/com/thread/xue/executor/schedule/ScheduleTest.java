package com.thread.xue.executor.schedule;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *用于测试ScheduledExecutorService的定时任务调度
 */
public class ScheduleTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService es = Executors.newScheduledThreadPool(10);
		Callable<String> a = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("a is starting");
				long ret = MyTask.a();
				System.out.println("a will be ending");
				return "a==="+ret;
			}

		};
		Callable<String> b = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("b is starting");
				long ret = MyTask.b();
				System.out.println("b will be ending");
				return "b==="+ret;
			}

		};
		//10秒钟后执行
		Future<String> fa = es.schedule(a, 0, TimeUnit.SECONDS);
		Future<String> fb = es.schedule(b, 0, TimeUnit.SECONDS);
		
		String reta = null;
		try {
			reta = fa.get(2, TimeUnit.SECONDS);
			
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		System.out.println("fa====:"+reta);
		String retb = null;
		try {
			retb = fb.get(3, TimeUnit.SECONDS);
			
		} catch (TimeoutException e) {
			e.printStackTrace();
			System.out.println("任务超时取消："+fb.cancel(true));
		}
		System.out.println("fb====:"+retb);
		
		List<Runnable> list = es.shutdownNow();//强行关闭,测试是需要停掉阻塞方法get
		System.out.println(list.size());
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