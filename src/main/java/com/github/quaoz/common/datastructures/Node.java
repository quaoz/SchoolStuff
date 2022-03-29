package com.github.quaoz.common.datastructures;

import org.jetbrains.annotations.NotNull;

public class Node<T> implements Comparable<T> {
	private T value;
	private Node<T> next;
	private Node<T> prev;

	/**
	 * Constructs a new node
	 *
	 * @param value The value to initialise the node with
	 */
	public Node(T value) {
		this.value = value;
		next = null;
		prev = null;
	}

	/**
	 * Returns the next node
	 *
	 * @return The next node
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Sets the next node
	 *
	 * @param next The next node
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * Returns the previous node
	 *
	 * @return The previous node
	 */
	public Node<T> getPrev() {
		return prev;
	}

	/**
	 * Sets the previous node
	 *
	 * @param prev The previous node
	 */
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	/**
	 * Returns the current nodes value
	 *
	 * @return The current nodes value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Sets the current nodes value and returns the previous value
	 *
	 * @param value The new value
	 *
	 * @return The old value
	 */
	public T setValue(T value) {
		T oldValue = this.value;
		this.value = value;

		return oldValue;
	}

	/**
	 * Prints the nodes value
	 */
	public void display() {
		System.out.println(value.toString());
	}

	/**
	 * Compares the current node to the given object
	 *
	 * @param o The object to compare to
	 *
	 * @return The result of the comparison
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int compareTo(@NotNull T o) {
		return value instanceof Comparable
				? ((Comparable<T>) value).compareTo(o)
				: value.toString().compareTo(o.toString());
	}

	/**
	 * Compares the value of the node to the given value
	 *
	 * @param o The value to compare the node's value to
	 *
	 * @return The result of the comparison
	 */
	@Override
	public boolean equals(Object o) {
		boolean result;

		if (this == o) {
			result = true;
		} else if (o == null || getClass() != o.getClass()) {
			result = false;
		} else {
			Node<?> node = (Node<?>) o;
			result = value.equals(node.value);
		}

		return result;
	}

	/**
	 * Returns the hashcode of the value
	 *
	 * @return The hashcode of the value
	 */
	@Override
	public int hashCode() {
		return value.hashCode();
	}

	/**
	 * Returns the string representation of the value
	 *
	 * @return The string representation of the value
	 */
	@Override
	public String toString() {
		return value.toString();
	}
}
