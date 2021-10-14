package com.github.quaoz.common.sorts;

import com.github.quaoz.common.arrayutils.Swap;
import org.jetbrains.annotations.NotNull;

/**
 * Quick sort is a fast sorting algorithm, works by picking an element as a pivot and partitioning the array around the
 * chosen pivot
 *
 * <p> Worst-case performance O(n^2), Best-case performance O(n log n), Average performance O(n log n)
 */
public class QuickSort {

	/**
	 * An overload for sort() so the left and right bounds don't have to be specified; implements a generic quick sort
	 * algorithm
	 *
	 * @param array The array to be sorted
	 * @return The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T[] array) {
		return sort(array, 0, array.length - 1);
	}

	/**
	 * Implements a generic quick sort algorithm
	 *
	 * @param array The array to be sorted
	 * @param left The first index of the array
	 * @param right The last index of the array
	 * @return The sorted array
	 */
	static <T extends Comparable<T>> T[] sort(T[] array, int left, int right) {
		if (left < right) {
			int pivot = randomPartition(array, left, right);
			sort(array, left, pivot - 1);
			sort(array, pivot, right);
		}
		return array;
	}

	/**
	 * Randomise the array to avoid the basically ordered sequences
	 *
	 * @param array The array to be sorted
	 * @param left The first index of an array
	 * @param right The last index of an array
	 * @return The partition index
	 */
	private static <T extends Comparable<T>> int randomPartition(T[] array, int left, int right) {
		int randomIndex = left + (int) (Math.random() * (right - left + 1));
		Swap.swap(array, randomIndex, right);
		return partition(array, left, right);
	}

	/**
	 * This method finds the partition index for an array
	 *
	 * @param array The array to be sorted
	 * @param left The first index of an array
	 * @param right The last index of an array
	 * @return The partition index
	 */
	private static <T extends Comparable<T>> int partition(T @NotNull [] array, int left, int right) {
		int mid = (left + right) >>> 1;
		T pivot = array[mid];

		while (left <= right) {
			while (array[left].compareTo(pivot) < 0) {
				left++;
			}
			while (pivot.compareTo(array[right]) < 0) {
				right--;
			}
			if (left <= right) {
				Swap.swap(array, left++, right--);
			}
		}
		return left;
	}
}
