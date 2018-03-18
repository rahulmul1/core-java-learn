package com.ims.base.corejava;

import java.util.ArrayList;

public class ClassCastExCollections {

	public static void main(String[] args) {

		ArrayList names = new ArrayList();
		/**
		 * here collections only take wrapper objects as input.
		 */
		names.add("abcd"); // adding String
		names.add(100); // adding Integer
		
		/**
		 * get(1) method returns object of class Object which we are 
		 * trying to cast to String object. So basically we are trying to
		 * cast Integer object to String object which are not 
		 * in parent child class relationship.
		 */
		String name = (String) names.get(0); // OK
		//name = (String) names.get(1); // throw ClassCastException because you
										// can not convert Integer to String
		
		/**
		 * toString can be used to convert Integer to String.
		 */
		
		name = names.get(1).toString();	
		System.out.println(name);
		
		String name1 = String.valueOf(names.get(1));
		System.out.println(name1);
		
	}
}
