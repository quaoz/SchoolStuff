package com.github.quaoz.common.searches;

import com.github.quaoz.common.Comparisons;
import org.jetbrains.annotations.NotNull;

/**
 * Jump search is a search algorithm which improves on linear search by jumping ahead a fixed amount instead of
 * searching each element, requires the array to be sorted
 *
 * <p >Worst-case performance O(√n), Best-case performance O(1), Average performance O(√n)
 **/
public class JumpSearch {

	/**
	 * Implements a generic jump search
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int The index or -1 if it wasn't found
	 */
	public static <T extends Comparable<T>> int find(T @NotNull [] array, @NotNull T value) {
		int result = -1;
		final int length = array.length;
		final int blockSize = (int) Math.floor(Math.sqrt(length)); // Defines the block size

		// Finds the block which may contain the value
		int limit = blockSize;
		while (Comparisons.smaller(array[limit], value) && limit < length - 1) {
			limit = Math.min(limit + blockSize, length - 1);
		}

		// Uses linear search on the block
		int i = limit - blockSize;
		while (i <= limit) {
			if (Comparisons.equal(array[i], value)) {
				// Returns -1 if contains is true or the index if contains is false
				result = i;
				break;
			}
			i++;
		}

		return result;
	}
}
