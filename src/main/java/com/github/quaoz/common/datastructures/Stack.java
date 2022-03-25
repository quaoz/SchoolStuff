package com.github.quaoz.common.datastructures;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class Stack<E> implements Iterable<E> {
	Node<E> top;
	int size;

	/**
	 * Constructor
	 */
	public Stack() {
		top = null;
		size = 0;
	}

	/**
	 * Constructs the stack from a collection
	 *
	 * @param collection The collection to construct the stack from
	 * @param <T>		 The collections type
	 */
	public <T extends Iterable<E>> Stack(T collection) {
		top = null;
		size = 0;
		pushAll(collection);
	}

	/**
	 * Adds a value to the stack
	 *
	 * @param value The value to add
	 *
	 * @return {@code true} if the stack is changed as a result of this call
	 */
	public boolean push(E value) {
		if (value != null) {
			Node<E> node = new Node<>(value);

			if (top != null) {
				node.setPrev(top);
				top.setNext(node);
			}

			top = node;
			size++;

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds a set of values to the stack
	 *
	 * @param iterable The values to add
	 *
	 * @return {@code true} if the stack is changed as a result of this call
	 */
	public boolean pushAll(@NotNull Iterable<E> iterable) {
		Iterator<E> iterator = iterable.iterator();

		if (!iterator.hasNext()) {
			return false;
		} else {
			Node<E> node = new Node<>(iterator.next());

			if (top == null) {
				top = node;
				node = new Node<>(iterator.next());
				size++;
			}

			while (iterator.hasNext()) {
				top.setNext(node);
				node.setPrev(top);
				top = node;
				node = new Node<>(iterator.next());
				size++;
			}

			return true;
		}
	}

	/**
	 * Removes and returns the value from the top of the stack
	 *
	 * @return The value from the top of the stack
	 */
	public E pop() {
		E value = top == null
				? null
				: top.getValue();

		if (top != null) {
			top = top.getPrev();
			size--;
		}

		return value;
	}

	/**
	 * Retirns the top value on the stack
	 *
	 * @return The top value on the stack
	 */
	public E peek() {
		return top.getValue();
	}

	/**
	 * Returns the size of the stack
	 *
	 * @return The size of the stack
	 */
	public int size() {
		return size;
	}

	/**
	 * @return {@code true} if the stack is empty
	 */
	public boolean isEmpty() {
		return top == null;
	}

	@NotNull
	@Override
	public Iterator<E> iterator() {
		return new StackIterator();
	}

	private class StackIterator implements Iterator<E> {
		Node<E> current = top;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			E value = current.getValue();
			current = current.getPrev();

			return value;
		}
	}
}
