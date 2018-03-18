package com.ims.base.stringexamples;

/**
 * Ilustrates call by value in java. This is true even for string.
 * 
 */
public class SwapStrings {

	static void swap(String a, String b) {
		String temp = a;
		a = b;
		b = temp;
	}

	static void swap(String arr[]) {
		String temp = arr[0];
		arr[0] = arr[1];
		arr[1] = temp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] arr = new String[2];
		arr[0] = "rahul";
		arr[1] = "dibiya";
		swap(arr);
		System.out.println(arr[0] + " , " + arr[1]);

		swap(arr[0], arr[1]);
		System.out.println(arr[0] + " , " + arr[1]);

	}

}
