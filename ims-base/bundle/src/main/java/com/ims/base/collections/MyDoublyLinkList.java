package com.ims.base.collections;

import org.apache.commons.lang3.StringUtils;

/**
 * Similar implementaion
 * http://www.sanfoundry.com/java-program-implement-doubly-linked-list/
 * 
 * 
 */
public class MyDoublyLinkList {

	private Node start;
	private Node end;

	public void insertAtEnd(String s) {
		Node node = new Node();
		node.setData(s);
		if (start == null) {
			start = node;
			end = node;
		} else {
			node.setPrevious(end);
			end.setNext(node);
			end = node;

		}
	}

	public void insertAtStart(String s) {
		Node node = new Node();
		node.setData(s);
		if (start == null) {
			start = node;
			end = node;
		} else {
			node.setNext(start);
			start.setPrevious(node);
			start = node;
		}
	}

	/**
	 * For inseting the node at a pos we need the node before that position and
	 * the node at that position. Then we just have to link the new node to both
	 * these nodes while inseting our new node in between these 2 nodes.
	 * 
	 * 
	 */

	public void insertAtPos(int pos, String s) {
		int i = 1;
		Node newNode = new Node();
		newNode.setData(s);
		Node node = start;

		while (node != null) {
			if (i == pos) {
				Node nextNode = node.getNext();
				newNode.setPrevious(node);
				newNode.setNext(nextNode);
				node.setNext(newNode);
				nextNode.setPrevious(newNode);
				break;

			}

			node = node.getNext();
			i++;
		}

	}

	/**
	 * For inseting the node at a pos we need the node after that position as we
	 * are iterating from the back of the list. and the node at that position.
	 */

	public void insertAtPosFromEnd(int pos, String s) {
		int i = 1;
		Node newNode = new Node();
		newNode.setData(s);
		Node node = end;

		while (node != null) {
			if (i == pos) {
				Node prevNode = node.getPrevious();
				newNode.setNext(node);
				newNode.setPrevious(prevNode);
				prevNode.setNext(newNode);
				node.setPrevious(newNode);
				break;
			}

			node = node.getPrevious();
			i++;
		}

	}

	/**
	 * Traverse till the node b4 the pos of the node to be removed(node 1).
	 * Find the node to be removed(node 2) and the node after the node to be removed(node 3).
	 * Modify the pointers such that next of node 1 points to node 3 and 
	 * previous of node 3 points to node1. 
	 * 
	 */
	public void deleteAtPos(int pos) {
		int i = 1;
		Node node = start;
		while (node != null) {
			if (i == pos) {
				Node nextNode = node.getNext();
				Node nextToNextNode = nextNode.getNext();
				node.setNext(nextToNextNode);
				nextToNextNode.setPrevious(node);
				break;
			}

			node = node.getNext();
			i++;
		}
	}

	public String get(int pos) {
		Node node = start;
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
		Node node = start;
		System.out.print("[");
		while (node != null) {
			System.out.print(node.getData() + ",");
			node = node.getNext();
		}
		System.out.println("]");
	}

}
