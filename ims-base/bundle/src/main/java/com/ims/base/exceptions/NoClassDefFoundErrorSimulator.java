package com.ims.base.exceptions;

/**
 * NoClassDefFoundErrorSimulator
 * 
 * @author Pierre-Hugues Charbonneau
 * 
 *         As you can see, the first attempt to create a new instance of ClassA
 *         did trigger a java.lang.ExceptionInInitializerError. This As you can
 *         see, attempt #2 and attempt #3 both generated a
 *         java.lang.NoClassDefFoundError, why? Static initializers are
 *         guaranteed to be executed only once in the JVM life cycle at class
 *         loading time . So the first time we tried instantiating it tried
 *         loading the class. But the class failed to load as static block threw
 *         exception. Well since the first attempt failed, class loading of
 *         ClassA was prevented. Successive attempts to create a new instance of
 *         ClassA within the current ClassLoader did generate
 *         java.lang.NoClassDefFoundError over and over since ClassA was not
 *         found within current ClassLoader.
 * 
 * 
 */
public class NoClassDefFoundErrorSimulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out
				.println("java.lang.NoClassDefFoundError Simulator - Training 2");
		System.out.println("Author: Pierre-Hugues Charbonneau");
		System.out.println("http://javaeesupportpatterns.blogspot.com\n\n");

		try {
			// Create a new instance of ClassA (attempt #1)
			System.out
					.println("FIRST attempt to create a new instance of ClassA...\n");
			ClassA classA = new ClassA();

		} catch (Throwable any) {
			any.printStackTrace();
		}

		try {
			// Create a new instance of ClassA (attempt #2)
			System.out
					.println("\nSECOND attempt to create a new instance of ClassA...\n");
			ClassA classA = new ClassA();

		} catch (Throwable any) {
			any.printStackTrace();
		}

		try {
			// Create a new instance of ClassA (attempt #3)
			System.out
					.println("\nTHIRD attempt to create a new instance of ClassA...\n");
			ClassA classA = new ClassA();

		} catch (Throwable any) {
			any.printStackTrace();
		}

		System.out.println("\n\ndone!");
	}
}