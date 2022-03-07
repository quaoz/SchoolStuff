package com.github.quaoz.scripts;

import com.github.quaoz.common.datastructures.linkedlist.Node;
import com.github.quaoz.common.datastructures.linkedlist.LinkedList;

public class LinkedListTest {
	public static void main(String[] args) {
		Node<Integer> n1 = new Node<>(13);
		Node<Integer> n2 = new Node<>(7);
		Node<Integer> n3 = new Node<>(68);

		n1.display();
		n2.display();
		n3.display();

		LinkedList<Integer> linkedList = new LinkedList<>();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(5);

		linkedList.set(1, 10);

		System.out.println(linkedList.get(1));
		System.out.println(linkedList.get(0));
	}
}
