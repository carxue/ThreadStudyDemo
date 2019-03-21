package com.thread.xue.novisible;

public class NoVisibibly {
	private static boolean flag;
	private static int a;
	private static class MyThread extends Thread{
		@Override
		public void run() {
			while(!flag)
				Thread.yield();
			System.out.println(flag+":"+a);
		}
	}
	public static void main(String[] args) {
		new MyThread().start();
		int c=1,b=1;
		synchronized (args) {
			for(int i=0;i<10000;i++)
				c=c+b;
			System.out.println(c);
		}
		a=40;
		flag=true;
		
	}
 
}
