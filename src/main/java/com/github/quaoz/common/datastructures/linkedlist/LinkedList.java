package com.github.quaoz.common.datastructures.linkedlist;

import com.github.quaoz.common.datastructures.interpreter.Interpreter;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class LinkedList<E> implements Interpreter<E>, Iterable<E> {
	private int size;
	private Node<E> head;

	/**
	 * Constructs an empty linked list
	 */
	public LinkedList() {
		head = null;
		size = 0;
	}

	/**
	 * Constructs linked list with the elements of an array
	 *
	 * @param array The array to use
	 */
	public LinkedList(E @NotNull [] array) {
		Node<E> node = new Node<>(array[0]);
		size = array.length;
		head = node;

		for (E e : array) {
			node.setNext(new Node<>(e));
			node = node.getNext();
		}
	}

	/**
	 * Constructs linked list with the elements of a list
	 *
	 * @param list The list to use
	 */
	public LinkedList(@NotNull List<E> list) {
		Node<E> node = new Node<>(list.get(0));
		size = list.size();
		head = node;

		for (E e : list) {
			node.setNext(new Node<>(e));
			node = node.getNext();
		}
	}

	/**
	 * Constructs a linked list using the elements of an iterable object
	 *
	 * @param iterable The iterable object to use
	 * @param <T>      The type of the iterable
	 */
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

	/**
	 * Constructs a linked list using an iterator
	 *
	 * @param iterator The iterator to use
	 * @param <T>      The iterators type
	 */
	public <T extends Iterator<E>> LinkedList(@NotNull T iterator) {
		Node<E> node = new Node<>(iterator.next());
		head = node;
		size = 0;

		while (iterator.hasNext()) {
			node.setNext(new Node<>(iterator.next()));
			node = node.getNext();
			size++;
		}
	}

	/**
	 * Adds a value to the end of the array
	 *
	 * @param value The value to add
	 */
	public void add(E value) {
		add(-1, value);
	}

	/**
	 * Adds a value at the given position in the linked list
	 *
	 * @param index The index to add the value at
	 * @param value The value to add
	 */
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

	/**
	 * Adds a value as the head of the linked list
	 *
	 * @param value The value to add
	 */
	public void addFirst(E value) {
		// Create a new node from the parsed value
		Node<E> node = new Node<>(value);
		size++;

		// Replace the head value
		node.setNext(head);
		head = node;
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

	@Override
	public E get(int index) {
		Node<E> current = head;
		int pos = 0;

		if (index > size || index < 0) {
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

	/**
	 * Returns the last value in the list
	 *
	 * @return The last value in the list
	 */
	public E getLast() {
		Node<E> current = head;

		// Step through the list until the last position is reached
		while (current.getNext() != null) {
			current = current.getNext();
		}

		return current.getValue();
	}

	/**
	 * Returns the first value in the list
	 *
	 * @return The first value in the list
	 */
	public E getFirst() {
		return head.getValue();
	}

	/**
	 * Returns whether the linked list contains a given value
	 *
	 * @param value The value to search for
	 *
	 * @return boolean Whether the list contains the value
	 */
	public boolean contains(E value) {
		return indexOf(value) != -1;
	}

	/**
	 * Returns the index of a given value in the linked list
	 *
	 * @param value The value to search for
	 *
	 * @return The index of the value or {@code -1} if it isn't found
	 */
	public int indexOf(E value) {
		Node<E> current = head;
		int pos = 0;

		while (current.getNext() != null) {
			if (current.getValue().equals(value)) {
				return pos;
			} else {
				current = current.getNext();
				pos++;
			}
		}

		return -1;
	}

	/**
	 * Clears all the elements in the list
	 */
	public void clear() {
		head = null;
	}

	/**
	 * Removes the first element of the linked list
	 *
	 * @return The removed element
	 */
	public E remove() {
		Node<E> removed = head;
		head = head.getNext();

		return removed.getValue();
	}

	/**
	 * Removes the element at the given index
	 *
	 * @param index The index of the element to remove
	 *
	 * @return The removed element
	 */
	public @NotNull E remove(int index) {
		Node<E> current = head;
		int pos = 0;

		if (index > size) {
			throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for list of length %d", index, size));
		} else if (index == 0) {
			head = head.getNext();
			return current.getValue();
		} else {
			size--;

			// Step through the list until the position is reached
			while (pos != index) {
				current = current.getNext();
				pos++;
			}

			// Remove the node by setting the current nodes next to the removed nodes next
			Node<E> removed = current.getNext();
			current.setNext(removed.getNext());
			return removed.getValue();
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
