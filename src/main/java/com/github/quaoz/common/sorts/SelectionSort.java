package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;
import com.github.quaoz.common.arrayutils.Swap;
import org.jetbrains.annotations.NotNull;

/**
 * Selection sort is a simple sorting algorithm which works by finding the smallest element and moving it into the sorted
 * region of the array
 *
 * <p> Worst-case performance O(n^2), Best-case performance O(n^2), Average performance O(n^2)
 */
public class SelectionSort {

	/**
	 * Implements a generic selection sort algorithm, assumes the whole array should be sorted
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
	 * Implements a generic selection sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param start The start index
	 * @param end   The end index
	 * @param <T>   The array type
	 *
	 * @return T    The sorted array
	 */
	static <T extends Comparable<T>> T[] sort(T @NotNull [] array, int start, int end) {
		for (int i = start; i < end; i++) {
			int smallest = i;
			for (int j = i; j <= end; j++) {
				if (Comparisons.smaller(array[j], array[smallest])) {
					smallest = j;
				}
			}
			Swap.swap(array, i, smallest);
		}
		return array;
	}
}
