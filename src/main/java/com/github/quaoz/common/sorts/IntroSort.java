package com.github.quaoz.common.sorts;

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
	 * @return T The sorted array
	 */
	public static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array) {
		final int maxDepth = (int) (2 * Math.floor(Math.log(array.length) / Math.log(2)));
		return sort(array, maxDepth, 0, array.length - 1);
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
	static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array, int maxDepth, int left, int right) {
		final int size = right - left;

		if (size <= 0) {
			return array;
		} else if (size <= 16) {
			// Use insertion sort when the array is smaller than 16 elements
			InsertionSort.sort(array, left, right);
		} else if (maxDepth <= 0) {
			// Use heap sort if teh max depth is exceeded
			HeapSort.sort(array, left, right);
		} else {
			// Recursively use quick sort
			int pivot = QuickSort.partition(array, left, right);
			sort(array, maxDepth - 1, left, pivot - 1);
			sort(array, maxDepth - 1, pivot + 1, right);
		}

		return array;
	}
}
