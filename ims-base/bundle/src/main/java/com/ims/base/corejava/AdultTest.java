package com.ims.base.corejava;

@SuppressWarnings("serial")
class NotAdultException extends Exception{
	public NotAdultException() {
		super();
	}
	NotAdultException(String msg){
		super(msg);
	}
}

class Adult{
	int age;
	public Adult(int age) {
		this.age=age;
	}
	/**
	 * Mandatory to throw the user defined exception or to handle it here only.
	 * We can throw any unchecked exception.Compiler dosnt force us to handle it.
	 */
	public void testAdult() throws NotAdultException, NullPointerException{
		if(age<18){
			System.out.println("In method ");
			throw new NotAdultException("I am not a adult");
		}else{
			System.out.println("Adult");
		}
		
	}
}

public class AdultTest{
	public static void main(String[] args) {
		Adult adult  = new Adult(10);
		Adult adult2 = null;
		try {
			adult.testAdult();
			if(adult2==null){
				System.out.println("inside null");
				/**
				 * we can also throw runtime ex and compiler dosnt promt to 
				 * handle it. Its our responsibility.
				 */
				throw new NullPointerException();
			}
			/**
			 * Compulsary to handle user defined exception.
			 */
		} catch (NotAdultException e) {
			System.out.println("In catch exception block");
			System.out.println(e.getMessage());
		}/*catch (NullPointerException e) {
			e.printStackTrace();
		}*/
	
	}
}

