package com.ims.base.innerclasses;

 class Person {
	private static String i =" mango";
	private String j = " lichi";
	protected String z = " protected";
	void eat(){
		System.out.println("Inside eat of person");
	}
}

public class TestAnnonymousInner {
	public static void main(String args[]) {
		/**
		 * creates an instance of anonym cls and assign it to person class reference.
		 */
		Person p = new Person() {
			void eat() {
				
				/**
				 * Dosnt have access to variables of the outer class. Simply extends the above class.
				 * Method overriding.
				 */
				System.out.println("nice fruit"/* + i+j*/ + z);
				super.eat();
			}
		};

		p.eat();
	}
}
