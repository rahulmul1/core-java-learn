package com.ims.base.collections;

public class TestMyHashMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyHashMap myHashMap = new MyHashMap();
		myHashMap.put("rahul", "dibiya");
		myHashMap.put("karan", "neha");
		myHashMap.put("ajat", "sheena");
		myHashMap.put("tarun", "tine");
		myHashMap.put("rahul", "divya bansali");
		
		for (EntryClass entryClass : myHashMap.getTable()) {
			System.out.println(entryClass);
		}
		
	}

}
