package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;
import com.github.quaoz.common.arrayutils.Swap;
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
	 * @return T The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T[] array) {
		return sort(array, 0, array.length - 1);
	}

	/**
	 * Implements a generic quick sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param left  The first index of the array
	 * @param right The last index of the array
	 * @param <T>   The array type
	 *
	 * @return T The sorted array
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
	 * @param left  The first index of an array
	 * @param right The last index of an array
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
	 * @param left  The first index of an array
	 * @param right The last index of an array
	 * @param <T>   The array type
	 *
	 * @return int The partition index
	 */
	static <T extends Comparable<T>> int partition(T @NotNull [] array, int left, int right) {
		final T pivot = array[right];
		int index = left;

		while (left < right) {
			if (!Comparisons.bigger(array[left], pivot)) {
				Swap.swap(array, index++, left);
			}
			left++;
		}

		Swap.swap(array, index, right);
		return index;
	}
}
