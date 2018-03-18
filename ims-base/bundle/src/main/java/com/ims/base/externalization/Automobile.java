package com.ims.base.externalization;

import java.io.*;

/**
 * The superclass implements externalizable
 */
class Automobile implements Externalizable {
	//class Automobile {
	/*
	 * Instead of making thse members private and adding setter and getter
	 * methods, I am just giving default access specifier. You can make them
	 * private members and add setters and getters.
	 */
	String regNo;
	String mileage;

	/*
	 * A public no-arg constructor
	 */
	public Automobile() {
	}

	Automobile(String rn, String m) {
		regNo = rn;
		mileage = m;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(regNo);
		out.writeObject(mileage);
	}

	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		regNo = (String) in.readObject();
		mileage = (String) in.readObject();
	}

}
