package com.github.quaoz.common.sorts;

import org.jetbrains.annotations.NotNull;

/**
 * Insertion sort is a fast sorting algorithm, works by virtually splitting the array and placing values from the
 * unsorted part into their places in the sorted part
 *
 * <p> Worst-case performance O(n^2), Best-case performance O(n), Average performance O(n^2)
 */
public class InsertionSort {

	/**
	 * Implements a generic insertion sort algorithm, assumes the whole array should be sorted
	 *
	 * @param array The array to be sorted
	 * @return The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T @NotNull [] array) {
		return sort(array, 0, 0);
	}

	/**
	 * Implements a generic insertion sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param start The index to start sorting at
	 * @param end The index to stop sorting at
	 * @return The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T @NotNull [] array, int start, int end) {
		if (end <= start) {
			end = array.length;
		}

		// Iterates through the array
		for (int i = start + 1; i < end; i++) {
			T insertValue = array[i];
			int j = i - 1;

			// Moves elements of array[0..i-1] that are greater than the insert value one position ahead
			while (j >= start && insertValue.compareTo(array[j]) < 0) {
				array[j + 1] = array[j];
				j--;
			}

			// Re-adds the insert value
			array[j + 1] = insertValue;
		}

		return array;
	}
}
