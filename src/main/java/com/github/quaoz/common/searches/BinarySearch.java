package com.github.quaoz.common.searches;

/**
 * Binary search is a popular search algorithm, it works by repeatedly splitting the array and selecting the half that
 * may contain the value, it requires the array to be sorted
 *
 * <p> Worst-case performance O(log n), Best-case performance O(1), Average performance O(log n)
 **/
public class BinarySearch {

	/**
	 * Implements a generic binary search without having to specify the upper and lower bounds
	 *
	 * @param array    The array to be searched
	 * @param value    The value being searched for
	 * @param contains Whether to return the index of the value or just if the array contains it
	 * @param <T>      The array type
	 *
	 * @return int        Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	public static <T extends Comparable<T>> int find(T[] array, T value, boolean contains) {
		return find(array, value, 0, array.length - 1, contains);
	}

	/**
	 * Implements a generic binary search without having to specify the upper and lower bounds and returning the index
	 * by default
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int    Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	public static <T extends Comparable<T>> int find(T[] array, T value) {
		return find(array, value, 0, array.length - 1, false);
	}

	/**
	 * Implements a generic binary search
	 *
	 * @param array    The array to be searched
	 * @param value    The value being searched for
	 * @param left     The left bound
	 * @param right    The right bound
	 * @param contains Whether to return the index of the value or just if the array contains it
	 * @param <T>      The array type
	 *
	 * @return int        Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	static <T extends Comparable<T>> int find(T[] array, T value, int left, int right, boolean contains) {
		int result;

		if (left <= right) {
			// Finds the middle
			int middle = (left + right) >>> 1;
			int comp = value.compareTo(array[middle]);

			// Recursively splits the array and searches the half that may contain the term
			if (comp < 0) {
				result = find(array, value, left, middle - 1, contains);
			} else if (comp > 0) {
				result = find(array, value, middle + 1, right, contains);
			} else {
				// Returns -1 if contains is true or the index if contains is false
				result = contains ? -1 : middle;
			}
		} else {
			result = -2;
		}

		return result;
	}
}
