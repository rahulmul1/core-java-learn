package com.ims.base.javalangex;

class Address {
	private String city;

	Address(String city) {
		this.city = city;
	}

	public Address copyAddress() {
		return new Address(new String(city));
	}

	String getCity() {
		return city;
	}

	void setCity(String city) {
		this.city = city;
	}
}