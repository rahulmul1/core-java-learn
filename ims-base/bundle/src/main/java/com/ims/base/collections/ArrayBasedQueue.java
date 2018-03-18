package com.ims.base.collections;

public class ArrayBasedQueue<T> {

	private T arr[];
	int capacity;
	int front = -1;
	int rear = -1;

	@SuppressWarnings("unchecked")
	public ArrayBasedQueue(int capacity) {
		this.capacity = capacity;
		arr = (T[]) new Object[this.capacity];

	}

	public void push(T e) {

		if ((front == 0 && rear == capacity - 1) || front == rear + 1) {
			System.out.println("que is full, cannot push element = " + e);
		} else {
			if (rear == -1) {
				front = 0;
				rear = 0;
			} else if (rear == this.capacity - 1) {
				rear = 0;
			} else {
				rear++;
			}
			arr[rear] = e;
			System.out.println("Pushed elemeent = " + arr[rear]);
		}
	}

	public T pop() {
		T e = null;
		if (front == -1) {
			System.out.println("que empty");
		} else {
			e = arr[front];
			System.out.println("Popped element = " + e);
			if (front == rear) {
				front = -1;
				rear = -1;
			} else if (front == capacity - 1) {
				front = 0;
			} else {
				front++;
			}
		}
		return e;
	}

	public void display() {

		if (rear == -1) {
			System.out.println("Que is empty and nothing to display");
			System.out.print("front = " + front);
			System.out.print(",rear = " + rear);
			System.out.println();
		} else {
			System.out.print("que=[");
			int i = front;
			if (front <= rear) {
				while (i <= rear) {
					System.out.print(arr[i] + ",");
					i++;
				}
			} else {
				while (i < capacity) {
					System.out.print(arr[i] + ",");
					i++;
				}
				i = 0;
				while (i <= rear) {
					System.out.print(arr[i] + ",");
					i++;
				}
			}

			System.out.print("front = " + front);
			System.out.print(",rear = " + rear);
			System.out.println("]");
		}

	}

}
