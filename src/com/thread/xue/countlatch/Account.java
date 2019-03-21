package com.thread.xue.countlatch;

public class Account {
	private Double money;
	private String name;

	public Account( String name,Double money) {
		super();
		this.money = money;
		this.name = name;
	}
	
	public boolean pay(Double price) throws InterruptedException{
		if(this.money>=price){
			Thread.sleep(100);
			this.money=this.money-price;
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}
