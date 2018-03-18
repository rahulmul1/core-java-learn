package com.ims.base.javalangex;

class Data implements Cloneable {
	int x;
	/*Overriden and made public so that it can be called from a different class also
	like CLoneDemo.If not ovverriden then protected clone method would be inherited from
	object class which could not be called from outside this class.*/
	@Override
	public Data clone() throws CloneNotSupportedException {
		return (Data)super.clone();
	}
	public static void main(String[] args) throws CloneNotSupportedException {
		Data data = new Data();
		data.x = 5;
		System.out.printf("data.x = %d%n", data.x);
		Data data2 = (Data) data.clone();
		System.out.printf("data2.x = %d%n", data2.x);
	}
}