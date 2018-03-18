package com.ims.base.stringexamples;

public class EqualsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1 = new String("Hello");
		String s2 = "Hello";
		System.out.println("s1 = " + s1 + "\ns2 = " + s2);
		if (s1 == s2)
			System.out.println("s1==s2 true");
		else
			System.out.println("s1==s2 false");
		if (s1.equals(s2))
			System.out.println("s1.equals(s2) true");
		else
			System.out.println("s1.equals(s2) false");

	}

}
