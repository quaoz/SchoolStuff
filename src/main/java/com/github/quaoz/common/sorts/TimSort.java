package com.github.quaoz.common.sorts;

import org.jetbrains.annotations.NotNull;

/**
 * Tim sort is a combination of insertion sort and merge sort, it works by finding subsequences in the already sorted
 * data and using them to sort the rest more efficiently, works well on partially sorted data
 *
 * <p> Worst-case performance O(n log n), Best-case performance O(n), Average performance O(n log n)
 */
public class TimSort {

	/**
	 * Implements a generic selection sort algorithm, assumes the whole array should be sorted
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
	 * Implements generic tim sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param <T>   The array type
	 *
	 * @return The sorted array
	 */
	public static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array, int start, int end) {
		final int minMerge = 32;
		int minRun = 32;

		while (minRun >= 32) {
			minRun >>= 1;
		}

		// Apply insertion sort on RUNS
		for (int i = start; i <= end; i += minRun) {
			InsertionSort.sort(array, i, Math.min(i + minMerge - 1, end));
		}

		// Start merging from 32 and double each time
		for (int size = minRun; size <= end; size *= 2) {

			// Pick starting point of left sub array
			int left = start;
			while (left <= end) {

				// Find end point of left sub array
				final int mid = left + size - 1;
				final int right = Math.min(left + 2 * size - 1, end);

				// Merge sub arrays
				if (mid < right) {
					MergeSort.merge(array, left, mid, right);
				}
				left += 2 * size;
			}
		}

		return array;
	}
}
