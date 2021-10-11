package common.sorts;

import org.jetbrains.annotations.NotNull;

/**
 * Insertion sort is a fast sorting algorithm, works by virtually splitting the array and placing values from the
 * unsorted part into their places in the sorted part
 *
 * <p> Worst-case performance O(n^2), Best-case performance O(n), Average performance O(n^2)
 */
public class InsertionSort {

	/**
	 * Implements a generic merge sort algorithm
	 *
	 * @param array The array to be sorted
	 * @return The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T @NotNull [] array) {
		// Iterates through the array
		for (int i = 1; i < array.length; i++) {
			T insertValue = array[i];
			int j = i - 1;

			// Moves elements of array[0..i-1] that are greater than the insert value one position ahead
			while (j >= 0 && insertValue.compareTo(array[j]) < 0) {
				array[j + 1] = array[j];
				j--;
			}

			// Re-adds the insert value
			if (j != i - 1) {
				array[j + 1] = insertValue;
			}
		}
		return array;
	}
}
