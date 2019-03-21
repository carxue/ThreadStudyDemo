package com.thread.xue.visible;

public class VisibleTureThread extends Thread {
	
	private volatile VisibleTrue v;
	
	public VisibleTureThread(VisibleTrue v) {
		this.v = v;
	}
	@Override
	public void run() {
		v.doSomething();
	}
}
