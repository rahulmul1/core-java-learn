package com.ims.base.externalization;

import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ExternExample.
 * 
 * What if the externalizable class extends super class that implements Externalizable interface?
 * 
 *  
 */
public class ExternExample {
   
	/** The Constant FILEPATH. */
	
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String args[]) {

	// create a Car object 
	Car car = new Car("Mitsubishi", 2009,"H26C" , "20");
	
	Car newCar = null;
	
	File tempFile = null;
    
	//serialize the car
	try {
		tempFile = File.createTempFile("ExternalizeCar", ".txt");
		FileOutputStream fo = new FileOutputStream(tempFile);
	    ObjectOutputStream so = new ObjectOutputStream(fo);
	    System.out.format("Canonical filename: %s\n", tempFile.getCanonicalFile());
		
	    so.writeObject(car);
	    so.flush();
	    so.close();
	} catch (Exception e) {
	    System.out.println(e);
	    System.exit(1);
	}

	// de-serialize the Car
	try {
		if(tempFile!=null) {
			FileInputStream fi = new FileInputStream(tempFile);
		    ObjectInputStream si = new ObjectInputStream(fi);  	    
		    newCar = (Car) si.readObject();
		    si.close();
		}
	}
	catch (Exception e) {
	    System.out.println(e);
	    System.exit(1);
	}

	/* 
	 * Print out the original and new car information
	 */
	System.out.println("The original car is ");
	//calls toString method internally.
	System.out.println(car);
	System.out.println("The new car is ");
        System.out.println(newCar);
    }
}
