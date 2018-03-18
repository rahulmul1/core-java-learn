package com.ims.base.javalangex;

/**
 * The previous example didn't need to override the clone() method because the
 * code that invokes clone() is located in the class being cloned (i.e., the
 * Data class). However, if the clone() invocation is located in a different
 * class, you will need to override clone(). Otherwise, you will receive a
 * "The method clone() from the type Object is not visible" message because
 * clone() is declared protected. To test this try uncommenting the comented
 * line below.
 * 
 * @author NK
 * 
 */
public class CloneDemo {
	public static void main(String[] args) throws CloneNotSupportedException {
		Data data = new Data();
		data.x = 5;
		System.out.printf("data.x = %d%n", data.x);
		Data data2 = (Data) data.clone();
		System.out.printf("data2.x = %d%n", data2.x);
	}
}