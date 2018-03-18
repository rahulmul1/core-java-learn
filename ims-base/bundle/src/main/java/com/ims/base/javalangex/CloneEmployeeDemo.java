package com.ims.base.javalangex;

/**
 * 
 * The below class tests deep cloning. If u need to test shallow cloning try
 * commenting the address.clone method and also the call to this method from
 * employees clone method.
 * 
 * Shallow copy problem : CloneDemo's main() method creates an Employee object
 * and clones this object. It then changes the city's name in the original
 * Employee object's address field. Because both Employee objects reference the
 * same Address object, the changed city is seen by both objects.
 */
public class CloneEmployeeDemo {
	public static void main(String[] args) throws CloneNotSupportedException {
		Employee e = new Employee("John Doe", 49, new Address("Denver"));
		System.out.printf("%s: %d: %s%n", e.getName(), e.getAge(), e
				.getAddress().getCity());
		Employee e2 = (Employee) e.clone();
		System.out.printf("%s: %d: %s%n", e2.getName(), e2.getAge(), e2
				.getAddress().getCity());
		e.getAddress().setCity("Chicago");
		System.out.printf("%s: %d: %s%n", e.getName(), e.getAge(), e
				.getAddress().getCity());
		System.out.printf("%s: %d: %s%n", e2.getName(), e2.getAge(), e2
				.getAddress().getCity());
	}
}
