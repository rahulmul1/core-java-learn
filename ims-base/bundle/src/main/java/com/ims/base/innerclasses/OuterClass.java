package com.ims.base.innerclasses;
/**
 * Member inner class.
 * @author NK
 *
 */
class OuterClass extends BaseClass{
	private int i = 9;
	/**
	 * Instance var of inner class.
	 */
	InnerClass innerClass = new InnerClass();

	// Creating instance of inner class and calling inner class function
	public void createInner() {
		InnerClass i1 = new InnerClass();
		i1.getValue();
	}

	// inner class declarataion
	class InnerClass {
		int i = 1;

		
		public void getValue() {
			// accessing private variable from inner class
			System.out.println("value of i private variable from inner class " + this.i + " or " + i);
			
			/**
			 * Accessing private variable from outer class
			 * Every class has 1 this and 1 super reference. Hence these can be
			 * considered as static like class var. So how to access outer class instance var.
			 * 
			 */
			System.out.println("value of i private variable from outer class " + OuterClass.this.i);
			/**
			 * Accessing private variable from super class of outer class.
			 * Every class has 1 this and 1 super reference. Hence these can be
			 * considered as static like class var. So how to access super class of outer class instance var.
			 * 
			 */
			System.out.println("value of i variable from super class of outer class "+OuterClass.super.i);
			/**
			 * similar rule applies for the member functions to be accessed of outer and super class.
			 */
		}
	}
}
