package com.ims.base.javalangex;

/**
 * Deeply cloning the address field
 * 
 * Employee's clone() method first invokes super.clone(), which shallowly copies
 * the name, age, and address fields. It then invokes clone() on the address
 * field to make a duplicate of the referenced Address object.
 * 
 */
class Employee implements Cloneable {
	private String name;
	private int age;
	private Address address;

	Employee(String name, int age, Address address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}

	@Override
	public Employee clone() throws CloneNotSupportedException {
		Employee e = (Employee) super.clone();
		e.address = address.copyAddress();
		return e;
	}

	Address getAddress() {
		return address;
	}

	String getName() {
		return name;
	}

	int getAge() {
		return age;
	}
}
