package com.github.quaoz.common.searches;

import com.github.quaoz.common.arrayutils.Comparisons;
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
	 * Implements a generic exponential search which returns the index by default
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int    Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	public static <T extends Comparable<T>> int find(T @NotNull [] array, T value) {
		return find(array, value, false);
	}

	/**
	 * Implements a generic exponential search
	 *
	 * @param array    The array to be searched
	 * @param value    The value being searched for
	 * @param contains Whether to return the index of the value or just if the array contains it
	 * @param <T>      The array type
	 *
	 * @return int        Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	public static <T extends Comparable<T>> int find(T @NotNull [] array, T value, boolean contains) {
		int size = array.length;
		int bound = 1;
		int result;

		if (Comparisons.equal(array[0], value)) {
			result = contains ? -1 : 0;
		} else {
			while (bound < size && Comparisons.smaller(array[bound], value)) {
				bound *= 2;
			}
			result = BinarySearch.find(array, value, bound / 2, Math.min(bound + 1, size), contains);
		}

		return result;
	}
}

