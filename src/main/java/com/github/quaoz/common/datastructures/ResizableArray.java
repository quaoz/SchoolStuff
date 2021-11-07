package com.github.quaoz.common.datastructures;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

/**
 * Implements a resizable array
 *
 * @param <T> The array type
 */
public class ResizableArray<T> implements Iterable<T>, Interpreter<T> {
	private static final int defaultCapacity = 16;
	private int size = 0;                                // Number of spaces taken up in the array
	private T[] elements;

	/**
	 * Constructs a resizable array with a set capacity
	 *
	 * @param capacity The starting capacity of the array
	 */
	@SuppressWarnings("unchecked")
	public ResizableArray(final int capacity) {
		this.elements = (T[]) new Object[capacity];
	}

	/**
	 * Constructs a resizable array with the default capacity
	 */
	public ResizableArray() {
		this(defaultCapacity);
	}

	/**
	 * Adds an element to the end of the array, will automatically resize the array if needed
	 *
	 * @param element The element to add to the array
	 */
	public void add(final T element) {
		if (size == elements.length) {
			increaseCapacity();
		}

		elements[size++] = element;
	}

	/**
	 * Adds an array of elements to the end of the array, will automatically resize the array if needed
	 *
	 * @param elements The elements to add to the array
	 */
	public void addAll(final T @NotNull [] elements) {
		size += elements.length;

		if (size >= this.elements.length) {
			increaseCapacity(size - this.elements.length);
		}

		System.arraycopy(elements, 0, this.elements, size - elements.length, elements.length);
	}

	/**
	 * Replaces the element at the specified index
	 *
	 * @param index   The index for the element to be placed
	 * @param element The element to be inserted
	 *
	 * @throws IndexOutOfBoundsException The index was out of bounds for the array
	 */
	public void put(final int index, final T element) throws IndexOutOfBoundsException {
		elements[index] = element;
	}

	/**
	 * Inserts an element at the specified index, will automatically resize the array if needed
	 *
	 * @param index   The index to insert the element at
	 * @param element The element to insert
	 *
	 * @throws IndexOutOfBoundsException The index was out of bounds for the array
	 */
	public void insert(final int index, T element) throws IndexOutOfBoundsException {
		if (size == elements.length) {
			increaseCapacity();
		}

		System.arraycopy(elements, index, elements, index + 1, elements.length - 1 - index);
		put(index, element);
		size++;
	}

	/**
	 * Inserts an array of elements at the specified index, will automatically resize the array if needed
	 *
	 * @param index    The index to insert the elements at
	 * @param elements The array of elements to insert
	 *
	 * @throws IndexOutOfBoundsException The index was out of bounds for the array
	 */
	public void insertAll(final int index, final T @NotNull [] elements) throws IndexOutOfBoundsException {
		size += elements.length;

		if (size >= this.elements.length) {
			increaseCapacity(size - this.elements.length);
		}

		System.arraycopy(this.elements, index, this.elements, index + elements.length, size - elements.length - index);
		System.arraycopy(elements, 0, this.elements, index, elements.length);
	}

	@Override
	public T get(int index) {
		return elements[index];
	}

	@Override
	public T set(int index, T element) {
		final T previous = elements[index];
		elements[index] = element;

		return previous;
	}

	/**
	 * Removes an element from the array
	 *
	 * @param index The index of the element to be removed
	 *
	 * @throws IndexOutOfBoundsException The index was out of bounds for the array
	 */
	public void remove(final int index) throws IndexOutOfBoundsException {
		System.arraycopy(elements, index + 1, elements, index, elements.length - 1 - index);
		elements[--size] = null;
	}

	/**
	 * Removes all the elements between the bounds (inclusive)
	 *
	 * @param startIndex The index to start removing from
	 * @param endIndex   The index to remove to
	 *
	 * @throws IndexOutOfBoundsException The index was out of bounds for the array
	 */
	public void removeAll(final int startIndex, final int endIndex) throws IndexOutOfBoundsException {
		final int size = endIndex - startIndex + 1;

		System.arraycopy(elements, endIndex + 1, elements, startIndex, elements.length - endIndex - 1);

		this.size = elements.length - size;
		for (int i = this.size; i < elements.length; i++) {
			elements[i] = null;
		}
	}

	/**
	 * Removes an element from the array and resizes the array
	 *
	 * @param index The index of the element to be removed
	 *
	 * @throws IndexOutOfBoundsException The index was out of bounds for the array
	 */
	public void pop(final int index) throws IndexOutOfBoundsException {
		remove(index);
		trim();
	}

	/**
	 * Removes all the elements between the bounds (inclusive) and resizes the array
	 *
	 * @param startIndex The index to start removing from
	 * @param endIndex   The index to remove to
	 *
	 * @throws IndexOutOfBoundsException The index was out of bounds for the array
	 */
	public void popAll(final int startIndex, final int endIndex) throws IndexOutOfBoundsException {
		removeAll(startIndex, endIndex);
		trim();
	}

	/**
	 * Removes any empty elements from the array
	 */
	public void trim() {
		elements = Arrays.copyOf(elements, size);
	}

	/**
	 * Trims the array to the requested size
	 *
	 * @param size The size to trim the array to
	 */
	public void trimToSize(final int size) {
		elements = Arrays.copyOf(elements, size);
	}

	/**
	 * Returns the array size, number of elements containing a value
	 *
	 * @return int The size
	 */
	public int size() {
		return size;
	}

	/**
	 * Whether the array is empty
	 *
	 * @return boolean {@code true} if the array has no elements
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Increases the capacity by one
	 */
	public void increaseCapacity() {
		increaseCapacity(1);
	}

	/**
	 * Increases the number of elements by the specified capacity
	 *
	 * @param capacity The increment to increase the capacity by
	 */
	public void increaseCapacity(int capacity) {
		elements = Arrays.copyOf(elements, elements.length + capacity);
	}

	/**
	 * Makes sure the capacity is over a specified amount
	 *
	 * @param capacity The minimum capacity
	 */
	public void ensureCapacity(int capacity) {
		if (elements.length - size < capacity) {
			elements = Arrays.copyOf(elements, size + capacity);
		}
	}

	/**
	 * Returns the String representation of this object
	 *
	 * @return String The String representation of the object
	 */
	@Override
	public String toString() {
		return Arrays.toString(Arrays.stream(elements).toArray());
	}

	/**
	 * Creates and returns a new Resizable Array iterator
	 *
	 * @return Iterator A Resizable Array iterator
	 */
	@Override
	public Iterator<T> iterator() {
		return new DynamicArrayIterator();
	}

	@Override
	public Spliterator<T> spliterator() {
		return Iterable.super.spliterator();
	}

	/**
	 * Iterator for a Resizable Array
	 */
	private class DynamicArrayIterator implements Iterator<T> {
		private int cursor;

		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@Override
		public T next() {
			if (cursor > size) {
				throw new NoSuchElementException();
			}

			if (cursor > elements.length) {
				throw new ConcurrentModificationException();
			}

			return get(cursor++);
		}

		@Override
		public void remove() {
			if (cursor < 0) {
				throw new IllegalStateException();
			}

			ResizableArray.this.remove(cursor--);
		}

		@Override
		public void forEachRemaining(Consumer<? super T> action) {
			Objects.requireNonNull(action);

			while (hasNext()) {
				action.accept(next());
			}
		}
	}
}
