package com.ims.base.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Person.
 */
public class Person implements Serializable {
	// public class Person {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2295044255392332679L;

	/** The id. */
	private String id;

	/**
	 * Instantiates a new person.
	 * 
	 * If subclass is serializable and super is not then following rule applies.
	 * To allow subtypes of non-serializable classes to be serialized, the
	 * subtype may assume responsibility for saving and restoring the state of
	 * the supertype's public, protected, and (if accessible) package fields.
	 * The subtype may assume this responsibility only if the class it extends
	 * has an accessible no-arg constructor to initialize the class's state. It
	 * is an error to declare a class Serializable if this is not the case. The
	 * error will be detected at runtime. If this is violated, readObject() will
	 * produce a java.io.InvalidClassException in runtime.
	 * 
	 * 
	 */
	public Person() {
		id = "1111111";
	}

	/**
	 * Instantiates a new person.
	 * 
	 * @param id
	 *            the id
	 */
	public Person(String id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * The below two methods will work when this class is serializable.
	 * 
	 * How to customize the default protocol? Notice that both methods are
	 * declared private and ofcourse they must be declared private, proving that
	 * neither method is inherited and overridden or overloaded. The trick here
	 * is that the virtual machine will automatically check to see if either
	 * method is declared during the corresponding method call. The virtual
	 * machine can call private methods of your class whenever it wants but no
	 * other objects can. Thus, the integrity of the class is maintained and the
	 * serialization protocol can continue to work as normal.
	 * 
	 */

	/**
	 * Always treat de-serialization as a full-blown constructor, by validating
	 * the final state of the de-serialized object.
	 */
	private void readObject(ObjectInputStream aInputStream)
			throws ClassNotFoundException, IOException {
		// always perform the default de-serialization first
		System.out.println("Overriden readObject");
		/**
		 * Comment below line and check the value of instance var 'id'.
		 * It will be null coz default serialization will not work.
		 * We need to comment both read and right default object calls.
		 * Commenting one will cause exeception.
		 */
		aInputStream.defaultReadObject();

	}

	/**
	 * This is the default implementation of writeObject. Customise if
	 * necessary.
	 */
	private void writeObject(ObjectOutputStream aOutputStream)
			throws IOException {
		System.out.println("Overriden writeObject");
		// perform the default serialization for all non-transient, non-static
		// fields
		/**
		 * Comment below line and check the value of instance var 'id'.
		 * It will be null coz default serialization will not work.
		 * We need to comment both read and right default object calls.
		 * Commenting one will cause exeception.
		 */
		aOutputStream.defaultWriteObject();
	}

}
