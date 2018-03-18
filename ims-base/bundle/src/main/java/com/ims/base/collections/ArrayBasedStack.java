package com.ims.base.collections;

import java.util.Arrays;

public class ArrayBasedStack<T> {

	private T arr[];
	private int top = -1;
	private int capacity;
	private static int INITIALCAPACITY = 10;

	public int getTop() {
		return top;
	}

	public int getCapacity() {
		return capacity;
	}

	@SuppressWarnings("unchecked")
	public ArrayBasedStack(int capacity) {
		this.capacity = capacity;
		arr = (T[]) new Object[capacity];
	}

	public boolean isEmpty() {
		if (top == -1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFull() {
		if (top == capacity - 1) {
			return true;
		} else {
			return false;
		}
	}

	public void push(T e) {
		if (top < capacity - 1) {
			arr[++top] = e;
		} else {
			System.out
					.println("Stack Overflow : Doubling the size of the Array");
			reallocate();
			push(e);
		}
	}

	public T pop() {
		T e = null;

		if (!isEmpty()) {
			e = arr[top--];
			System.out.println("Popped = " +e);
			if ((capacity) / 2 >= INITIALCAPACITY && top < (capacity / 2) - 1) {
				shrink();
			}
		} else {
			System.out.println("Stack is empty");
		}
		return e;
	}

	private void reallocate() {
		if (isFull()) {
			capacity = 2 * capacity;
			arr = Arrays.copyOf(arr, capacity);
		}
	}

	private void shrink() {
		capacity = capacity / 2;
		arr = Arrays.copyOf(arr, capacity);
	}

	public void display() {
		System.out.print("[");
		for (int i = top; i > -1; i--) {
			System.out.print(arr[i] + ",");
		}
		System.out.println("]");
		System.out.println("top =" + (top));
		System.out.println("currentsize =" + (top + 1));
		System.out.println("capacity =" + capacity);
	}
}
