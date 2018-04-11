package com.ims.base.singleton;

import org.apache.log4j.Logger;

public class ClassicSingleton {
	private static final Logger logger = Logger.getLogger(ClassicSingleton.class);
	// Static member holds only one instance of the
	// ClassicSingleton class
	/**
	 * Instance variable is an object of this class. Needs to be static because the
	 * getSingletonInstance() method should not be called by object instead will be
	 * called by the class and hence needs to be static and non-static member cannot
	 * be accessed inside static member.
	 */
	private static ClassicSingleton singletonInstance;

	// ClassicSingleton prevents any other class from instantiating
	private ClassicSingleton() {
	}

	// Providing Global point of access
	public static ClassicSingleton getInstance() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering getInstance()");
		}
		if (null == singletonInstance) {
			singletonInstance = new ClassicSingleton();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("exiting getInstance()");
			logger.debug("returning: " + singletonInstance);
		}
		return singletonInstance;
	}

	public void printSingleton() {
		System.out.println("Inside print Singleton");
	}
}
