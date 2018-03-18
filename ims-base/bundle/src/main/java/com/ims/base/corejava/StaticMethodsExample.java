package com.ims.base.corejava;

public class StaticMethodsExample extends Base{

	int child;
	static int c;
	static {
		/**
		 * 	super and this cant be used in static context.
		 */
		// super(); //Error
		// this.child =9; //Error
		System.out.println("Static block invoked");
	}

	StaticMethodsExample() {
		System.out.println("Constructor is invoked");
		
	}

	void showi() {
		//static methods can be invoked with this and super.
		super.showvar();
		this.showcount();
		/**
		 * non static can call static
		 */
		showcount();
		System.out.println(child);
		/**
		 * Static var cannot be declared inside a method or constructor.
		 *  Ie static var cannot be local var.
		 */
		//static int i=1;
	}

	static void showcount() {
		//super and this cant be used in static context.
		System.out.println(c);
		/**
		 * static cant call non static. Need to access by creating its object.
		 */
		//showi();
		/**
		 * Static var cannot be declared inside a method or constructor.
		 *  Ie static var cannot be local var.
		 */
		//static int i=1;
	}
	
	public static void main(String args[]) throws Exception {
		StaticMethodsExample p = new StaticMethodsExample();

	}
}

class Base {
	int base = 1;
	static int b = 2;

	void showbase() {
		System.out.println(base);
	}

	static void showvar() {
		System.out.println(b);
	}

}