package com.ims.base.collections;

public class ArrayBasedQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayBasedQueue<Integer> queue = new ArrayBasedQueue<>(5);

		queue.push(0);
		queue.display();
		queue.pop();
		queue.display();

		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);
		queue.push(5);
		queue.display();

		queue.push(6);

		 queue.pop();
		queue.display();

		queue.push(7);
		queue.push(8);
		queue.display();

		queue.pop();
		queue.pop();
		queue.display();

		queue.push(8);
		queue.display();

		queue.pop();
		queue.pop();
		queue.pop();
		queue.display();

		queue.pop();
		queue.display();
	}
}
