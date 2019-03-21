package com.thread.xue.clientsyc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListHelper<E> {
	public List<E> list = Collections.synchronizedList(new ArrayList<E>());
	public synchronized boolean putIfAbsent(E x){
		boolean absent = !list.contains(x);
		if(absent)
			list.add(x);
		return absent;
	}
	public synchronized boolean putIfAbsent1(E x){
		boolean absent = !list.contains(x);
		if(absent)
			list.add(x);
		return absent;
	}
	
	public static void main(String[] args) throws InterruptedException {
		ListHelper<String> lh = new ListHelper<String>();
		for(int i=0;i<10;i++){
			new Thread(){
				public void run() {
					for(int j=0;j<10;j++){
						lh.putIfAbsent("xuekui" + j);
						lh.putIfAbsent1("xuekui" + j);
					}
					
				};
			}.start();
		} 
		
//		Thread.sleep(2000);
		for(int i =0;i<lh.list.size();i++)
			System.out.println(lh.list.get(i));
		
		System.out.println(lh.list.size());
		
	}
			
}
