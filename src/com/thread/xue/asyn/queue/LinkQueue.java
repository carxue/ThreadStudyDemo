package com.thread.xue.asyn.queue;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkQueue {
	public static void linkQueuetest() throws InterruptedException {
		LinkedBlockingQueue<String> list = new LinkedBlockingQueue<String>(2);
		try {
			list.put("xuekui");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list.add("haha");
		System.out.println("移除："+list.take());
		for (String element : list)
			System.out.println(element);
		
		System.out.println((new Integer(2)).hashCode()^(new Integer(3)).hashCode());
	}

	public static void main(String[] args) throws InterruptedException {
		linkQueuetest();
	}
}
