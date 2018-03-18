package com.ims.base.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * We don’t need to do type-casting and we can remove ClassCastException at
 * runtime. If we don’t provide the type at the time of creation, compiler will
 * produce a warning that “GenericsType is a raw type.
 * 
 * @param <T>
 */
class GenericType<T> {

	T object;
	
	public GenericType() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Type safe constructor.
	 */
	public GenericType(T obj) {
		this.object = obj;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	/**
	 * 
	 * Type safe isEqual() method. Generic method.
	 * 
	 * Type inference: We can invoke them like a normal method. Java compiler is
	 * smart enough to determine the type of variable to be used, this facility
	 * is called as type inference. <T> after static is for the method params.
	 */
	public static <T> boolean isEqual(GenericType<T> obj1, GenericType<T> obj2) {
		return obj1.getObject().equals(obj2.getObject());
	}

	/**
	 * Non generic normal method.
	 */
	public static boolean isEqualNonGen(GenericType obj1, GenericType obj2) {
		return obj1.getObject().equals(obj2.getObject());
	}

	/**
	 * Generic list as method parameter.
	 */
	public static double sum(List<Number> list) {
		double sum = 0;
		for (Number n : list) {
			sum += n.doubleValue();
		}
		return sum;
	}

	/**
	 * Next two method explain Difference between List<Object> and raw type List
	 * in Java?
	 */
	public static double sumRawType(List list) {
		double sum = 0;

		for (Object o : list) {
			Integer i = (Integer) o;
		}
		return sum;
	}
	/**
	 * sum of object type list.
	 */
	public static double sumObjectType(List<Object> list) {
		double sum = 0;

		for (Object o : list) {
			Integer i = (Integer) o;
		}
		return sum;
	}
	
	/**
	 * Generics Upper Bounded Wildcard
	 */
	public static double sumUpperBoundNumber(List<? extends Number> list) {
		double sum = 0;
		/**
		 * Cant assign in upper bound . Read only.
		 */
		// list.add(new Integer(1));
		// list.add(new Double(1.0));
		for (Number n : list) {
			sum += n.doubleValue();
		}
		return sum;
	}

	/**
	 * Generic lower bound.
	 */
	public static Object sumLowerBoundInteger(List<? super Integer> list) {
		/**
		 * Can assign the lowest class object to list. Line 1 dosnt compile and
		 * next line does. Next line compiles coz a Integer will always be an
		 * object of any of the superclass of it. BUt number cant be assigned
		 * coz Consider List<? super Integer> l = new ArrayList<Integer>(); Now
		 * adding number should not be allowed in this case. Hence only Double
		 * can be added in lowerbound.
		 */
		Number n1 = new Integer(1);
		Integer n2 = new Integer(1);
		// l.add(n1); //Line 1- won't compile
		list.add(n2); // will compile
		
		Integer sum = 0;
		for (Object n : list) {

		}
		return sum;
	}
	

	/**
	 * Type erasure error.
	 * 
	 * Try uncommenting the below sum method. It will give compiler error below.
	 * CE: Method sum(List<? extends Number>) has the same erasure sum(List<E>) as
	 * another method in type GenericType<T>
	 * 
	 */
	/*public static double sum(List<? extends Number> list) {

	}*/
	/**
	 * Type erasure Not allowed coz of type erasure. Same as above reason
	 * 
	 */
	/*
	 * public static double sum(List<String> list) { }
	 */
}


/**
 * The Class GenericTest.
 */
public class GenericTest {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		/**
		 * Using setobject method to pass object of string type to instance var
		 * of class
		 */
		GenericType<String> genObj1 = new GenericType<>();
		genObj1.setObject("generic");
		/**
		 * Using constructor to create instance var of string type in class
		 */
		GenericType<String> genObj2 = new GenericType<>("generic");
		/**
		 * Type inference. See diamond notation <>.
		 */
		GenericType<Integer> genObj3 = new GenericType<>(10);

		/**
		 * Type safety :The method setObject(String) in the type
		 * GenericType<String> is not applicable for the arguments (int)
		 */
		// genObj1.setObject(10);
		/**
		 * Type casting: No need to type cast.Prevents casting exception.
		 */
		System.out.println(genObj1.getObject());

		/**
		 * Generic method example: isEqual() method call
		 */
		boolean equal = GenericType.isEqual(genObj1, genObj2);
		System.out.println(equal);
		/**
		 * Causes compilation error coz we cant pass an integer
		 */
		// boolean equal = GenericType.isEqual(genObj1, genObj3);
		/**
		 * Allows us to pass any objtype of generictype class. We are passing
		 * one integer object and one string obj which will produce runtime
		 * exception.
		 */
		boolean equalNonGen = GenericType.isEqualNonGen(genObj1, genObj3);
		System.out.println(equalNonGen);

		/**
		 * Since we have not declared generic class object type we can pass any
		 * parameter type. While casting it may lead to classcastex as below.
		 */
		GenericType obj2 = new GenericType();
		obj2.setObject(10);
		// String s1 = (String) obj2.getObject();

		/**
		 * Polymorphism applies only to base types. Not to parameter generic
		 * types. CE: Type mismatch: cannot convert from ArrayList<Integer> to
		 * List<Number>
		 */
		// List<Number> foo4 = new ArrayList<Integer>();
		/**
		 * Polymorphism not applicable for parameterirized types in method call
		 * also. CE: The method sum(List<Number>) in the type GenericType is not
		 * applicable for the arguments (List<Integer>)
		 */
		List<Integer> listInteger1 = new ArrayList<>();
		listInteger1.add(1);
		listInteger1.add(2);
		// GenericType.sum(listInteger1);
		/**
		 * However this can be done as its equivalent to Number n = new
		 * Integer(3). We can add any type of object which is a subclass of
		 * Number to listNumber list.
		 */
		List<Number> listNumber = new ArrayList<Number>();
		listNumber.add(3);
		listNumber.add(new Integer(3));
		listNumber.add(new Double(3.0));
		Number number = listNumber.get(1);
		System.out.println(number);
		/**
		 * However since Double is not child of Integer you cant do this.
		 * CE: The method add(Integer) in the type List<Integer> is not applicable
		 * for the arguments (Double)
		 */
		List<Double> listDouble = new ArrayList<>();
		listDouble.add(new Double(2.0));
//		listDouble.add(new Integer(2));
//		listDouble.add(2);

		/**
		 * Difference between List<Object> and raw type List in Java? you can
		 * pass any parameterized type to raw type List but you can not pass
		 * List<String> to any method which accept List<Object> it will result
		 * in compilation error.
		 * sumObjectType will take only object parameterized list.
		 */
		GenericType.sumRawType(listNumber);
		GenericType.sumRawType(listDouble);
//		 GenericType.sumObjectType(listNumber);

		/**
		 * Generics Upper Bounded Wildcard
		 * Passing integer list to upperbound number list.
		 */
		System.out.println("sum1 method call ,sum= " + GenericType.sumUpperBoundNumber(listInteger1));

		/**
		 *Generic lower bounded wildcard.
		 *Passing number list to lowebound integer list.
		 */
		List<? super Integer> listNumber1 = new ArrayList<Number>();
		System.out.println("sum1 method call ,sum= " + GenericType.sumLowerBoundInteger(listNumber1));


	}
}