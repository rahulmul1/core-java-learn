package com.ims.base.collections;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class MyHashMap.
 * 
 * The hasmap is basically an array containing linklist in each element of the
 * array. Array is also called buckets. A particular hashcode maps to a
 * particular array element. The link list for that element all have same
 * hashcodes.
 * 
 */
public class MyHashMap {

	/** The Constant SIZE. */
	private static final int SIZE = 16;

	/** The table. */
	private EntryClass table[] = new EntryClass[SIZE];

	/**
	 * Gets the table.
	 * 
	 * Return non null array elements/buckets.
	 * 
	 * @return the table
	 */
	public List<EntryClass> getTable() {
		List<EntryClass> entryList = new ArrayList<>();
		for (EntryClass entry : table) {
			if (entry != null) {
				entryList.add(entry);
			}
		}
		return entryList;
	}

	/**
	 * Gets the.
	 * 
	 * Here we keep on iterating the link list of the bucket/array element
	 * untill we find the key. If we dont we return null.
	 * 
	 * 
	 * 
	 */
	public String get(String key) {
		int hashCode = key.hashCode() % 16;
		EntryClass entry = table[hashCode];
		if (entry != null) {
			while (entry != null) {
				if (entry.getKey().equals(key)) {
					return entry.getValue();
				}
				entry = entry.getNext();
			}
		}
		return null;
	}

	/**
	 * Put.
	 * 
	 * We take the mod of the hashcode of string key. Depending on the hashcode
	 * we identify the bucket in which the key value pair will go. Once the
	 * bucket is identified we create a link list if not present or if present
	 * traverse the link list and put the keyvalue pair in the end of the link
	 * list. Here entry is a block in link list which has key value and next
	 * pointer pointing to the next block in the link list.
	 * 
	 * 
	 */
	public void put(String key, String value) {
		int hashCode = key.hashCode() % 16;
		EntryClass entry = table[hashCode];
		EntryClass newEntry = new EntryClass();
		newEntry.setKey(key);
		newEntry.setValue(value);
		boolean flag = false;
		if (entry == null) {
			table[hashCode] = newEntry;
		} else {
			while (entry != null) {
				/**
				 * If we find key already present in the link list we update the
				 * key. Else keep on iterating.
				 */
				if (entry.getKey().equals(key)) {
					entry.setValue(value);
					flag = true;
					break;
				} else {
					entry = entry.getNext();
				}
			}
			if (flag == false)
				entry.setNext(newEntry);
		}
	}

}
