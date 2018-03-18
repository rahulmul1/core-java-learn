package com.ims.base.innerclasses;

public class NestedStaticClass {
	private static int data = 30;
	private int i = 4;

	static class Inner {
		static void msg() {
			int j=1;
			//static int k=1;
			System.out.println("data is " + data + j /*+ i*/);
		}
		void msg1(){
			//static int l=1;
			System.out.println("inside Nonstatic method " + data /*+ i*/);
		}
	}

	
}