/**
 * 
 */
package com.ims.base.constants;

/**
 * @author NK
 *
 */
public enum ApplicationConstants {
	USER_ID("userid"),
	PWD("password");
	
	private final String value;
	 
	private ApplicationConstants(final String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
