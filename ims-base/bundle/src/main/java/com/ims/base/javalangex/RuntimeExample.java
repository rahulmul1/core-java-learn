package com.ims.base.javalangex;

public class RuntimeExample {

	public static void main(String args[]) {
		Runtime r = Runtime.getRuntime();
		long mem1, mem2;
		Integer someints[] = new Integer[1000];
		/**
		 * This fn tells the total space in java heap memory
		 */
		System.out.println("Total memory is: " + r.totalMemory());
		/**
		 * This fn tell the free space in heap.
		 */
		mem1 = r.freeMemory();
		System.out.println("Initial free memory: " + mem1);
		/**
		 * Forces the garbage collection.
		 */
		r.gc();
		/**
		 * This fn tell the free space in heap.
		 */
		mem1 = r.freeMemory();
		System.out.println("Free memory after garbage collection: " + mem1);
		
		for (int i = 0; i < 1000; i++)
			someints[i] = new Integer(i); // allocate integers
		mem2 = r.freeMemory();
		System.out.println("Free memory after allocation: " + mem2);
		System.out.println("Memory used by allocation: " + (mem1 - mem2));
		// discard Integers
		
		for (int i = 0; i < 1000; i++)
			someints[i] = null;
		r.gc(); // request garbage collection
		mem2 = r.freeMemory();
		System.out.println("Free memory after collecting"
				+ " discarded Integers: " + mem2);
	}
}
