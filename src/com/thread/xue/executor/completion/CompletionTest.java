package com.thread.xue.executor.completion;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * CompletionService中通过future和LinkedBlockingQueue实现，当future状态为完成时才会放入
 * 到LinkedBlockingQueue中，当取future时如果队列为空则会阻塞直到有future放入，这样就不会做无谓的
 * 等等提高效率
 *
 */
public class CompletionTest {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(100);
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
		Callable<String> c = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("c is starting");
				long ret = MyTask.c();
				Thread.currentThread().interrupt();
				System.out.println("c will be ending");
				return "c==="+ret;
			}

		};

		CompletionService<String> com = new ExecutorCompletionService<String>(executor);
		com.submit(b);
		com.submit(c);
		com.submit(a);
		for (int i = 0; i < 3; i++) {
			Future<String> fu;
			try {
				fu = com.take();
				System.out.println(fu.get());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		executor.shutdown();

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