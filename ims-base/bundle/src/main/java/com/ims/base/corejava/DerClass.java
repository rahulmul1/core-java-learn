package com.ims.base.corejava;

class BaseClass {
	public static void staticMethod() {
		System.out.println("BaseClass:  staticMethod");
	}
	
	/**
	 * no public specifier
	 */
	static void staticMethod2(int a) {
		System.out.println("BaseClass: staticMethod2");
	}
	
	
	public void disp() {
		System.out.println("BaseClass: disp");
	}
	
	/**
	 * no public specifier
	 */
	void disp2(int a) {
		System.out.println("BaseClass: disp2");
	}
}

class DerClass extends BaseClass {
	/**
	 * Cannot reduce the visibility of the inherited method from BaseClass
	 * for static methods.
	 */
	/*private static void staticMethod() {
		System.out.println("Derived Class: staticMethod");
	}*/
	/**
	 * Cannot reduce the visibility of the inherited method from BaseClass
	 * for nonstatic methods,
	 */
	/*private void disp() {
		System.out.println("Derived Class: disp");
	}*/
	
	/**
	 * This instance method cannot override the static method from BaseClass.
	 * Cannot override by removing static. 
	 * 
	 */
	/*public void staticMethod2(int a) {
		System.out.println("Derived Class: staticMethod");
	}*/
	
	/**
	 * This static method cannot hide the instance method from BaseClass
	 * connot override by adding static
	 */
	/*public static void disp() {
		System.out.println("BaseClass: disp");
	}*/
	
	/**
	 * correctly hidden static method of base class by increasing scope
	 * from default to public
	 */
	public static void staticMethod2(int a) {
		System.out.println("Derived Class: staticMethod2 ");
	}

	/**
	 * correctly overriden non-static method of base class by increasing scope
	 * from default to public
	 */
	public void disp2(int a) {
		System.out.println("Derived Class: disp2");
	}
	
	/**
	 * mthod not present in base class.
	 */
	public void show() {
		System.out.println("Inside show");
	}
	

	public static void main(String[] args) {
		/**
		 * Sttatic methods cant be overridden or inherited.depend on type of
		 * reference and not obj
		 * 
		 */
		BaseClass baseClassObj = new DerClass();
		baseClassObj.staticMethod2(1);
		baseClassObj.disp2(1);
		/**
		 * Methods that are newly declared in the sub classes cannot be accessed
		 * Using this reference
		 */
		// baseClassObj.show();

	
	}
}
