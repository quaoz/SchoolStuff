package com.github.quaoz.common.sorts;

import org.jetbrains.annotations.NotNull;

/**
 * Dual-pivot intro sort is a modified intro sort which uses dual-pivot quick sort instead of standard quick sort as it
 * is faster for almost all data sets
 *
 * <p> Worst-case performance O(n log n), Best-case performance O(n log n), Average performance O(n log n)
 */
public class DualPivotIntroSort {
	/**
	 * Implements a generic intro sort algorithm without the need to specify the bounds
	 *
	 * @param array The array to be sorted
	 * @param <T>   The array type
	 *
	 * @return T The sorted array
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
	 * @return T The sorted array
	 */
	static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array, int left, int right, int maxDepth) {
		final int size = right - left;

		if (size > 16) {
			if (maxDepth != 0) {
				final int[] partition = DualPivotQuickSort.partition(array, left, right);

				sort(array, left, partition[0] - 1, maxDepth - 1);
				sort(array, partition[0] + 1, partition[1] - 1, maxDepth - 1);
				sort(array, partition[1] + 1, right, maxDepth - 1);
			} else {
				HeapSort.sort(array, left, right);
			}
		} else {
			InsertionSort.sort(array, left, right);
		}

		return array;
	}
}
