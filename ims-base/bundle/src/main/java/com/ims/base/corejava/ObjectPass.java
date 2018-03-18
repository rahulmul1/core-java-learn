package com.ims.base.corejava;

public class ObjectPass {
	int value;
	String x = "hi";

	public void change() {
		x += "bye";
	}

	public static void increment(ObjectPass a) {
		a.value++;
		System.out.println(a);
	}

	/**
	 * Do run the code after uncommenting the first line in foo also.A new
	 * object is created hence no change in p in that case.
	 * 
	 * @param d
	 */
	static public void foo(ObjectPass d) {
		// d = new ObjectPass();
		d.setValue(2);
		d.setX("New Object");
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

}
