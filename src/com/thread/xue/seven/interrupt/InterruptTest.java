package com.thread.xue.seven.interrupt;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 线程中断
 *
 */
public class InterruptTest {
	public static void main(String[] args) {
		BlockingQueue<BigInteger> queue = new LinkedBlockingQueue<>();
		PrimeProducer pp = new PrimeProducer(queue);
		pp.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(pp.getQueue().size());
		pp.cancle();
	}

}

class PrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;
	

	public BlockingQueue<BigInteger> getQueue() {
		return queue;
	}

	public PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		BigInteger p = BigInteger.ONE;
		while (!Thread.currentThread().isInterrupted())
			try {
				queue.put(p = p.nextProbablePrime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public void cancle() {
		interrupt();
	}
}