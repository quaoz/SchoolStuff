package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;
import com.github.quaoz.common.arrayutils.Swap;
import com.github.quaoz.common.datastructures.Interpreter;
import org.jetbrains.annotations.NotNull;

/**
 * Bubble sort a very slow simplest sorting algorithm, works by comparing adjacent elements and swapping them if they
 * are out of order
 *
 * <p> Worst-case performance O(n^2), Best-case performance O(n), Average performance O(n^2)
 */
public class BubbleSort {

	/**
	 * Implements a generic bubble sort algorithm, assumes the whole array should be sorted
	 *
	 * @param array The array to be sorted
	 * @param <T>   The array type
	 *
	 * @return T    The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T @NotNull [] array) {
		return sort(array, 0, array.length - 1);
	}

	/**
	 * Implements a generic bubble sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param start The index to start searching from
	 * @param end   The index to stop searching at
	 * @param <T>   The array type
	 *
	 * @return T    The sorted array
	 */
	static <T extends Comparable<T>> T[] sort(T @NotNull [] array, int start, int end) {
		boolean swapped;

		for (int i = start; i < end; ++i) {
			swapped = false;
			for (int j = start; j < end - i; ++j) {
				if (Comparisons.bigger(array[j], array[j + 1])) {
					Swap.swap(array, j, j + 1);
					swapped = true;
				}
			}

			if (!swapped) {
				break;
			}
		}
		return array;
	}

	/**
	 * Implements a generic bubble sort algorithm, assumes the whole array should be sorted
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
	 * Implements a generic bubble sort algorithm
	 *
	 * @param interpreter The interpreter to be sorted
	 * @param start       The index to start searching from
	 * @param end         The index to stop searching at
	 * @param <T>         The interpreter type
	 *
	 * @return The sorted interpreter
	 */
	static <T extends Comparable<T>> Interpreter<T> sort(Interpreter<T> interpreter, int start, int end) {
		boolean swapped = true;

		while (swapped) {
			swapped = false;
			int j = start;
			while (j < end) {
				if (Comparisons.bigger(interpreter.get(j++), interpreter.get(j))) {
					Swap.swap(interpreter, j - 1, j);
					swapped = true;
				}
			}

			end--;
		}
		return interpreter;
	}

}
