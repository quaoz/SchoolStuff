package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;
import com.github.quaoz.common.arrayutils.Swap;
import org.jetbrains.annotations.NotNull;

/**
 * Modified selection sort which works by finding the smallest and largest elements and moving them into the correct
 * sorted regions of the array
 *
 * <p> Worst-case performance O(n^2), Best-case performance O(n^2), Average performance O(n^2)
 */
public class DualSelectionSort {

	/**
	 * Implements a generic dual selection sort algorithm, assumes the whole array should be sorted
	 *
	 * @param array The array to be sorted
	 * @param <T>   The array type
	 *
	 * @return T    The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T @NotNull [] array) {
		return sort(array, 0, array.length - 1);
	}

	/**
	 * Implements a generic dual selection sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param start The start index
	 * @param end   The end index
	 * @param <T>   The array type
	 *
	 * @return T    The sorted array
	 */
	static <T extends Comparable<T>> T[] sort(T @NotNull [] array, int start, int end) {
		while (start < end) {
			int lowIndex = start;
			int highIndex = end;

			for (int i = start; i <= end; i++) {
				if (Comparisons.smaller(array[i], array[lowIndex])) {
					lowIndex = i;
				} else if (Comparisons.bigger(array[i], array[highIndex])) {
					highIndex = i;
				}
			}

			Swap.swap(array, lowIndex, start++);
			Swap.swap(array, highIndex, end--);
		}
		return array;
	}
}
