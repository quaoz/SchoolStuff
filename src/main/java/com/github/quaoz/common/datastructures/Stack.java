package com.github.quaoz.common.datastructures;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class Stack<E> implements Iterable<E> {
	Node<E> top;
	int size;

	public Stack() {
		top = null;
		size = 0;
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8};

		stack.pushAll(List.of(array));
		System.out.println(stack.size());

		System.out.println("-----------");

		for (Integer integer : stack) {
			System.out.println(integer);
		}

		System.out.println("-----------");

		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

	public <T extends Iterable<E>> Stack(T collection) {
		top = null;
		size = 0;
		pushAll(collection);
	}

	public void push(E value) {
		Node<E> node = new Node<>(value);

		if (top != null) {
			node.setPrev(top);
			top.setNext(node);
		}

		top = node;
		size++;
	}

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

	public E peek() {
		return top.getValue();
	}

	public int size() {
		return size;
	}

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
