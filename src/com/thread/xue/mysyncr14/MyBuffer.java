package com.thread.xue.mysyncr14;

public class MyBuffer<V> {
	private final V[] item;
	private int tail;
	private int head;
	private int count;

	@SuppressWarnings("unchecked")
	protected MyBuffer(int len) {
		this.item = (V[]) new Object[len];
	}
	
	protected synchronized final void doput(V v){
		item[tail] = v;
		if(++tail == item.length)
			tail = 0;
		++count;
	}
	
	protected synchronized final V dotake(){
		V v = item[head];
		item[head] = null;
		if(++head==item.length)
			head = 0;
		--count;
		return v;
	}
	
	public synchronized final boolean isFull(){
		return count == item.length;
	}
	
	public synchronized final boolean isEmpty(){
		return count == 0;
	}
	
}
