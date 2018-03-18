package com.ims.base.serialization;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * 
 * If you try to serialize an object of a class which implements Serializable,
 * but the object includes a reference to an non- Serializable class then a
 * ‘NotSerializableException’ will be thrown at runtime. We got exception what
 * went wrong.I forgot to mention,Address class must also be serializable.So you
 * have to make Address serializable by implement serialzable interface.
 * 
 * The Class Address.
 */
//public class Address implements Serializable {
	 public class Address {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 783808547847355239L;

	/** The home no. */
	int homeNo;

	/** The street. */
	String street;

	/** The city. */
	String city;
	
	public Address(){
		
	}

	/**
	 * Instantiates a new address.
	 * 
	 * @param homeNo
	 *            the home no
	 * @param street
	 *            the street
	 * @param city
	 *            the city
	 */
	public Address(int homeNo, String street, String city) {
		super();
		this.homeNo = homeNo;
		this.street = street;
		this.city = city;
	}

	/**
	 * Gets the home no.
	 * 
	 * @return the home no
	 */
	public int getHomeNo() {
		return homeNo;
	}

	/**
	 * Sets the home no.
	 * 
	 * @param homeNo
	 *            the new home no
	 */
	public void setHomeNo(int homeNo) {
		this.homeNo = homeNo;
	}

	/**
	 * Gets the street.
	 * 
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 * 
	 * @param street
	 *            the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the city.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 * 
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}
}