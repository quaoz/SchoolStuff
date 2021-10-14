package com.github.quaoz.common.sorts;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Tim sort is a combination of insertion sort and merge sort, it works by finding subsequences in the already sorted
 * data and using them to sort the rest more efficiently
 *
 * <p> Worst-case performance O(n log n), Best-case performance O(n), Average performance O(n log n)
 */
public class TimSort {
	private static final int MIN_MERGE = 32;

	public static int minRunLength(int n) {
		assert n >= 0;

		// Becomes 1 if any 1 bits are shifted off
		int r = 0;
		while (n >= MIN_MERGE) {
			r |= (n & 1);
			n >>= 1;
		}
		return n + r;
	}

	/**
	 * Implements generic tim sort algorithm
	 *
	 * @param array The array to be sorted
	 * @return The sorted array
	 */
	@Contract("_ -> param1")
	public static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array) {
		int arrayLength = array.length;
		int minRun = minRunLength(MIN_MERGE);

		// Applying insertion sort on RUNS
		for (int i = 0; i < arrayLength; i += minRun) {
			InsertionSort.sort(array, i, Math.min(i + MIN_MERGE - 1, (arrayLength - 1)));
		}

		// Start merging from size RUN (or 32). It will merge to form size 64, then 128, 256 and so on
		for (int size = minRun; size < arrayLength; size = 2 * size) {

			// Pick starting point of left sub array. We are going to merge arr[left..left+size-1] and arr[left+size, left+2*size-1] After every merge, we increase left by 2*size
			for (int left = 0; left < arrayLength;
				 left += 2 * size) {

				// Find ending point of left sub array mid+1 is starting point of right sub array
				int mid = left + size - 1;
				int right = Math.min((left + 2 * size - 1), (arrayLength - 1));

				// Merge sub array arr[left.....mid] & arr[mid+1....right]
				if (mid < right) {
					MergeSort.merge(array, left, mid, right);
				}
			}
		}

		return array;
	}
}
