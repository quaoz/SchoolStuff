package com.github.quaoz.common.datastructures;

import java.util.List;

public class ListInterpreter<E> implements Interpreter<E> {
	private final List<E> list;

	public ListInterpreter(List<E> list) {
		this.list = list;
	}

	@Override
	public E get(int index) {
		return list.get(index);
	}

	@Override
	public E set(int index, E element) {
		return list.set(index, element);
	}

	@Override
	public int size() {
		return list.size();
	}
}
