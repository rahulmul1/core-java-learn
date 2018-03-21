package com.ims.base.singleton;

public class SingletonExample {
	// Static member holds only one instance of the
	// SingletonExample class
	/**
	 * Instance variable is an object of this class. Needs to be static because the
	 * getSingletonInstance() method should not be called by object instead will be
	 * called by the class and hence needs to be static and non-static member cannot
	 * be accessed inside static member.
	 */
	private static SingletonExample singletonInstance;

	// SingletonExample prevents any other class from instantiating
	private SingletonExample() {
	}

	// Providing Global point of access
	public static SingletonExample getSingletonInstance() {
		if (null == singletonInstance) {
			singletonInstance = new SingletonExample();
		}
		return singletonInstance;
	}

	public void printSingleton() {
		System.out.println("Inside print Singleton");
	}
}
