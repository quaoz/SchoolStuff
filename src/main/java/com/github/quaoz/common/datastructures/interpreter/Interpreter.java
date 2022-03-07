package com.github.quaoz.common.datastructures.interpreter;

/**
 * Used to generalise access to datastructures with several elements
 *
 * @param <T> The interpreter type
 */
public interface Interpreter<T> {
	/**
	 * Returns the element at the specified position in this interpreter
	 *
	 * @param index Index of the element to return
	 *
	 * @return The element at the specified position in this interpreter
	 *
	 * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >= size()})
	 */
	T get(int index);

	/**
	 * Replaces the element at the specified position in this interpreter with the specified element
	 *
	 * @param index   Index of the element to replace
	 * @param element Element to be stored at the specified position
	 *
	 * @return The element previously at the specified position
	 *
	 * @throws ClassCastException        If the class of the specified element prevents it from being added to this list
	 * @throws NullPointerException      If the specified element is null and this list does not permit null elements
	 * @throws IllegalArgumentException  If some property of the specified element prevents it from being added to this list
	 * @throws IndexOutOfBoundsException If the index is out of range ({@code index < 0 || index >= size()})
	 */
	T set(int index, T element);


	/**
	 * Gets the number of elements in the interpreter
	 *
	 * @return The number of elements in the interpreter
	 */
	int size();
}
