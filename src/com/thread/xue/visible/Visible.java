package com.thread.xue.visible;

public class Visible extends Thread{
	@Override
	public void run() {
		String name = this.getName()+":";
		int i=0;
		i=10;
		System.out.println(name+this.isAlive());
		int j=i;
		System.out.println(name+j);
		System.out.println(name+this.isAlive());
	}
	public static void main(String[] args) {
		Visible v1 = new Visible();System.out.println("v1"+v1.isAlive());
		Visible v2 = new Visible();System.out.println("v2"+v1.isAlive());
		v1.start();
		v2.start();
		System.out.println("v1"+v1.isAlive());
		System.out.println("v2"+v1.isAlive());
	}
}
