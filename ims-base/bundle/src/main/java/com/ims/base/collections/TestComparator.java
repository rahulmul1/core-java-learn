package com.ims.base.collections;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * The Class TestComparator.
 * 
 * Sort the strings according to ascending order of length.
 * IF length is equal then sort in ascending order of alphabets.
 * 
 */
public class TestComparator {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		TreeSet<Object> t = new TreeSet<>(new MyComparator());
		t.add("A");
		t.add(new StringBuffer("ABC"));
		t.add(new StringBuffer("AA"));
		t.add("XX");
		t.add("ABCD");
		t.add("A");
		System.out.println(t);

	}
}

class MyComparator implements Comparator<Object> {
	public int compare(Object o1, Object o2) {

		String s1 =  o1.toString();
		String s2 =  o2.toString();
		Integer l1 = s1.length();
		Integer l2 = s2.length();
		if (l1.equals(l2)) {
			return s1.compareTo(s2);
		} else {
			return l1.compareTo(l2);
		}

	}

}









