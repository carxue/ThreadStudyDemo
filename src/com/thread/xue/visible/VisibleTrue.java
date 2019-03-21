package com.thread.xue.visible;

public class VisibleTrue{
	public void doSomething(){
		boolean  stop = false;
		while(!stop){
//			for(int i=0;i<5;i++)
				System.out.println(Thread.currentThread().getId()+":hello");
		}
		stop = true;
	}
	
	public static void main(String[] args) {
		VisibleTrue v = new VisibleTrue();
		for(int i=0;i<1;i++){
			VisibleTureThread vt = new VisibleTureThread(v);
			vt.start();
		}
	}
}
