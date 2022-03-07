package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;
import com.github.quaoz.common.datastructures.interpreter.Interpreter;
import org.jetbrains.annotations.NotNull;

/**
 * Insertion sort is a simple sorting algorithm, works by virtually splitting the array and placing values from the
 * unsorted part into their places in the sorted part
 *
 * <p> Worst-case performance O(n^2), Best-case performance O(n), Average performance O(n^2)
 */
public class InsertionSort {

	/**
	 * Implements a generic insertion sort algorithm, assumes the whole array should be sorted
	 *
	 * @param array The array to be sorted
	 * @param <T>   The array type
	 *
	 * @return The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T @NotNull [] array) {
		return sort(array, 0, array.length - 1);
	}

	/**
	 * Implements a generic insertion sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param start The start index
	 * @param end   The end index
	 * @param <T>   The array type
	 *
	 * @return The sorted array
	 */
	static <T extends Comparable<T>> T[] sort(T @NotNull [] array, int start, int end) {

		// Iterates through the array
		for (int i = start; i <= end; i++) {
			T insertValue = array[i];
			int j = i;

			// Moves elements of array[0..i-1] that are greater than the insert value one position ahead
			while (j > start && Comparisons.smaller(insertValue, array[j - 1])) {
				array[j] = array[--j];
			}

			// Re-adds the insert value
			array[j] = insertValue;
		}

		return array;
	}

	/**
	 * Implements a generic insertion sort algorithm, assumes the whole interpreter should be sorted
	 *
	 * @param interpreter The interpreter to be sorted
	 * @param <T>         The interpreter type
	 *
	 * @return The sorted interpreter
	 */
	public static <T extends Comparable<T>> Interpreter<T> sort(Interpreter<T> interpreter) {
		return sort(interpreter, 0, interpreter.size() - 1);
	}

	/**
	 * Implements a generic insertion sort algorithm
	 *
	 * @param interpreter The interpreter to be sorted
	 * @param start       The start index
	 * @param end         The end index
	 * @param <T>         The interpreter type
	 *
	 * @return The sorted interpreter
	 */
	static <T extends Comparable<T>> Interpreter<T> sort(Interpreter<T> interpreter, int start, int end) {

		// Iterates through the interpreter
		for (int i = start; i <= end; i++) {
			T insertValue = interpreter.get(i);
			int j = i;

			// Moves elements of interpreter[0..i-1] that are greater than the insert value one position ahead
			while (j > start && Comparisons.smaller(insertValue, interpreter.get(j - 1))) {
				interpreter.set(j, interpreter.get(--j));
			}

			// Re-adds the insert value
			interpreter.set(j, insertValue);
		}

		return interpreter;
	}
}
