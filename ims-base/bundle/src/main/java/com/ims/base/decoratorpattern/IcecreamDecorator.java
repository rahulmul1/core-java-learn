package com.ims.base.decoratorpattern;

// TODO: Auto-generated Javadoc
/**
 * The Class IcecreamDecorator.
 */
abstract class IcecreamDecorator implements Icecream {

	/** The special icecream. */
	protected Icecream specialIcecream;

	/**
	 * Instantiates a new icecream decorator.
	 *
	 * @param specialIcecream the special icecream
	 */
	public IcecreamDecorator(Icecream specialIcecream) {
		this.specialIcecream = specialIcecream;
	}

	/* (non-Javadoc)
	 * @see com.ims.base.decoratorpattern.Icecream#makeIcecream()
	 */
	public String makeIcecream() {
		return specialIcecream.makeIcecream();
	}
}
