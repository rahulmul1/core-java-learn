package com.ims.base.corejava;

import java.io.IOException;

public class ConstructorExceptionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * Constructor Inheritance , overloading and exception.
 * 
 */
class AParent {
	int i = 10;

	/**
	 * try toggling b.w the comented and uncommented line below and c the
	 * compiler error occurs when we uncomment the throwable line. This is coz
	 * the constructor executed 1st(AParent(int p)) is having parent exception
	 * of the exception thrown by the constructor executed after(BChild()) that
	 * which is not allowed.
	 */
	// AParent(int p) throws Throwable {
	AParent(int p) throws RuntimeException {

		System.out.println("Inside Base para int");
	}

	AParent() {
		System.out.println("Inside Base default");
	}

	AParent(String s) throws IOException {
		System.out.println("Inside Base para String");
	}
}

class BChild extends AParent {
	int i = 20;

	/**
	 * Notice BChild(int k) throws throwable calls BChild() throws Exception
	 * which intern calls AParent(int p) throws RuntimeException.So AParent(int
	 * p) is executed first then BChild() then BChild(int k).
	 * 
	 * Notice the contructor executed first(AParent(int p)) has the lowest level
	 * exception. And then the level of exception keeps increasing as we go to
	 * the next executed constructor.
	 * 
	 * SO basically the parent constructor or the constuctor executed 1st will
	 * have lower or equal exception than the constructor executed last. OR the
	 * called constructor should throw a lower or equal exception than the
	 * calling constructor.
	 * 
	 * 
	 */
	BChild() throws Exception {
		super(10);
		System.out.println("Inside Default");
	}

	BChild(int k) throws Throwable {
		this();
		System.out.println("inside child para int ");
	}

	BChild(String s) throws Exception {
		super(s);
		System.out.println("inside child para string");
	}

}
