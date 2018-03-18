package com.ims.base.innerclasses;

/**
 * Member inner class
 * @author NK
 *
 */
public class TestMemberInner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OuterClass outerClass = new OuterClass();
		OuterClass.InnerClass innerClass = outerClass.new InnerClass();
		innerClass.getValue();
		
	}

}
