package com.github.quaoz.common.datastructures;

public class Queue<E> extends LinkedList<E> {

	public void enqueue(E value) {
		addFirst(value);
	}

	public E dequeue() {
		return removeLast();
	}
}
