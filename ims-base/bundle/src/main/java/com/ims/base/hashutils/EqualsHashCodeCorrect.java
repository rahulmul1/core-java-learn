/**
 * Copyright 2012 Richemont
 */
package com.ims.base.hashutils;

import java.util.HashMap;
import java.util.Map;

/**
 * Both equal and hashcode methods overrriden from object class. Hence works
 * well in case of collections.
 * 
 * @author ragga7
 */
public class EqualsHashCodeCorrect {
	private long crmID;
	private int nameSpace;

	public EqualsHashCodeCorrect(long crmID, int nameSpace) {
		super();
		this.crmID = crmID;
		this.nameSpace = nameSpace;
	}

	public boolean equals(Object obj) {
		// null instanceof Object will always return false
		if (!(obj instanceof EqualsHashCodeCorrect))
			return false;
		if (obj == this)
			return true;
		return this.crmID == ((EqualsHashCodeCorrect) obj).crmID
				&& this.nameSpace == ((EqualsHashCodeCorrect) obj).nameSpace;
	}

	public int hashCode() {
		int result = 0;
		result = (int) (crmID / 12) + nameSpace;
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {

		Map m = new HashMap(10, 1.0f);
		m.put(new EqualsHashCodeCorrect(2345891234L, 0), "Jeff Smith");
		System.out.println(m.get(new EqualsHashCodeCorrect(2345891234L, 0)));
	}
}