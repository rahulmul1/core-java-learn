package com.ims.base.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class GetPersonDetails.
 * 
 * Make superclass serializable and subclass non serializable and then run this
 * . Both super and sub class variables are serialized.
 * 
 * Make Subclass serializable and superclass non serializable and then run this
 * and check the difference. Superclass variables (ie id in this case) are not
 * serialized then and while deserializing, JVM will run default constructor and
 * not parameterized constructor in super class and populates the default
 * values. Same thing will happen for all superclasses.
 * 
 */
public class GetPersonDetails {

	/** The Constant FILEPATH. */
	public static final String FILEPATH = "H:/IMS PROJECT/Created Xmls/SerializedPerson.txt";

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		/**
		 * Persists object by initializing using constructor.
		 */
		Address address1 = new Address(52, "sector 4", "Gurgaon");
		Address address2 = new Address(53, "sector 5", "Gurgaon");
		
		PersonDetails person1 = new PersonDetails("hemanth1", 10, "Male", "1");
		person1.setAddress(address1);
		PersonDetails person2 = new PersonDetails("bob", 12, "Male", "2");
		person2.setAddress(address1);
		PersonDetails person3 = new PersonDetails("Richa", 10, "Female", "3");
		person3.setAddress(address1);
		List<PersonDetails> list = new ArrayList<PersonDetails>();
		list.add(person1);
		list.add(person2);
		list.add(person3);

		serializePerson(list);
		deserializePerson();

		/**
		 * Persists object by initializing one object thru setter.
		 * 
		 */
		PersonDetails person4 = new PersonDetails("rahul", 20, "Female");
		person4.setId("4");
		person4.setAddress(address2);
		PersonDetails person5 = new PersonDetails("karan", 21, "Female");
		person5.setAddress(address2);
		PersonDetails person6 = new PersonDetails("ajat", 22, "Male");
		person6.setAddress(address2);
		
		List<PersonDetails> list1 = new ArrayList<PersonDetails>();
		list1.add(person4);
		list1.add(person5);
		list1.add(person6);

		serializePerson(list1);
		deserializePerson();

	}

	/**
	 * Deserialize person.
	 */
	@SuppressWarnings("unchecked")
	private static void deserializePerson() {
		List<PersonDetails> pDetails = null;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			fileInputStream = new FileInputStream(FILEPATH);
			objectInputStream = new ObjectInputStream(fileInputStream);
			pDetails = (ArrayList<PersonDetails>) objectInputStream
					.readObject();
			objectInputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		System.out
				.println("Transient variables are not serialized, so while deserialization those variables will be initialized with corresponding default values (ex: for objects null, int 0).");
		System.out
				.println("Static variables are not serialized, So while deserialization static variable value will loaded from the class.(Current value will be loaded.)");

		for (PersonDetails personDetails : pDetails) {
			System.out.println();
			System.out.println("instance var name: " + personDetails.getName());
			System.out.println("transient age: " + personDetails.getAge());
			System.out.println("Static sex: " + personDetails.getSex());
			System.out.println("Super class instace var id: "
					+ personDetails.getId());
			System.out.println("Reference Object Address: " + personDetails.getAddress().getCity() + " , " + personDetails.getAddress().getStreet() + " , " + personDetails.getAddress().getHomeNo());
			System.out.println();
		}
		System.out.println();

	}

	/**
	 * Serialize person.
	 * 
	 * @param personList
	 *            the person list
	 */
	private static void serializePerson(List<PersonDetails> personList) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(FILEPATH);
			out = new ObjectOutputStream(fos);
			out.writeObject(personList);
			out.close();
			System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}