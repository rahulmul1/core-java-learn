package com.ims.base.corejava;


/**
 * Constructor Inheritance
 *
 */
class A {
	int i = 10;

	A(int p) {
		System.out.println("Inside Base");
	}
	/*A(){
		System.out.println("Inside Base");
	}*/
}

class B extends A {
	int i = 20;

	/**
	 * Comment super(10) and check it gives
	 * o/p==compile time error cannot find symbol symbol : constructor A() Here
	 * above since we have a constructor B() which will call the default
	 * constructor A() if its object is created. This will give an error since
	 * there is no constructor A(). So there are 2 ways to prevent error.
	 * 1.comment the B() constructor. 2.call super(10) from B() also.
	 */
	B(){
		super(10);
		//this(1);CE
		System.out.println("Inside Default");
	}

	B(int k) {
		this();
		System.out.println("inside para");
	}

	
}

class ConstructorInheritanceTest{
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		B b = new B(1000);
	}
}
