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
		add(-1, value);
	}

	public void add(int index, E value) {
		// Create a new node from the parsed value
		Node<E> node = new Node<>(value);

		if (index > size) {
			throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for list of length %d", index, size));
		} else if (index == -1) {
			// Adds the node at the first available position
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
		} else {
			Node<E> current = head;
			int pos = 1;

			// Step through the list until the position is reached
			while (pos < index) {
				current = current.getNext();
				pos++;
			}

			// Insert the node
			node.setNext(current.getNext());
			current.setNext(node);
		}

		size++;
	}

	public void addFirst(E value) {
		// Create a new node from the parsed value
		Node<E> node = new Node<>(value);
		size++;

		// Replace the head value
		node.setNext(head);
		head = node;
	}

	@Override
	public E get(int index) {
		Node<E> current = head;
		int pos = 0;

		if (index > size) {
			throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for list of length %d", index, size));
		} else {
			// Step through the list until the position is reached
			while (pos != index) {
				current = current.getNext();
				pos++;
			}
		}

		return current.getValue();
	}

	public boolean contains(E value) {
		return indexOf(value) != -1;
	}

	public int indexOf(E value) {
		Node<E> current = head;
		int pos = 0;

		while (current.getNext() != null) {
			if (current.getValue() == value) {
				return pos;
			} else {
				current = current.getNext();
				pos++;
			}
		}

		return -1;
	}

	@Override
	public E set(int index, E element) {
		Node<E> current = head;
		int pos = 0;

		if (index > size) {
			throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for list of length %d", index, size));
		} else {
			// Step through the list until the position is reached
			while (pos != index) {
				current = current.getNext();
				pos++;
			}
		}

		return current.setValue(element);
	}

	public void clear() {
		head = null;
	}

	public @NotNull Node<E> remove(int index) {
		Node<E> current = head;
		int pos = 0;

		if (index > size) {
			throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for list of length %d", index, size));
		} else {
			size--;

			// Step through the list until the position is reached
			while (pos != index - 1) {
				current = current.getNext();
				pos++;
			}

			// Remove the node by setting the current nodes next to the removed nodes next
			Node <E> removed = current.getNext();
			current.setNext(removed.getNext());
			return removed;
		}
	}

	@Override
	public int size() {
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
