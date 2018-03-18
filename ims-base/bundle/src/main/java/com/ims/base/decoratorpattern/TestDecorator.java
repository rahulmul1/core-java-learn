package com.ims.base.decoratorpattern;

// TODO: Auto-generated Javadoc
/**
 * The Class TestDecorator.
 */
public class TestDecorator {

	/**
	 * The main method.
	 * 
	 * Output shows that nuttydecorator and honeydecorator act as wrappers over simple 
	 * icecream. They add additional functionality to simple icecream.
	 * 
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		Icecream simpleIcecream = new SimpleIcecream();
		Icecream nuttIcecream = new NuttyDecorator(simpleIcecream);
		System.out.println(nuttIcecream.makeIcecream());
		
		Icecream honeIcecream =  new HoneyDecorator(nuttIcecream);
		System.out.println(honeIcecream.makeIcecream());
		
		honeIcecream = new HoneyDecorator(simpleIcecream );
		System.out.println(honeIcecream.makeIcecream());
	}

}
