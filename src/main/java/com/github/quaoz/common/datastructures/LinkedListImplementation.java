package com.github.quaoz.common.datastructures;

import com.github.quaoz.common.datastructures.interpreter.Interpreter;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class LinkedListImplementation<E> implements Interpreter<E>, List<E> {
	private int size;
	private NodeImplementation<E> head;
	private NodeImplementation<E> last;

	/* Constructors */

	/**
	 * Constructs an empty linked list
	 */
	public LinkedListImplementation() {
		head = null;
		last = null;
		size = 0;
	}

	/**
	 * Constructs linked list with the elements of an array
	 *
	 * @param array The array to use
	 */
	public LinkedListImplementation(E @NotNull [] array) {
		NodeImplementation<E> node = new NodeImplementation<>(array[0]);
		size = array.length;
		head = node;

		for (E e : array) {
			node.setNext(new NodeImplementation<>(e));
			node = node.getNext();
		}

		last = node;
	}

	/**
	 * Constructs linked list with the elements of a list
	 *
	 * @param list The list to use
	 */
	public LinkedListImplementation(@NotNull List<E> list) {
		NodeImplementation<E> node = new NodeImplementation<>(list.get(0));
		size = list.size();
		head = node;

		for (E e : list) {
			node.setNext(new NodeImplementation<>(e));
			node = node.getNext();
		}

		last = node;
	}

	/**
	 * Constructs a linked list using the elements of an iterable object
	 *
	 * @param iterable The iterable object to use
	 * @param <T>      The type of the iterable
	 */
	public <T extends Iterable<E>> LinkedListImplementation(@NotNull T iterable) {
		Iterator<E> iterator = iterable.iterator();
		NodeImplementation<E> node = new NodeImplementation<>(iterator.next());
		head = node;
		size = 0;

		while (iterator.hasNext()) {
			node.setNext(new NodeImplementation<>(iterator.next()));
			node = node.getNext();
			size++;
		}

		last = node;
	}

	/**
	 * Constructs a linked list using an iterator
	 *
	 * @param iterator The iterator to use
	 * @param <T>      The iterators type
	 */
	public <T extends Iterator<E>> LinkedListImplementation(@NotNull T iterator) {
		NodeImplementation<E> node = new NodeImplementation<>(iterator.next());
		head = node;
		size = 0;

		while (iterator.hasNext()) {
			node.setNext(new NodeImplementation<>(iterator.next()));
			node = node.getNext();
			size++;
		}

		last = node;
	}

	/**
	 * Adds a value as the head of the linked list
	 *
	 * @param value The value to add
	 */
	public void addFirst(E value) {
		// Create a new node from the parsed value
		NodeImplementation<E> node = new NodeImplementation<>(value);
		size++;

		// Replace the head value
		if (head == null) {
			last = node;
		} else {
			node.setNext(head);
			head.setPrev(node);
		}

		head = node;
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);

		if (index == -1) {
			return false;
		} else {
			remove(index);
			return true;
		}
	}

	@Override
	public boolean containsAll(@NotNull Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean addAll(@NotNull Collection<? extends E> c) {
		if (c.isEmpty()) {
			return false;
		} else {
			for (E e : c) {
				add(e);
			}
		}

		return true;
	}

	@Override
	public boolean addAll(int index, @NotNull Collection<? extends E> c) {
		if (c.isEmpty()) {
			return false;
		} else {
			for (E e : c) {
				add(index++, e);
			}
		}

		return true;
	}

	@Override
	public boolean removeAll(@NotNull Collection<?> c) {
		if (c.isEmpty()) {
			return false;
		} else {
			for (Object o : c) {
				remove(o);
			}
		}

		return true;
	}

	@Override
	@Deprecated
	public boolean retainAll(@NotNull Collection<?> c) {
		return false;
	}

	/**
	 * Adds a value at the given position in the linked list
	 *
	 * @param index The index to add the value at
	 * @param value The value to add
	 */
	@Override
	public void add(int index, E value) {
		// Create a new node from the parsed value
		NodeImplementation<E> node = new NodeImplementation<>(value);

		if (index > size) {
			throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for list of length %d", index, size));
		} else if (index == -1) {
			add(value);
		} else {
			NodeImplementation<E> current = head;
			int pos = 1;

			// Step through the list until the position is reached
			while (pos < index) {
				current = current.getNext();
				pos++;
			}

			// Insert the node
			node.setNext(current.getNext());
			current.setNext(node);
			size++;
		}
	}

	@Override
	public E set(int index, E element) {
		NodeImplementation<E> current = head;
		int pos = 0;

		if (index > size) {
			throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for list of length %d", index, size));
		} else if (index == size) {
			return last.setValue(element);
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
		NodeImplementation<E> current = head;
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
		return last.getValue();
	}

	/**
	 * Returns the first value in the list
	 *
	 * @return The first value in the list
	 */
	public E getFirst() {
		return head.getValue();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	/**
	 * Clears all the elements in the list
	 */
	public void clear() {
		head = null;
		last = null;
		size = 0;
	}

	/**
	 * Removes the first element of the linked list
	 *
	 * @return The removed element
	 */
	public E removeFirst() {
		NodeImplementation<E> removed = head;

		if (removed != null) {
			head = head.getNext();
			size--;

			return removed.getValue();
		} else {
			return null;
		}
	}

	/**
	 * Removes the last element of the linked list
	 *
	 * @return The removed element
	 */
	public E removeLast() {
		NodeImplementation<E> removed = last;

		if (removed != null) {
			last = last.getPrev();
			size--;

			return removed.getValue();
		} else {
			return null;
		}
	}

	/**
	 * Removes the element at the given index
	 *
	 * @param index The index of the element to remove
	 *
	 * @return The removed element
	 */
	public @NotNull E remove(int index) {
		NodeImplementation<E> current = head;
		int pos = 0;

		if (index > size) {
			throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for list of length %d", index, size));
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size) {
			current = last;
			last = last.getPrev();
			return current.getValue();
		} else {
			size--;

			// Step through the list until the position is reached
			while (pos != index) {
				current = current.getNext();
				pos++;
			}

			// Remove the node by setting the current nodes next to the removed nodes next
			NodeImplementation<E> removed = current.getNext();
			current.setNext(removed.getNext());
			return removed.getValue();
		}
	}

	@Override
	public int indexOf(Object o) {
		NodeImplementation<E> current = head;
		int pos = 0;

		while (current.getNext() != null) {
			if (current.getValue().equals(o)) {
				return pos;
			} else {
				current = current.getNext();
				pos++;
			}
		}

		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		NodeImplementation<E> current = last;
		int pos = size;

		while (current.getPrev() != null) {
			if (current.getValue().equals(o)) {
				return pos;
			} else {
				current = current.getNext();
				pos++;
			}
		}

		return -1;
	}

	@NotNull
	@Override
	@Deprecated
	public ListIterator<E> listIterator() {
		return null;
	}

	@NotNull
	@Override
	@Deprecated
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@NotNull
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		NodeImplementation<E> current = head;
		int pos = 0;

		while (pos < fromIndex) {
			current = current.getNext();
			pos++;
		}

		List<E> list = new ArrayList<>();

		for (int i = 0; i < toIndex; i++) {
			list.add(current.getValue());
			current = current.getNext();
		}

		return list;
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

	@NotNull
	@Override
	public Object @NotNull [] toArray() {
		Object[] array = new Object[size];
		NodeImplementation<E> current = head;
		int pos = 0;

		while (current.getNext() != null) {
			array[pos++] = current.getValue();
			current = current.getNext();
		}

		return array;
	}

	@NotNull
	@Override
	@SuppressWarnings("unchecked")
	public <T> T @NotNull [] toArray(@NotNull T @NotNull [] a) {
		NodeImplementation<E> current = head;
		int pos = 0;

		while (current.getNext() != null) {
			a[pos++] = (T) current.getValue();
			current = current.getNext();
		}

		return a;
	}

	@Override
	public boolean add(E e) {
		NodeImplementation<E> node = new NodeImplementation<>(e);

		if (head == null) {
			head = node;
		} else {
			last.setNext(node);
		}

		last = node;
		size++;

		return true;
	}

	private class LinkedListIterator implements Iterator<E> {
		private NodeImplementation<E> node = head;

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
