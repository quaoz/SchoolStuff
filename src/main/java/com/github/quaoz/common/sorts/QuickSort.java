package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;
import com.github.quaoz.common.arrayutils.Swap;
import com.github.quaoz.common.datastructures.interpreter.Interpreter;
import org.jetbrains.annotations.NotNull;

/**
 * Quick sort is a very fast sorting algorithm, works by picking an element as a pivot and partitioning the array around
 * the chosen pivot
 *
 * <p> Worst-case performance O(n^2), Best-case performance O(n log n), Average performance O(n log n)
 */
public class QuickSort {

	/**
	 * Implements a generic quick sort algorithm without having to specify the left and right bounds
	 *
	 * @param array The array to be sorted
	 * @param <T>   The array type
	 *
	 * @return The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T[] array) {
		return sort(array, 0, array.length - 1);
	}

	/**
	 * Implements a generic quick sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param left  The left bound of the array
	 * @param right The right bound of the array
	 * @param <T>   The array type
	 *
	 * @return The sorted array
	 */
	static <T extends Comparable<T>> T[] sort(T[] array, int left, int right) {
		if (left < right) {
			randomPivot(array, left, right);

			final int partition = partition(array, left, right);
			sort(array, left, partition - 1);
			sort(array, partition + 1, right);
		}

		return array;
	}

	/**
	 * Randomise the array to avoid the basically ordered sequences
	 *
	 * @param array The array to be sorted
	 * @param left  The left bound of the array
	 * @param right The right bound of the array
	 * @param <T>   The array type
	 */
	static <T extends Comparable<T>> void randomPivot(T[] array, int left, int right) {
		final int randomIndex = left + (int) (Math.random() * (right - left + 1));
		Swap.swap(array, randomIndex, right);
	}

	/**
	 * This method finds the partition index for an array
	 *
	 * @param array The array to be partitioned
	 * @param left  The left bound of the array
	 * @param right The right bound of the array
	 * @param <T>   The array type
	 *
	 * @return The partition index
	 */
	static <T extends Comparable<T>> int partition(T @NotNull [] array, int left, int right) {
		final T pivot = array[right];
		int index = left;

		while (left < right) {
			if (Comparisons.smallerOrEqual(array[left], pivot)) {
				Swap.swap(array, index++, left);
			}
			left++;
		}

		Swap.swap(array, index, right);
		return index;
	}

	/**
	 * Implements a generic quick sort algorithm without having to specify the left and right bounds
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
	 * Implements a generic quick sort algorithm
	 *
	 * @param interpreter The interpreter to be sorted
	 * @param left        The left bound of the interpreter
	 * @param right       The right bound of the interpreter
	 * @param <T>         The interpreter type
	 *
	 * @return The sorted interpreter
	 */
	static <T extends Comparable<T>> Interpreter<T> sort(Interpreter<T> interpreter, int left, int right) {
		if (left < right) {
			randomPivot(interpreter, left, right);

			final int partition = partition(interpreter, left, right);
			sort(interpreter, left, partition - 1);
			sort(interpreter, partition + 1, right);
		}

		return interpreter;
	}

	/**
	 * Randomise the array to avoid the basically ordered sequences
	 *
	 * @param interpreter The interpreter to be sorted
	 * @param left        The left bound of the interpreter
	 * @param right       The right bound of the interpreter
	 * @param <T>         The interpreter type
	 */
	static <T extends Comparable<T>> void randomPivot(Interpreter<T> interpreter, int left, int right) {
		final int randomIndex = left + (int) (Math.random() * (right - left + 1));
		Swap.swap(interpreter, randomIndex, right);
	}

	/**
	 * This method finds the partition index for an array
	 *
	 * @param interpreter The interpreter to be partitioned
	 * @param left        The left bound of the interpreter
	 * @param right       The right bound of the interpreter
	 * @param <T>         The interpreter type
	 *
	 * @return The partition index
	 */
	static <T extends Comparable<T>> int partition(@NotNull Interpreter<T> interpreter, int left, int right) {
		final T pivot = interpreter.get(right);
		int index = left;

		while (left < right) {
			if (Comparisons.smallerOrEqual(interpreter.get(left), pivot)) {
				Swap.swap(interpreter, index++, left);
			}
			left++;
		}

		Swap.swap(interpreter, index, right);
		return index;
	}
}
