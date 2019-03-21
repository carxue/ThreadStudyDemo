package com.thread.xue.atomic;

public class IncrementThread extends Thread{
	private Increment increment;
	public IncrementThread(Increment increment){
		this.increment = increment;
	}
	
	@Override
	public void run() {//increment.getAtomicCount()
		for(int i=0;i<5;i++)
			System.out.println(Thread.currentThread().getId()+" ＝＝＝　"+increment.getCount());
	}

}
