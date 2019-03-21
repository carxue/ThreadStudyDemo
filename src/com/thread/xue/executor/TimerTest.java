package com.thread.xue.executor;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	
	/**
	 * 异常会导致timer任务终止
	 */
	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		timer.schedule(new ThrowTask(), 1);
		Thread.sleep(1);
		timer.schedule(new ThrowTask(), 1);
		Thread.sleep(5);
	}
	
	static class ThrowTask extends TimerTask{
		@Override
		public void run() {
//			System.out.println("------");
			throw new RuntimeException();
		}
	}
}
