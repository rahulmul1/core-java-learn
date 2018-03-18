package com.ims.base.collections;

public class DoublyLinkListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Linklist start at 0 index");
		MyDoublyLinkList myLinkList = new MyDoublyLinkList();
		myLinkList.insertAtEnd("rahul1");
		myLinkList.insertAtEnd("rahul3");
		myLinkList.insertAtEnd("rahul4");
		System.out.println("------Insert at End/list.add(element)---------");
		myLinkList.display();
		
		System.out.println("------Insert at Start---------");
		myLinkList.insertAtStart("rahul0");
		myLinkList.display();
		
		System.out.println("------Insert at 2nd pos---------");
		myLinkList.insertAtPos(2, "rahul2");
		myLinkList.display();
		
		System.out.println("------Get at 3rd pos---------");
		String string = myLinkList.get(3);
		System.out.println(string);
		
		System.out.println("------After delete 3rd pos---------");
		myLinkList.deleteAtPos(3);
		myLinkList.display();
		
		System.out.println("------Get at 3rd pos---------");
		string = myLinkList.get(3);
		System.out.println(string);
		
	}

}
