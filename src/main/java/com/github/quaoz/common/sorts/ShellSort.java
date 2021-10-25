package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;
import org.jetbrains.annotations.NotNull;

/**
 * Shell sort is a modified insertion sort algorithm, works by virtually splitting the array and placing values from the
 * unsorted part into their places in the sorted part
 *
 * <p> Worst-case performance O(n (log n)^2), Best-case performance O(n log n), Average performance O(n (log n)^2)
 */
public class ShellSort {

	/**
	 * Implements generic shell sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param <T>   The array type
	 *
	 * @return T The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T @NotNull [] array) {
		final double gapConstant = 2.25;
		int gap = 1;

		// Calculate the gap
		while (gap < array.length / gapConstant) {
			gap = (int) Math.ceil(gapConstant * gap + 1);
		}

		while (gap > 0) {
			// Iterates through the array
			for (int i = gap; i < array.length; i++) {
				T insertValue = array[i];
				int j = i;

				// Moves elements of array[0..i-1] that are greater than the insert value one gap ahead
				while (j >= gap && Comparisons.smaller(insertValue, array[j - gap])) {
					j -= gap;
					array[j] = array[j];
				}
				array[j] = insertValue;
			}
			gap /= gapConstant;
		}

		return array;
	}
}
