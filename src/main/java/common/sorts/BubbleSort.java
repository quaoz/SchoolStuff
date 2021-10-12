package common.sorts;

import common.arrayutils.Swap;
import org.jetbrains.annotations.NotNull;

/**
 * Bubble sort is the simplest sorting algorithm, works by comparing adjacent elements and swapping them if they are
 * out of order
 *
 * <p> Worst-case performance O(n^2), Best-case performance O(n), Average performance O(n^2)
 */
public class BubbleSort {

	/**
	 * Implements a generic bubble sort algorithm
	 *
	 * @param array The array to be sorted
	 * @return The sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(T @NotNull [] array) {
		boolean swapped;

		for (int i = 0, size = array.length; i < size - 1; ++i) {
			swapped = false;
			for (int j = 0; j < size - 1 - i; ++j) {
				if (array[j].compareTo(array[j + 1]) > 0) {
					Swap.swap(array, j, j + 1);
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
		return array;
	}
}