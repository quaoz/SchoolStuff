package com.github.quaoz.common.datastructures.linkedlist;

import com.github.quaoz.common.datastructures.interpreter.Interpreter;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class LinkedList<E> implements Interpreter<E>, Iterable<E> {
	private int size;
	private Node<E> head;

	public LinkedList() {
		head = null;
		size = 0;
	}

	public LinkedList(E @NotNull [] array) {
		Node<E> node = new Node<>(array[0]);
		size = array.length;
		head = node;

		for (E e : array) {
			node.setNext(new Node<>(e));
			node = node.getNext();
		}
	}

	public LinkedList(@NotNull List<E> list) {
		Node<E> node = new Node<>(list.get(0));
		size = list.size();
		head = node;

		for (E e : list) {
			node.setNext(new Node<>(e));
			node = node.getNext();
		}
	}

	public <T extends Iterable<E>> LinkedList(@NotNull T iterable) {
		Iterator<E> iterator = iterable.iterator();
		Node<E> node = new Node<>(iterator.next());
		head = node;
		size = 0;

		while (iterator.hasNext()) {
			node.setNext(new Node<>(iterator.next()));
			node = node.getNext();
			size++;
		}
	}

	public void add(E value) {
		Node<E> node = new Node<>(value);
		size++;

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

		// TODO: compare index to size

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

		// TODO: compare index to size

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

		// TODO: return size instead of checking


		while (current.getNext() != null) {
			current = current.getNext();
			size++;
		}

		return size;
	}

	@NotNull
	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<E> {
		private Node<E> node = head;

		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public E next() {
			E value = node.getValue();
			node = node.getNext();

			return value;
		}
	}
}
