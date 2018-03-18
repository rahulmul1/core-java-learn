package com.ims.base.corejava;
/**
 * Static var used to count the number of object or customers.
 * An instance var cant be used to count the no.
 * 
 */
public class StaticCounter {

	/**
	 * initialized only once on class load in one execution of the program.
	 */
	static int count = 0;// will get memory only once and retain its value
	int instanceVar = 0;

	/**
	 * Called only once on class load.
	 */
	static{
		int t=1;
		System.out.println("Inside static block before main()");
		count=10;
	}
	
	StaticCounter() {
		System.out.println("Inside constructor");
		instanceVar++;
		count++;
/*		System.out.println("Count = "+count);
		System.out.println("instance Var = "+instanceVar);
*/	}

	/**
	 * Also comment all lines in main and test.Static blocks execute of the class
	 * which has main method even though main is empty.
	 */
	public static void main(String args[]) {
		System.out.println("Inside main()");

		StaticCounter c1 = new StaticCounter();
		StaticCounter c2 = new StaticCounter();
		StaticCounter c3 = new StaticCounter();
		System.out.println("Count = "+ count);
		System.out.println("c1 instance Var = "+c1.instanceVar);
		System.out.println("c2 instance Var = "+c2.instanceVar);
		System.out.println("c3 instance Var = "+c3.instanceVar);

	}
	/**
	 * static block executes b4 main() even if writn after it.
	 */
	static{
		System.out.println("Inside static block after main()");
		count=100;
	}
}
