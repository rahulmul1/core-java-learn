package com.ims.base.collections;

public class Node {

	private String data;
	/**
	 * Not used in singlylinkedlist. SO ignore for its implementatiom.
	 * Used in doubly linked list.
	 */
	 private Node previous; 
	private Node next;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
	 
	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}
