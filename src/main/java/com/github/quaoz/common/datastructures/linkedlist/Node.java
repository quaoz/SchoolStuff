package com.github.quaoz.common.datastructures.linkedlist;

public class Node<T> {
	private T value;
	private Node<T> next;

	public Node(T value) {
		this.value = value;
		next = null;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public T getValue() {
		return value;
	}

	public T setValue(T value) {
		T oldValue = this.value;
		this.value = value;

		return oldValue;
	}

	public void display() {
		System.out.println(value.toString());
	}
}
