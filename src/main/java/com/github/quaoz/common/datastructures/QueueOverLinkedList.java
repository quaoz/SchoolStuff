package com.github.quaoz.common.datastructures;

public class QueueOverLinkedList<E> extends LinkedListImplementation<E> {

	public void enqueue(E value) {
		addFirst(value);
	}

	public E dequeue() {
		return removeLast();
	}
}
