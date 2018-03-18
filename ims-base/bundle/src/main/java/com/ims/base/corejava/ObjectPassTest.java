package com.ims.base.corejava;

/**
 * Java passes everything by value. ------------------------------ Now it is
 * like the pass was by reference! But the thing is what we pass exactly is a
 * handle of an object, and in the called method a new handle created and
 * pointed to the same object. Now when more than one handles tied to the same
 * object, it is known as aliasing. Both object references p and a contain the
 * same addres in stack and the address is of the object ObjectPass in Heap.
 * 
 * @author NK
 * 
 */
public class ObjectPassTest {

	public static void main(String[] args) {
		/**
		 * Eventhough the value is modified in the method increment it is
		 * reflected in the object p.
		 */
		ObjectPass p = new ObjectPass();
		p.value = 5;
		System.out.println("Before calling: p.value=" + p.value);
		ObjectPass.increment(p);
		System.out.println("After calling: p.value=" + p.value);
		System.out.println(p);

		/**
		 * Objects in java are mutable remember even if they contain instance
		 * var string or wrapper classes(immutable obj) they act as mutable
		 * objects as one complete block. ie. if 2 reference to the same object
		 * and one reference modifies the object , the other reference will also
		 * have the modified value.
		 */

		ObjectPass q = p;
		System.out.println("Before calling: p.x=" + p.x);
		System.out.println("Before calling: q.x=" + q.x);

		q.change();

		System.out.println("After calling: q.x=" + q.x);
		System.out.println("After calling: p.x=" + p.x);

		/**
		 * Again the value changed by the reference var inside foo is reflected
		 * in the object p.
		 * 
		 * Do run the code after uncommenting the first line in foo also.A new
		 * object is created hence no change in p in that case.
		 */
		System.out.println("Before calling foo: p.value=" + p.value);
		System.out.println("Before calling foo: p.x=" + p.x);
		ObjectPass.foo(p);
		System.out.println("After calling foo: p.value=" + p.value);
		System.out.println("After calling foo: p.x=" + p.x);
	}

}
