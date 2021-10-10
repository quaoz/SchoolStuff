package common.searches;

import org.jetbrains.annotations.NotNull;

/**
 * Jump search is a search algorithm which improves on linear search by jumping ahead a fixed amount instead of
 * searching each element, requires the array to be sorted
 *
 * <p >Worst-case performance O(√n), Best-case performance O(1), Average performance O(√n)
 **/
public class JumpSearch {

	/**
	 * An overload for find(); implements a generic jump search which returns the index by default
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @return int Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	public static <T extends Comparable<T>> int find(T @NotNull [] array, @NotNull T value) {
		return find(array, value, false);
	}

	/**
	 * Implements a generic jump search
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param contains Whether to return the index of the value or just if the array contains it
	 * @return int Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	public static <T extends Comparable<T>> int find(T @NotNull [] array, @NotNull T value, boolean contains) {
		int result = -2;
		final int length = array.length;
		// Defines the block size
		final int blockSize = (int) Math.floor(Math.sqrt(length));

		// Finds the block which may contain the value
		int limit = blockSize;
		while (value.compareTo(array[limit]) > 0 && limit < length - 1) {
			limit = Math.min(limit + blockSize, length - 1);
		}

		// Uses linear search on the block
		for (int i = limit - blockSize; i <= limit; i++) {
			if (array[i] == value) {
				// Returns -1 if contains is true or the index if contains is false
				result = contains ? -1 : i;
				break;
			}
		}

		return result;
	}
}
