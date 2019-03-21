package com.thread.xue.countlatch;

import java.util.concurrent.CountDownLatch;

public class Shopping extends Thread {
	
	private Account account;
	private Double price;
	private String goodsName;
	private CountDownLatch startGate;
	private CountDownLatch endGate;
	
	public Shopping(Account account,Double price,String goodsName) {
		this.account = account;
		this.price = price;
		this.goodsName = goodsName;
		
	}

	@Override
	public void run() {
		try {
			startGate.await();
			if(account.pay(this.price))
				System.out.println("====支付成功====="+account.getName()+":购买了"+this.goodsName+"商品，总价："+this.price+"￥,账号余额:"+account.getMoney());
			else
				System.out.println("====支付失败"+account.getName()+":购买了"+this.goodsName+"商品，总价："+this.price+"￥,账号余额:"+account.getMoney());
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			endGate.countDown();
		}
	}

	public CountDownLatch getStartGate() {
		return startGate;
	}

	public void setStartGate(CountDownLatch startGate) {
		this.startGate = startGate;
	}

	public CountDownLatch getEndGate() {
		return endGate;
	}

	public void setEndGate(CountDownLatch endGate) {
		this.endGate = endGate;
	}
	
}
