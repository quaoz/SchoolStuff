package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;
import com.github.quaoz.common.arrayutils.Swap;
import com.github.quaoz.common.datastructures.interpreter.Interpreter;
import org.jetbrains.annotations.NotNull;

/**
 * Heap sort is a fast sorting algorithm, works by dividing the array into a sorted and unsorted region and shrinking
 * the unsorted region by moving the largest element into the sorted region
 *
 * <p> Worst-case performance O(n log n), Best-case performance O(n log n), Average performance O(n log n)
 */
public class HeapSort {

	/**
	 * Implements a generic heap sort algorithm without having to specify the bounds
	 *
	 * @param array The array to be sorted
	 * @param <T>   The array type
	 *
	 * @return The sorted array
	 */
	public static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array) {
		return sort(array, 0, array.length);
	}

	/**
	 * Implements a generic heap sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param start The start index
	 * @param end   The end index
	 * @param <T>   The array type
	 *
	 * @return The sorted array
	 */
	static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array, int start, int end) {
		int size = end - start;

		// Build heap
		for (int i = size / 2 - 1; i >= 0; i--) {
			heapify(array, size, i);
		}

		// Extract an element from the heap
		while (size > 0) {
			// Move the current root to the end
			Swap.swap(array, start, --size + start);

			// Heapify the reduced heap
			heapify(array, size, start);
		}

		return array;
	}

	/**
	 * Heapifies the subtree
	 *
	 * @param array The array to be heapified
	 * @param size  The size of the subtree
	 * @param root  The root of the subtree
	 * @param <T>   The array type
	 */
	static <T extends Comparable<T>> void heapify(T @NotNull [] array, int size, int root) {
		int max = root;
		final int left = 2 * root + 1;
		final int right = 2 * root + 2;

		// If left is bigger than max replace max with it
		if (left < size && Comparisons.bigger(array[left], array[max])) {
			max = left;
		}

		// If right is bigger than max replace max with it
		if (right < size && Comparisons.bigger(array[right], array[max])) {
			max = right;
		}

		// Swaps the max and the root if they aren't equal
		if (max != root) {
			Swap.swap(array, max, root);

			// Recursively heapify the affected subtree
			heapify(array, size, max);
		}
	}

	/**
	 * Implements a generic heap sort algorithm without having to specify the bounds
	 *
	 * @param interpreter The interpreter to be sorted
	 * @param <T>         The interpreter type
	 *
	 * @return The sorted interpreter
	 */
	public static <T extends Comparable<T>> Interpreter<T> sort(Interpreter<T> interpreter) {
		return sort(interpreter, 0, interpreter.size());
	}

	/**
	 * Implements a generic heap sort algorithm
	 *
	 * @param interpreter The interpreter to be sorted
	 * @param start       The start index
	 * @param end         The end index
	 * @param <T>         The interpreter type
	 *
	 * @return The sorted interpreter
	 */
	static <T extends Comparable<T>> Interpreter<T> sort(Interpreter<T> interpreter, int start, int end) {
		int size = end - start;

		// Build heap
		for (int i = size / 2 - 1; i >= 0; i--) {
			heapify(interpreter, size, i);
		}

		// Extract an element from the heap
		while (size > 0) {
			// Move the current root to the end
			Swap.swap(interpreter, start, --size + start);

			// Heapify the reduced heap
			heapify(interpreter, size, start);
		}

		return interpreter;
	}

	/**
	 * Heapifies the subtree
	 *
	 * @param interpreter The interpreter to be heapified
	 * @param size        The size of the subtree
	 * @param root        The root of the subtree
	 * @param <T>         The interpreter type
	 */
	static <T extends Comparable<T>> void heapify(Interpreter<T> interpreter, int size, int root) {
		int max = root;
		final int left = 2 * root + 1;
		final int right = 2 * root + 2;

		// If left is bigger than max replace max with it
		if (left < size && Comparisons.bigger(interpreter.get(left), interpreter.get(max))) {
			max = left;
		}

		// If right is bigger than max replace max with it
		if (right < size && Comparisons.bigger(interpreter.get(right), interpreter.get(max))) {
			max = right;
		}

		// Swaps the max and the root if they aren't equal
		if (max != root) {
			Swap.swap(interpreter, max, root);

			// Recursively heapify the affected subtree
			heapify(interpreter, size, max);
		}
	}
}
