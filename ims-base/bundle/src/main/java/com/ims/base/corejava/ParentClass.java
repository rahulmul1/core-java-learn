package com.ims.base.corejava;

public class ParentClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ParentClass parentClass = new SubChildClass();
		/**
		 * instance of method prevents classcastexecption.
		 */
		if (parentClass instanceof ChildClass) {
			ChildClass childClass = (ChildClass) parentClass;
			System.out.println(childClass);
		}
		
		ParentClass parentClass2  = new ParentClass();
		if(parentClass2 instanceof ChildClass){
			ChildClass childClass = (ChildClass)parentClass2;
		}else{
			System.out.println("Cannot be type casted");
		}

	}

}

class ChildClass extends ParentClass {

}

class SubChildClass extends ChildClass {

}