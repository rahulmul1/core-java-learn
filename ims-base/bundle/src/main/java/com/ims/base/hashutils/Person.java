package com.ims.base.hashutils;

import java.util.HashSet;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class Person.
 * 
 * How hashcode &equals works for collections.(hashmap and hashset)
 */
class Person {
	
	/** The name. */
	String name;

	/**
	 * Instantiates a new person.
	 *
	 * @param n the n
	 */
	Person(String n) {
		name = n;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {

		System.out.println("in equals");

		Person obj = (Person) arg0;

		System.out.println("1st " + getName());
		System.out.println("2nd " + obj.getName());

		if (this.getName().equals(obj.getName())) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		System.out.println("in hash code");
		System.out.println(" value is " + Integer.valueOf(name.charAt(0)));
		return Integer.valueOf(name.charAt(0));
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		
		Person obj1 = new Person("karan");
		Person obj2 = new Person("ajay");
		Person obj3 = new Person("rahul");
		Person obj4 = new Person("rahul");

		Set<Person> sset = new HashSet<Person>();

		/**
		 * Question 1 : why equals() function is called only once for checking
		 * obj3 and obj4 ? Why its not checked for rest of the objects ?1.
		 * Ans : There's no need to call equals if hashCode differs
		 */
	/*	sset.add(obj1);
		sset.add(obj4);
		sset.add(obj2);
		sset.add(obj3);
		
		System.out.println("Size: " + sset.size());
	*/	
	
		/**
		 * Question 2 : If the answer is because they both have same hash
		 * code,only then equals will be called, then why its not called for
		 * below code It's not going inside equals() method even though two same
		 * objects are added to hash set which has same hash code. Ans : equals
		 * will be called only if hash of key being added equals to the key
		 * already present in set and references of these two are different.
		 */
		sset.add(obj1);
		sset.add(obj4);
		sset.add(obj2);
		sset.add(obj4);
		
		System.out.println("Size: " + sset.size());
		
		/**
		 * Question 4 :I iterated the above value and printed the contents but
		 * neither hashcode nor equals were called. whenits really useful to
		 * override hashcode and equals method ? Ans : There's no need for
		 * hashCode and/or equals just to iterate - you're not comparing objects
		 * Hashcode called only when needed to distinguish in between objects.
		 */
		System.out.println(sset);
		

	}
}
