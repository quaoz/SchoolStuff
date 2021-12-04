package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;

/**
 * Merge sort is a fast popular sorting algorithm, works by splitting it then recombining the pieces in order
 *
 * <p> Worst-case performance O(n log n), Best-case performance O(n log n), Average performance O(n log n)
 */
public class MergeSort {

	/**
	 * Implements a generic merge sort algorithm without having to specify the left and right bounds
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
	 * Implements a generic merge sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param left  The left bound of the array
	 * @param right The right bound of the array
	 * @param <T>   The array type
	 *
	 * @return T The sorted array
	 */
	static <T extends Comparable<T>> T[] sort(T[] array, int left, int right) {
		if (left < right) {
			int mid = (left + right) >>> 1;

			// Sorts the first half
			sort(array, left, mid);

			// Sorts the second half
			sort(array, mid + 1, right);

			// Merges the sorted halves
			if (Comparisons.bigger(array[mid], array[mid + 1])) {
				merge(array, left, mid, right);
			}
		}

		return array;
	}

	/**
	 * Merges two parts of an array.
	 *
	 * @param array The array to be merged
	 * @param left  The left bound of the array
	 * @param mid   The midpoint of the array
	 * @param right The right bound of the array
	 * @param <T>   The array type
	 */
	static <T extends Comparable<T>> void merge(T[] array, int left, int mid, int right) {
		final int length = right - left + 1;

		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Comparable[length];
		int i = left;
		int j = mid + 1;
		int k = 0;

		// Compares and adds the values to the temp array
		while (i <= mid && j <= right) {
			temp[k++] = Comparisons.smallerOrEqual(array[i], array[j])
					? array[i++]
					: array[j++];
		}

		// Copy remaining values to the array
		while (i <= mid) {
			temp[k++] = array[i++];
		}

		while (j <= right) {
			temp[k++] = array[j++];
		}

		// Copies the temp array to the original array
		System.arraycopy(temp, 0, array, left, length);
	}
}
