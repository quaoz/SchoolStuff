package com.github.quaoz.common.searches;

import com.github.quaoz.common.Comparisons;
import org.jetbrains.annotations.NotNull;

/**
 * Exponential search works by find the range where the value may be found and then performing a binary search over this
 * range, it requires the array to be sorted
 *
 * <p> Worst-case performance O(log i), Best-case performance O(1), Average performance O(log i) where i is the position
 * of the value being searched for
 **/
public class ExponentialSearch {

	/**
	 * Implements a generic exponential search
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int The index or -1 if it wasn't found
	 */
	public static <T extends Comparable<T>> int find(T @NotNull [] array, T value) {
		int size = array.length;
		int bound = 1;
		int result;

		if (Comparisons.equal(array[0], value)) {
			result = 0;
		} else {
			while (bound < size && Comparisons.smaller(array[bound], value)) {
				bound *= 2;
			}
			result = BinarySearch.find(array, value, bound / 2, Math.min(bound + 1, size));
		}

		return result;
	}
}

