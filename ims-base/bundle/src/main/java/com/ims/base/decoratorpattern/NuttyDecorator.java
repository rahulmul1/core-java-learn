package com.ims.base.decoratorpattern;

// TODO: Auto-generated Javadoc
/**
 * The Class NuttyDecorator.
 */
public class NuttyDecorator extends IcecreamDecorator {

	/**
	 * Instantiates a new nutty decorator.
	 *
	 * @param specialIcecream the special icecream
	 */
	public NuttyDecorator(Icecream specialIcecream) {
		super(specialIcecream);
	}

	/* (non-Javadoc)
	 * @see com.ims.base.decoratorpattern.IcecreamDecorator#makeIcecream()
	 */
	public String makeIcecream() {
		return specialIcecream.makeIcecream() + addNuts();
	}

	/**
	 * Adds the nuts.
	 *
	 * @return the string
	 */
	private String addNuts() {
		return " + cruncy nuts";
	}
}
