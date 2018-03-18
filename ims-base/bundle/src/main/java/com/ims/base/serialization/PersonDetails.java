package com.ims.base.serialization;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonDetails.
 */
// public class PersonDetails extends Person implements Serializable{
public class PersonDetails extends Person {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6940436925726257547L;

	/** The name. */
	private String name;

	/** The age. */
	transient private int age;

	/** The sex. */
	static private String sex;

	/** The address. */
	transient private Address address;

	/**
	 * Gets the address.
	 * 
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 * 
	 * @param address
	 *            the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/*
	 * public PersonDetails() { // TODO Auto-generated constructor stub }
	 */
	/**
	 * Instantiates a new person details.
	 * 
	 * @param name
	 *            the name
	 * @param age
	 *            the age
	 * @param sex
	 *            the sex
	 * @param id
	 *            the id
	 */
	public PersonDetails(String name, int age, String sex, String id) {
		super(id);
		this.name = name;
		this.age = age;
		PersonDetails.sex = sex;
	}

	/**
	 * Instantiates a new person details.
	 * 
	 * @param name
	 *            the name
	 * @param age
	 *            the age
	 * @param sex
	 *            the sex
	 */
	public PersonDetails(String name, int age, String sex) {
		//super("10");
		this.name = name;
		this.age = age;
		PersonDetails.sex = sex;
	}

	/**
	 * Gets the age.
	 * 
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 * 
	 * @param age
	 *            the new age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the sex.
	 * 
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets the sex.
	 * 
	 * @param sex
	 *            the new sex
	 */
	public void setSex(String sex) {
		PersonDetails.sex = sex;
	}

	/**
	 * If Super Class of a Class already implements Serializable interface in
	 * Java then its already Serializable in Java, since you can not
	 * unimplemented an interface its not really possible to make it Non
	 * Serializable class but yes there is a way to avoid serialization of new
	 * class. To avoid java serialization you need to implement writeObject()
	 * and readObject() method in your Class and need to throw
	 * NotSerializableException from those method.
	 * 
	 * @param out
	 *            the out
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	/*
	 * private void writeObject(ObjectOutputStream out) throws IOException {
	 * 
	 * throw new NotSerializableException("Dont Serialize"); }
	 * 
	 * private void readObject(ObjectInputStream in) throws IOException {
	 * 
	 * throw new NotSerializableException("Dont Serialize"); }
	 */

	/**
	 * 
	 * Make the address class non serializable for the below example.
	 * 
	 * Case 2:What if you don’t have access to reference object’s source
	 * code(e.g you don’t have access to above Address class)
	 * 
	 * So then how will you serialize Employee object? so solution is you can
	 * make it transient.If you don’t want to serialize any field then make it
	 * transient. 1 transient Address address
	 * 
	 * So after making address transient in Employee class when you run
	 * program.You will get nullPointerException because during deserialization
	 * address reference will be null
	 * 
	 * If you make address transient then during deserialization it will return
	 * null.But what if you still want to have same state as when you have
	 * serialized address object.Java serialization provides a mechnism such
	 * that if you have private methods with particular signature then they will
	 * get called during serialization and deserialization so we will override
	 * writeObject and readObject method of employee class and they will be
	 * called during serialization and deserialization of Employee object.
	 * 
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeInt(address.getHomeNo());
		out.writeObject(address.getCity());
		out.writeObject(address.getStreet());
	}

	private void readObject(ObjectInputStream in)
			throws ClassNotFoundException, IOException {
		in.defaultReadObject();
		address= new Address();
		address.setHomeNo(in.readInt());
		address.setCity((String) in.readObject());
		address.setStreet((String) in.readObject());
		//address=new Address(homeNo,street,city);
	}

}