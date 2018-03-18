package com.ims.base.hackerearth;

import java.util.ArrayList;
import java.util.List;

class Node{
	Integer x;
	Node next;
	
}

public class LinkedListLoop {
	
	public static void main(String[] args) {
		Node[] node = new Node[10];
		for (int i = 0; i < node.length; i++) {
			node[i] = new Node();
			node[i].x = i;
		}
		
		for (int i = 0; i < node.length; i++) {
			if(node[i+1]!=null){
				node[i].next=node[i+1];
			}
			node[6].next = node[3];
		}
		
		Node temp = node[0];
		
		while(temp!=null){
			Node slow = temp;
			Node fast=null;
			if(temp.next!=null){
				 fast = temp.next;	
			}
			
		}
	}
	
}
