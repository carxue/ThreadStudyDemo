package com.thread.xue.seven.cancel;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CancelTest {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<BigInteger>();
		BrokenPrimeProducer c = new BrokenPrimeProducer(primes);
		c.start();
		while (true) {
			
		}
	}
}

class BrokenPrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;
	private volatile boolean cancelled = false;

	public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		BigInteger b = BigInteger.ONE;
		while(!cancelled)
			try {
				queue.put(b=b.nextProbablePrime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	public void cancel(){cancelled=true;}
}