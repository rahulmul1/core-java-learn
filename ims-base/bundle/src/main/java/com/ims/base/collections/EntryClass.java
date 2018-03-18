package com.ims.base.collections;

// TODO: Auto-generated Javadoc
/**
 * The Class EntryClass.
 * 
 * A block in link list. Containing key value and pointer pointing to the next
 * element in the link list.
 * 
 */
public class EntryClass {

	/** The key. */
	private String key;
	
	/** The value. */
	private String value;
	
	/** The next. */
	private EntryClass next;
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public EntryClass getNext() {
		return next;
	}
	
	/**
	 * Sets the next.
	 *
	 * @param next the new next
	 */
	public void setNext(EntryClass next) {
		this.next = next;
	}
	

}
