package com.ims.base.decoratorpattern;

// TODO: Auto-generated Javadoc
/**
 * The Class HoneyDecorator.
 */
public class HoneyDecorator extends IcecreamDecorator {

	/**
	 * Instantiates a new honey decorator.
	 *
	 * @param specialIcecream the special icecream
	 */
	public HoneyDecorator(Icecream specialIcecream) {
		super(specialIcecream);
	}

	/* (non-Javadoc)
	 * @see com.ims.base.decoratorpattern.IcecreamDecorator#makeIcecream()
	 */
	public String makeIcecream() {
		return specialIcecream.makeIcecream() + addHoney();
	}

	/**
	 * Adds the honey.
	 *
	 * @return the string
	 */
	private String addHoney() {
		return " + sweet honey";
	}
}