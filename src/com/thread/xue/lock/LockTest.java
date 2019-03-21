package com.thread.xue.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock锁测试，1000个并发速度比较慢
 *
 */
public class LockTest {
	public static void main(String[] args) {
		Account account = new Account("xk",100d);
		Lock lock = new ReentrantLock();
		ATMThread atm1 = new ATMThread(account, 80,lock,"first");
		atm1.start();
		for(int i=0;i<5;i++){
			ATMThread atm = new ATMThread(account, 50+i,lock,"thread:"+i);
			atm.start();
		}
		atm1.interrupt();
		
		
	}
}

class ATMThread extends Thread {
	private Account account;
	private double price;
	private String name;
	private final Lock lock;

	public ATMThread(Account account, double price,Lock lock,String name) {
		this.account = account;
		this.price = price;
		this.lock = lock;
		this.name = name;
	}

	@Override
	public void run() {
		boolean flag = false;
		try {
			flag = lock.tryLock(2,TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(flag){
			MyTask1.a();
			try{
				double amount = account.getAmount();
				if (amount >= price){
					account.setAmount(amount - price);
					System.out.println(this.name+"剩余额度:" + account.getAmount()+"购买成功");
				}else
					System.out.println(this.getName()+"剩余额度:" + account.getAmount()+"购买失败!!!");
			}finally{
				if(flag)
				lock.unlock();
			}
		}else{
			System.out.println("获取锁失败");
		}
	}
}

class Account {
	private String name;
	private double amount;

	public Account(String name, double amount) {
		super();
		this.name = name;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}

class MyTask1 {
	public static long a() {
		long ret = 0;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			ret += i;
		}
		return ret;
	}
}
