/**
 * Copyright 2012 Richemont
 */
package com.ims.base.hashutils;

import java.util.HashMap;
import java.util.Map;

/**
 * Compile and run the above code, the output result is
 * 
 * null
 * 
 * What is wrong? The two instances of EqualsHashCodeIncorrect are logically equal according
 * to the class's equals method. Because the hashCode() method is not
 * overridden, these two instances' identities are not in common to the default
 * hashCode implementation. Therefore, the Object.hashCode returns two seemingly
 * random numbers instead of two equal numbers. Such behavior violates
 * "Equal objects must have equal hash codes" rule defined in the hashCode
 * contract.
 * 
 * @author ragga7
 */
public class EqualsHashCodeIncorrect {
	private long crmID;
	private int nameSpace;

	public EqualsHashCodeIncorrect(long crmID, int nameSpace) {
		super();
		this.crmID = crmID;
		this.nameSpace = nameSpace;
	}

	public boolean equals(Object obj) {
		// null instanceof Object will always return false
		if (!(obj instanceof EqualsHashCodeIncorrect))
			return false;
		if (obj == this)
			return true;
		return this.crmID == ((EqualsHashCodeIncorrect) obj).crmID
				&& this.nameSpace == ((EqualsHashCodeIncorrect) obj).nameSpace;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		Map m = new HashMap();
		m.put(new EqualsHashCodeIncorrect(2345891234L, 0), "Jeff Smith");
		System.out.println(m.get(new EqualsHashCodeIncorrect(2345891234L, 0)));
	}

}