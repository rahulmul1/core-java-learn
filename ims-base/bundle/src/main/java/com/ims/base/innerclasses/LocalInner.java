package com.ims.base.innerclasses;

public class LocalInner {
	private int data = 30;// instance variable

	/**
	 * local var and parameter of display method have to be final to be accessed
	 * in the local inner class.
	 * 
	 */
	void display(final int param) { // final parameter variable
		final int value = 50;// local variable must be final
		class Local {
			void msg() {
				System.out.println(data + " " + value + " " + param);
			}// ok
		}
		/**
		 * this instantiation is req to call the msg() method. It can be called from only inside the method.
		 * Cannot access from main.
		 */
		Local l = new Local();
		l.msg();
	}

	public static void main(String args[]) {
		LocalInner obj = new LocalInner();
		obj.display(10);
	}
}
