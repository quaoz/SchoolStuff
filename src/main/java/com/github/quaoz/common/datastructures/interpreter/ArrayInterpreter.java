package com.github.quaoz.common.datastructures.interpreter;

public class ArrayInterpreter<T> implements Interpreter<T> {
	private final T[] array;

	public ArrayInterpreter(T[] array) {
		this.array = array;
	}

	@Override
	public T get(int index) {
		return array[index];
	}

	@Override
	public T set(int index, T element) {
		final T previous = array[index];
		array[index] = element;

		return previous;
	}

	@Override
	public int size() {
		return array.length;
	}

	/**
	 * Returns the base array
	 *
	 * @return The base array
	 */
	public T[] getArray() {
		return array;
	}
}
