package com.ims.base.innerclasses;

public class TestStaticClass {

	public static void main(String args[]) {
		NestedStaticClass.Inner.msg();// no need to create the instance of static
								// nested class
	}
}
