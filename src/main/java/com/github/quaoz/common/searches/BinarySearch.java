package com.github.quaoz.common.searches;

import com.github.quaoz.common.datastructures.Interpreter;

/**
 * Binary search is a popular search algorithm, it works by repeatedly splitting the array and selecting the half that
 * may contain the value, it requires the array to be sorted
 *
 * <p> Worst-case performance O(log n), Best-case performance O(1), Average performance O(log n)
 **/
public class BinarySearch {

	/**
	 * Implements a generic binary search without having to specify the upper and lower bounds
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int The index or -1 if it wasn't found
	 */
	public static <T extends Comparable<T>> int find(T[] array, T value) {
		return find(array, value, 0, array.length - 1);
	}

	/**
	 * Implements a generic binary search
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param left  The left bound
	 * @param right The right bound
	 * @param <T>   The array type
	 *
	 * @return int The index or -1 if it wasn't found
	 */
	static <T extends Comparable<T>> int find(T[] array, T value, int left, int right) {
		int result;

		if (left <= right) {
			// Finds the middle
			final int middle = (left + right) >>> 1;
			final int comp = value.compareTo(array[middle]);

			// Recursively splits the array and searches the half that may contain the term
			if (comp < 0) {
				result = find(array, value, left, middle - 1);
			} else if (comp > 0) {
				result = find(array, value, middle + 1, right);
			} else {
				// Returns -1 if contains is true or the index if contains is false
				result = middle;
			}
		} else {
			result = -2;
		}

		return result;
	}

	/**
	 * Implements a generic binary search without having to specify the upper and lower bounds
	 *
	 * @param interpreter The interpreter to be searched
	 * @param value       The value being searched for
	 * @param <T>         The interpreter type
	 *
	 * @return int The index or -1 if it wasn't found
	 */
	public static <T extends Comparable<T>> int find(Interpreter<T> interpreter, T value) {
		return find(interpreter, value, 0, interpreter.size() - 1);
	}

	/**
	 * Implements a generic binary search
	 *
	 * @param interpreter The array to be searched
	 * @param value       The value being searched for
	 * @param left        The left bound
	 * @param right       The right bound
	 * @param <T>         The interpreter type
	 *
	 * @return int The index or -1 if it wasn't found
	 */
	static <T extends Comparable<T>> int find(Interpreter<T> interpreter, T value, int left, int right) {
		int result;

		if (left <= right) {
			// Finds the middle
			final int middle = (left + right) >>> 1;
			final int comp = value.compareTo(interpreter.get(middle));

			// Recursively splits the array and searches the half that may contain the term
			if (comp < 0) {
				result = find(interpreter, value, left, middle - 1);
			} else if (comp > 0) {
				result = find(interpreter, value, middle + 1, right);
			} else {
				// Returns -1 if contains is true or the index if contains is false
				result = middle;
			}
		} else {
			result = -2;
		}

		return result;
	}
}
