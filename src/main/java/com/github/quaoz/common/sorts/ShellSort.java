package com.github.quaoz.common.sorts;

import org.jetbrains.annotations.NotNull;

import static com.github.quaoz.common.Comparisons.smaller;

/**
 * Shell sort is a modified insertion sort algorithm, works by virtually splitting the array and placing values from the
 * unsorted part into their places in the sorted part
 *
 * <p> Worst-case performance O(n (log n)^2), Best-case performance O(n log n), Average performance O(n (log n)^2)
 */
public class ShellSort {

	/**
	 * Implements a generic shell sort algorithm, assumes the whole array should be sorted
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
	 * Implements generic shell sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param start The start index
	 * @param end   The end index
	 * @param <T>   The array type
	 *
	 * @return The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T @NotNull [] array, int start, int end) {
		final double gapConstant = 2.25;
		int gap = 1;

		// Calculate the gap
		while (gap <= end / gapConstant) {
			gap = (int) Math.ceil(gapConstant * gap + 1);
		}

		while (gap > start) {
			// Iterates through the array
			for (int i = gap; i <= end; i++) {
				T insertValue = array[i];
				int j = i;

				// Moves elements of array[0..i-1] that are greater than the insert value one gap ahead
				while (j >= gap && smaller(insertValue, array[j - gap])) {
					array[j] = array[j - gap];
					j -= gap;
				}
				array[j] = insertValue;
			}
			gap /= gapConstant;
		}

		return array;
	}
}
