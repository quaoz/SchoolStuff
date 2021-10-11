package common.sorts;

import org.jetbrains.annotations.NotNull;

/**
 * Shell sort is a modified insertion sort algorithm, works by virtually splitting the array and placing values from the
 * unsorted part into their places in the sorted part
 *
 * <p> Worst-case performance O(n (log n)^2), Best-case performance O(n log n), Average performance O(n (log n)^2)
 */
public class ShellSort {

	/**
	 * Implements generic shell sort.
	 *
	 * @param array The array to be sorted
	 * @return The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T @NotNull [] array) {
		int gap = 1;

		// Calculate gap
		while (gap < array.length / 3) {
			gap = 3 * gap + 1;
		}

		while (gap > 0) {
			// Iterates through the array
			for (int i = gap; i < array.length; i++) {
				T insertValue = array[i];
				int j = i;

				// Moves elements of array[0..i-1] that are greater than the insert value one gap ahead
				while (j >= gap && insertValue.compareTo(array[j - gap]) < 0) {
					array[j] = array[j - gap];
					j -= gap;
				}
				array[j] = insertValue;
			}
			gap /= 3;
		}
		return array;
	}
}
