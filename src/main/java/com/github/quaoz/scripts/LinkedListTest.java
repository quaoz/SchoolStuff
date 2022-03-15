package com.github.quaoz.scripts;

import com.github.quaoz.common.datastructures.LinkedList;
import com.github.quaoz.common.datastructures.Node;

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

		System.out.println();

		for (Integer integer : linkedList) {
			System.out.println(integer);
		}

		System.out.println();

		linkedList.set(1, 10);

		System.out.println(linkedList.get(1));
		System.out.println(linkedList.get(0));
		System.out.println();

		linkedList.add(1, -10);
		linkedList.addFirst(2000);
		linkedList.remove(3);

		for (Integer integer : linkedList) {
			System.out.println(integer);
		}

		System.out.println();
		System.out.println(linkedList.get(3));
		linkedList.remove(3);
		System.out.println(linkedList.get(3));
		System.out.println();

		System.out.println("\n2000 at " + linkedList.indexOf(2000));
		System.out.println(linkedList.remove(linkedList.indexOf(2000)));
		System.out.println("\n2000 at " + linkedList.contains(2000));

		LinkedList<Integer> list = new LinkedList<>(new Integer[] {1, 2, 3, 4});
	}
}
