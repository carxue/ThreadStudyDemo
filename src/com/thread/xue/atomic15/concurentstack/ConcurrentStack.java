package com.thread.xue.atomic15.concurentstack;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import com.thread.xue.atomic15.bean.Person;

/**
 * Treiber算法
 * 实现非阻塞的栈,LIFO后进先出
 * atomic中始终保存最新插入的一个Node节点
 * 
 * @param <E>
 */
public class ConcurrentStack<E> {
	private final static AtomicInteger atomicSize = new AtomicInteger(0);
	private AtomicReference<Node<E>> atomic = new AtomicReference<ConcurrentStack.Node<E>>();

	public void push(E item) {
		Node<E> newHead = new Node<E>(item);
		Node<E> oldHead;
		do {
			oldHead = atomic.get();
			newHead.next = oldHead;
		} while (!atomic.compareAndSet(oldHead, newHead));
		atomicSize.getAndIncrement();
	}

	public E pop() {
		Node<E> oldHead;
		Node<E> newHead;
		do {
			oldHead = atomic.get();
			if (oldHead == null)
				return null;
			newHead = oldHead.next;
		} while (!atomic.compareAndSet(oldHead, newHead));
		atomicSize.decrementAndGet();
		return oldHead.item;
	}

	public int size() {
		return atomicSize.get();
	}

	private static class Node<E> {
		public final E item;
		public Node<E> next;

		public Node(E item) {
			this.item = item;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ConcurrentStack<Person> concurrentStack = new ConcurrentStack<Person>();
		int size = 1000;
		CountDownLatch latch = new CountDownLatch(size);
		for (int i = 0; i < size; i++) {
			Person person = new Person("carl" + i, i);
			new Thread() {
				public void run() {
					concurrentStack.push(person);
					latch.countDown();
				};
			}.start();
		}
		latch.await();
		System.out.println(concurrentStack.size());
		for (int i = 0; i < size + 1; i++) {
			System.out.println(concurrentStack.pop());
		}
		System.out.println(concurrentStack.size());
		
		System.out.println("=============================================================");
		
		for (int i = 0; i < size; i++) {
			Person person = new Person("carl" + i, i);
			concurrentStack.push(person);
		}
		
		for (int i = 0; i < size + 1; i++) {
			System.out.println(concurrentStack.pop());
		}
		
	}

}
