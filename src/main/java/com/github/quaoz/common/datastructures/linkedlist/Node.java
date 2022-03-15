package com.github.quaoz.common.datastructures.linkedlist;

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

	@Override
	@SuppressWarnings("unchecked")
	public int compareTo(@NotNull T o) {
		return value instanceof Comparable
				? ((Comparable<T>) value).compareTo(o)
				: 0;
	}
}
