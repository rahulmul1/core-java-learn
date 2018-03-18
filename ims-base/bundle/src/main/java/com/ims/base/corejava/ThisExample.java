package com.ims.base.corejava;

public class ThisExample {

	String s;
	ThisExample instanceObj;

	ThisExample() {
		/**
		 * this keyword can only be the first statement in Constructor. 
		 */
		this("string");
		System.out.println("Inside Constructor without parameter");
		/**
		 * not allowed at this line.
		 */
		// this("ThisExample");
	}

	/**
	 * 2.this keyword can be used inside Constructor to call another overloaded
	 * Constructor in same class.
	 * 
	 * @param str
	 */
	ThisExample(String str) {
		/**
		 * trying to call a constructor with obj as argument using this not allowd.
		 */
		// this(this);
		/**
		 * 1.this refer to instance var
		 */
		this.s = str;
		System.out
				.println("Inside Constructor with String parameter as " + str);
		/**
		 * from constructor you can call method using this.method(); but From a method you cannot call constructor using this(). 
		 */
		this.method();
	}

	/**
	 * 4.this as arg in constructor call
	 */
	ThisExample(ThisExample thisExample) {
		/**
		 * Not allowed this on left hand side.
		 */
		// this=thisExample;
		/**
		 * allowd
		 */
		this.instanceObj = thisExample;
		/**
		 * allowed this on right hand side.
		 */
		// thisExample=this;
		System.out.println("Inside copy Constructor ");
		this.s = thisExample.s;

	}

	void methodOne() {
		System.out.println("Inside Method ONE");
	}

	/**
	 * 3.this keyword can also be used inside methods to call another method
	 * from same class.
	 * . Need not be the first statement in method.
	 */
	void methodTwo() {
		/**
		 * From a method you cannot call constructor using this(). But from constructor you can call method using this.method();
		 */
		//this();
		
		System.out.println("Inside Method TWO");
		/**
		 * Need not be the first statement in method.
		 */
		this.methodOne();// same as calling methodOne()
	}

	/**
	 * 4.Example of this keyword as method parameter
	 */
	void method() {
		method1(this);
	}

	void method1(ThisExample t) {
		System.out.println(this.s);
	}

	public static void main(String[] args) {
		ThisExample obj = new ThisExample();
		obj.methodTwo();
		obj.method();

		ThisExample obj1 = new ThisExample(obj);
		System.out.println(obj1.s);
	}

}
