package com.github.quaoz.common.datastructures.linkedlist;

import com.github.quaoz.common.datastructures.interpreter.Interpreter;

public class LinkedList<E> implements Interpreter<E> {
	private Node<E> head;

	public LinkedList() {
		head = null;
	}

	public void add(E value) {
		Node<E> node = new Node<>(value);

		if (head == null) {
			// If the node has no head value initialise it to the parsed value
			head = node;
		} else {
			// If the head is set step through the list until an unlinked node is found
			Node<E> current = head;

			while (current.getNext() != null) {
				current = current.getNext();
			}

			current.setNext(node);
		}
	}

	@Override
	public E get(int index) {
		int pos = 0;
		Node<E> current = head;

		while (pos != index) {
			if (current.getNext() != null) {
				current = current.getNext();
				pos++;
			} else {
				throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for list of length %d", index, pos));
			}
		}

		return current.getValue();
	}

	@Override
	public E set(int index, E element) {
		int pos = 0;
		Node<E> current = head;

		while (pos != index) {
			if (current.getNext() != null) {
				current = current.getNext();
				pos++;
			} else {
				throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for list of length %d", index, pos));
			}
		}

		return current.setValue(element);
	}

	@Override
	public int size() {
		Node<E> current = head;
		int size = 0;

		while (current.getNext() != null) {
			current = current.getNext();
			size++;
		}

		return size;
	}
}
