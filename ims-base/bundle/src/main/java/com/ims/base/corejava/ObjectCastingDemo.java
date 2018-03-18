package com.ims.base.corejava;

import java.util.ArrayList;

public class ObjectCastingDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/**
		 * CASE A) Wrong type casting.Belong to same heirarchy
		 * ==================================================
		 */
		
		/**
		 * perfectly allowed
		 */
		Person person = new Person();
		/**
		 * Compiler error Type mismatch: cannot convert from Person to Student
		 */
		// Student student = person;

		/**
		 * Cannot type cast parent object.
		 * 
		 * Exception in thread "main" java.lang.ClassCastException:
		 * com.ims.base.corejava.Person cannot be cast to
		 * com.ims.base.corejava.Student at
		 * com.ims.base.corejava.ObjectCastingDemo
		 * .main(ObjectCastingDemo.java:14)
		 */
		// Student student = (Student) person;
		
		
		/**
		 * CASEB) Wrong type casting. Not related in any way ie not in same herirarchy.
		 * ==================================================================
		 */
		
		/**
		 * Type casting two different objects ie baseClass object to
		 * Student class object.produces java.lang.ClassCastException:
		 * Eventhough baseclass and student are not related it doesnt give CE.
		 * This is coz it checks the relation in line 2 between object and
		 * student which are related.
		 * So if there is not relation b/w reference being typecasted and
		 * reference to which it is assigned it gives CE else if relation exists then
		 * CCE.
		 * 
		 */
		/*
		Object object = new BaseClass();
		Student student = (Student) object;		//line 2
		*/
		
		/**
		 * Correct type casting
		 * =============================================
		 */
		
		/**
		 * perfectly allowed
		 */
		Person person2 = new Student();
		/**
		 * Type mismatch: cannot convert from Person to Student
		 */
		// Student student2 = person;

		/**
		 * Can type cast parent reference having child object. type casting
		 * added to parent reference. This is now basically the child class
		 * object only ie. it is equal to Student student2= new Student();
		 */
		Student student2 = (Student) person2;
		student2.setName("sda");

		/**
		 * Correct Type castin in wrapper classes.
		 * =============================================
		 * 
		 */
		
		/**
		 * similar type casting here
		 */
		Object object = new Integer(2);
		Integer integer2 = (Integer) object;
		System.out.println(integer2);

		
		/**
		 * Wrong type casting on wrappers
		 * =============================================
		 */
		
		
		/**
		 * Compile time error. 
		 */
		Integer integer = new Integer(10);
		// String string = (String)integer;

		/**
		 * Exception in thread "main" java.lang.ClassCastException:
		 * java.lang.Integer cannot be cast to java.lang.String at
		 * com.ims.base.corejava
		 * .ObjectCastingDemo.main(ObjectCastingDemo.java:62)
		 */
		 //String string = (String)object;

	}

}

class Person {
	// Instance variables
	private String name;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

class Student extends Person {
	// Instance variables
	private int numCourses; // number of courses taken so far, max 30
	private int marks;

	public int getNumCourses() {
		return numCourses;
	}

	public void setNumCourses(int numCourses) {
		this.numCourses = numCourses;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

}

