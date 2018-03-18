package com.ims.base.corejava;

public class MethodOverloadingDemo {

	public static void staticMethod() {
		System.out.println("BaseClass:  staticMethod");
	}

	/**
	 * static methods can be overloaded.
	 * 
	 * @param a
	 */
	public static void staticMethod(int a) {
		System.out.println("BaseClass: overloaded staticMethod");
	}

	/**
	 * cannot overload by removing static modifier.
	 */
	/*
	 * public void staticMethod() {
	 * System.out.println("BaseClass:  staticMethod"); }
	 */

	public void disp() {
		System.out.println("BaseClass: disp");
	}
	/**
	 * cannot overload by removing access specifier
	 */
	/*void disp() {
		System.out.println("BaseClass: disp");
	}*/

}
