package com.ims.base.collections;

public class ArrayBasedStackTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		ArrayBasedStack<Integer> stack = new ArrayBasedStack<>(10);
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		stack.push(10);
		stack.display();

		stack.push(11);
		stack.display();
		
		stack.pop();
		stack.display();
		
		stack.pop();
		stack.display();
		
		stack.pop();
		stack.display();
		
	}

}
