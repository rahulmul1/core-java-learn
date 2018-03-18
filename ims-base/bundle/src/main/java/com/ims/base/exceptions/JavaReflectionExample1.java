package com.ims.base.exceptions;

import java.lang.reflect.Method;
/**
 * classnotfoundex simulator.
 *
 */
public class JavaReflectionExample1 {

	public JavaReflectionExample1() {
		Class c;
		try {
			/**
			 * Will throw classnotfoundex coz the class we are trying to load dosnt exist.
			 */
			c = Class.forName("FooClass");
			Method m[] = c.getDeclaredMethods();
			System.out.println(m[0].toString());
		} catch (ClassNotFoundException e) {
			// deal with the exception here ...
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new JavaReflectionExample1();
	}

}