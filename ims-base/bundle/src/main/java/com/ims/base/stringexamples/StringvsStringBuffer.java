package com.ims.base.stringexamples;


// TODO: Auto-generated Javadoc
/**
 * The Class StringvsStringBuffer.
 * Demonstrates difference b.w string/immutable and stringbuffer/stringbuilder/mutable.
 * Does not demonstrate pass by value.
 * 
 * Stringbuffer behaves like any other mutable object(generaly all objects are
 * mutable in java). A reference of the object is passed. And if one reference
 * is modified then all other references get modified. 
 * However in case of string
 * which is a immutable object if a reference is changed then the old reference
 * keeps pointing to the old value in the string pool. while for the modified
 * refrence a new object is created and assigned to it.
 * 
 */
public class StringvsStringBuffer {

	/**
	 * Change.
	 *
	 * @param in the in
	 */
	static void change(String in) { 
		  in = in + " changed"; //line2
		}

		/**
		 * Change.
		 *
		 * @param in the in
		 */
		static void change(StringBuffer in) {
		  in.append(" changed"); 	//line1
		}

		/**
		 * The main method.
		 *
		 * @param args the arguments
		 */
		public static void main(String[] args) {
		   StringBuffer sb = new StringBuffer("value");
		   String str = "value";
		   change(sb);	
		   change(str);
		   System.out.println("StringBuffer: "+sb);
		   System.out.println("String: "+str);
		   
		   /**
		    * concat vs append
		    */
		   String s1= new String("Durga");
		   s1.concat("Soft");
		   System.out.println("String: "+s1);
		   
		   StringBuffer s2= new StringBuffer("Durga");
		   s2.append("Soft");
		   System.out.println("StringBuffer: "+s2);
		}

}


/**
 * Consider the stack heap diag below for string vs stringbuffer
 * 
 * heap
 * 1000x: value		(after line1)value changed
 * 2000x: value
 * 3000x: (after line2)value changed            /**original string is never modified 
 * 													a new string created as string is immutable
 * 
 * main stack
 * sb:1000x
 * str:2000x
 * 
 * 
 * change(string) stack
 * in:2000x   (after line2)3000x 
 * 
 * 
 * change(stringbuffer) stack
 * in:1000x
 * 
 * 
 * 
 */










