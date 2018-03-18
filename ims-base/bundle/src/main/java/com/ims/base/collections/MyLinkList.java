package com.ims.base.collections;

import org.apache.commons.lang3.StringUtils;

/**
 * Singly linked list. Similar implementation.
 * http://www.sanfoundry.com/java-program-implement-singly-linked-list/
 * 
 */

public class MyLinkList {

	private Node first;
	private Node last;

	/**
	 * Similar to list.add(element) method of actual linkedlist class.
	 */
	public void insertAtEnd(String s) {
		Node node = new Node();
		node.setData(s);
		node.setNext(null);

		if (first == null) {
			first = node;
			last = node;
		} else {
			last.setNext(node);
			last = node;
		}
	}

	public void insertAtStart(String s) {
		Node node = new Node();
		node.setData(s);
		node.setNext(null);

		if (first == null) {
			first = node;
			last = node;
		} else {
			node.setNext(first);
			first = node;
		}

	}

	public void insertAtPos(int pos, String s) {
		int i = 1;
		Node node = first;
		while (node != null) {
			if (i == pos) {
				Node nextNode = node.getNext();
				Node newNode = new Node();
				newNode.setData(s);
				newNode.setNext(nextNode);
				node.setNext(newNode);
				break;
			}
			node = node.getNext();
			i++;
		}

	}

	public void deleteAtPos(int pos) {
		Node node = first;
		int i = 1;
		while (node != null) {
			if (i == pos) {
				Node nextNode = node.getNext();
				node.setNext(nextNode.getNext());
				break;
			}
			node = node.getNext();
			i++;
		}
	}

	public String get(int pos) {
		Node node = first;
		String data = StringUtils.EMPTY;
		for (int i = 0; node != null; i++) {
			if (i == pos) {
				data = node.getData();
				break;
			}
			node = node.getNext();
		}
		return data;
	}

	public void display() {
		Node node = first;
		System.out.print("[");
		while (node != null) {
			System.out.print(node.getData() + ",");
			node = node.getNext();
		}
		System.out.println("]");
	}

}
