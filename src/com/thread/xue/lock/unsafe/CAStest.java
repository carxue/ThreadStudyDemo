package com.thread.xue.lock.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * Unsafe实现原子操作
 * 参考https://www.cnblogs.com/mickole/articles/3757278.html
 *
 */
public class CAStest {
//	private final static Unsafe unsafe = Unsafe.getUnsafe();
	public static void main(String[] args) {
		Account account = new Account("xk",100);
		Class<?> clazz = account.getClass();
		Field field = null;
		Field shcoolField = null;
		try {
			field = clazz.getDeclaredField("amount");
			field.setAccessible(true);
			
			shcoolField = clazz.getDeclaredField("shcool");
			shcoolField.setAccessible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Unsafe unsafe = null;
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe"); //Internal reference  
			f.setAccessible(true);  
			unsafe = (Unsafe) f.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		long offset = unsafe.objectFieldOffset(field);
		System.out.println("用户账户金额的偏移量："+offset+"值为:"+account.getAmount());
		boolean modifyResult = unsafe.compareAndSwapInt(account, offset, 100, 110);
		System.out.println(modifyResult+"修改后的金额为:"+account.getAmount());
		
		long schooloffset = unsafe.arrayBaseOffset(clazz);
		System.out.println("小学偏移量:"+schooloffset);
		boolean modifyschoolResult = unsafe.compareAndSwapObject(account, schooloffset, "小学", "1小学1");
		System.out.println(modifyschoolResult+":"+account.getShcool()[0]);
		
		
	}

}

class Account {
	private String name;
	private int amount;
	private String[] shcool = {"小学","初中","高中","大学"};

	public Account(String name, int amount) {
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String[] getShcool() {
		return shcool;
	}

	public void setShcool(String[] shcool) {
		this.shcool = shcool;
	}

}