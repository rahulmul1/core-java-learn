package com.ims.base.corejava;
/**
 * Explains static block and var are initialized on class load.
 * 
 */
class StaticExample {
	static int count = 0;
	StaticExample() {
		System.out.println("Constructor invoked");
	}

	/**
	 * Static context can only have static members. For nonstatic they have to
	 * be reffered via objects.
	 */
	static {
		count++;
		System.out.println("inside static");
		StaticExample t = new StaticExample();
		t.show();
	}

	void show() {
		System.out.println("inside show");
	}

}

public class StaticTest {
	/**
	 * Will not be initialised . COntrol goes directly to main().
	 * Only static variables are initialized on class load and not non static ones. 
	 */
	StaticExample objOutsideMain =  new StaticExample();
	int i =1;
	
	public static void main(String[] args) {
		/**
		 * 1.StaticExample.count is responsibe for loading the class
		 * StaticExample. If commented StaticExample is not loaded and hence
		 * none of the static blocks execute. 2. If not commented static block
		 * of staticExample will execute first.
		 */
		int count = StaticExample.count;
		System.out.println(count);
		/**
		 * cannot access non static here.
		 */
		//System.out.println(i);
	}
}