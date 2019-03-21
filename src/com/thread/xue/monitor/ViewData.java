package com.thread.xue.monitor;

import java.util.Vector;

public class ViewData {
	public Object lock = new Object();

	public void viewData(String data) {
		 synchronized(lock){
		 for(int i=0;i<10;i++)
		 System.out.println(data);
		
		 }
	}
	
	public static void main(String[] args) {
		Vector v = null;
	}
}