package com.ims.base.externalization;

import java.io.*;

public class Car extends Automobile implements Externalizable {

	String name;
	int year;

	/**
	 * mandatory public no-arg constructor
	 */
	public Car() {
		super();
	}

	Car(String n, int y, String reg, String mil) {
		super(reg, mil);
		name = n;
		year = y;
	}

	/**
	 * Mandatory writeExernal method.
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		// first we call the writeExternal of the superclass as to write
		// all the superclass data fields
		super.writeExternal(out);

		// Now the subclass fields
		out.writeObject(name);
		out.writeInt(year);
	}

	/**
	 * Mandatory readExternal method.
	 */
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		// first call the superclass external method
		super.readExternal(in);

		// Now the subclass fields
		name = (String) in.readObject();
		year = in.readInt();
	}

	/**
	 * What will happen when an externalizable class extends a non
	 * externalizable super class?
	 * 
	 * Here the Automobile class does not implement Externalizable interface. So
	 * to persist the fields in the automobile class the writeExternal and
	 * readExternal methods of Car class are modified to save/restore the super
	 * class fields first and then the sub class fields.
	 */
	
/*	public void writeExternal(ObjectOutput out) throws IOException {

		
		//  Since the superclass does not implement the Serializable interface we
		//  explicitly do the saving.
		 
		out.writeObject(regNo);
		out.writeObject(mileage);

		// Now the subclass fields
		out.writeObject(name);
		out.writeInt(year);
	}

	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {

		
		//  Since the superclass does not implement the Serializable interface we
		//  explicitly do the restoring
		 
		regNo = (String) in.readObject();
		mileage = (String) in.readObject();

		// Now the subclass fields
		name = (String) in.readObject();
		year = in.readInt();
	}*/
	 

	/**
	 * Prints out the fields. used for testing!
	 */
	public String toString() {
		return ("Reg No: " + regNo + "\n" + "Mileage: " + mileage + "\n"
				+ "Name: " + name + "\n" + "Year: " + year);
	}
}
