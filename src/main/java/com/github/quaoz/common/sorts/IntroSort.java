package com.github.quaoz.common.sorts;

import com.github.quaoz.common.datastructures.interpreter.Interpreter;
import org.jetbrains.annotations.NotNull;

/**
 * Intro sort is a combination of quick sort, heap sort and insertion sort, works by using quick sort until the maximum
 * depth has been reached, in which case it will switch to heap sort to avoid quick sorts rare worst case performance,
 * or until the array is under a certain length where it will switch to insertion sort
 *
 * <p> Worst-case performance O(n log n), Best-case performance O(n log n), Average performance O(n log n)
 */
public class IntroSort {

	/**
	 * Implements a generic intro sort algorithm without the need to specify the bounds
	 *
	 * @param array The array to be sorted
	 * @param <T>   The array type
	 *
	 * @return The sorted array
	 */
	public static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array) {
		final int maxDepth = (int) (2 * Math.floor(Math.log(array.length) / Math.log(2)));
		return sort(array, 0, array.length - 1, maxDepth);
	}

	/**
	 * Implements a generic intro sort algorithm
	 *
	 * @param array    The array to be sorted
	 * @param maxDepth The number of times the function can recursively call itself before switching to heapsort
	 * @param left     The left bound of the array
	 * @param right    The right bound of the array
	 * @param <T>      The array type
	 *
	 * @return The sorted array
	 */
	static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array, int left, int right, int maxDepth) {
		final int size = right - left;

		if (size > 16) {
			if (maxDepth != 0) {
				QuickSort.randomPivot(array, left, right);

				final int partition = QuickSort.partition(array, left, right);
				sort(array, left, partition - 1, maxDepth - 1);
				sort(array, partition + 1, right, maxDepth - 1);
			} else {
				HeapSort.sort(array, left, right);
			}
		} else {
			InsertionSort.sort(array, left, right);
		}

		return array;
	}

	/**
	 * Implements a generic intro sort algorithm without the need to specify the bounds
	 *
	 * @param interpreter The interpreter to be sorted
	 * @param <T>         The interpreter type
	 *
	 * @return The sorted interpreter
	 */
	public static <T extends Comparable<T>> @NotNull Interpreter<T> sort(@NotNull Interpreter<T> interpreter) {
		final int maxDepth = (int) (2 * Math.floor(Math.log(interpreter.size()) / Math.log(2)));
		return sort(interpreter, 0, interpreter.size() - 1, maxDepth);
	}

	/**
	 * Implements a generic intro sort algorithm
	 *
	 * @param interpreter The interpreter to be sorted
	 * @param maxDepth    The number of times the function can recursively call itself before switching to heapsort
	 * @param left        The left bound of the interpreter
	 * @param right       The right bound of the interpreter
	 * @param <T>         The interpreter type
	 *
	 * @return The sorted interpreter
	 */
	static <T extends Comparable<T>> Interpreter<T> sort(Interpreter<T> interpreter, int left, int right, int maxDepth) {
		final int size = right - left;

		if (size > 16) {
			if (maxDepth != 0) {
				QuickSort.randomPivot(interpreter, left, right);

				final int partition = QuickSort.partition(interpreter, left, right);
				sort(interpreter, left, partition - 1, maxDepth - 1);
				sort(interpreter, partition + 1, right, maxDepth - 1);
			} else {
				HeapSort.sort(interpreter, left, right);
			}
		} else {
			InsertionSort.sort(interpreter, left, right);
		}

		return interpreter;
	}
}
