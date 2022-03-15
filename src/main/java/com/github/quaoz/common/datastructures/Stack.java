package com.github.quaoz.common.datastructures;

public class Stack<E> {
	Node<E> top;

	public Stack() {
		top = null;
	}

	public void push(E value) {
		Node<E> node = new Node<>(value);
		top.setNext(node);
		top = node;
	}

	public E pop() {
		E value = top.getValue();
		top = top.getPrev();

		return value;
	}

	public E peek() {
		return top.getValue();
	}
}
