package com.thread.xue.lock.trylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest {

	public static void main(String[] args) {
		Account account = new Account("xk",100d);
		Lock lock = new ReentrantLock();
		for(int i=0;i<5;i++){
			ATMThread atm = new ATMThread(account, 50+i,lock,"thread:"+i);
			atm.start();
		}
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
			flag = lock.tryLock(1,TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MyTask1.a();
		try{
			double amount = account.getAmount();
			if (amount >= price){
				account.setAmount(amount - price);
				System.out.println(this.name+"剩余额度:" + account.getAmount()+"购买成功");
			}else
				System.out.println(this.name+"剩余额度:" + account.getAmount()+"购买失败!!!!");
		}finally{
			if(flag)
				lock.unlock();
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

