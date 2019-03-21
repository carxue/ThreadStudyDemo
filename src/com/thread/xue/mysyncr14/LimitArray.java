package com.thread.xue.mysyncr14;

public class LimitArray extends MyBuffer<String>{

	public LimitArray(int size) {
		super(size);
	}
	
	public synchronized void put(String v) throws Exception{
		if(isFull())
			throw new Exception("Array is full");
		doput(v);
	}
	
	public synchronized String take() throws Exception{
		if(isEmpty())
			throw new Exception("Array is empty");
		return dotake();
	}

}
