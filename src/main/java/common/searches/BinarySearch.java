package common.searches;

/**
 * Binary search is a popular search algorithm, it works by repeatedly splitting the array and selecting the half that
 * may contain the value, it requires the array to be sorted
 *
 * <p> Worst-case performance O(log n), Best-case performance O(1), Average performance O(log n)
 **/
public class BinarySearch {

	/**
	 * An overload for find() so the upper and lower bounds don't have to be specified; implements a generic binary
	 * search
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param contains Whether to return the index of the value or just if the array contains it
	 * @return int Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	public static <T extends Comparable<T>> int find(T[] array, T value, boolean contains) {
		return find(array, value, 0, array.length - 1, contains);
	}

	/**
	 * An overload for find() so the upper and lower bounds don't have to be specified; implements a generic binary
	 * search which returns the index by default
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @return int Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	public static <T extends Comparable<T>> int find(T[] array, T value) {
		return find(array, value, 0, array.length - 1, false);
	}

	/**
	 * Implements a generic binary search, this method should not be directly called
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param left The lower bound
	 * @param right The upper bound
	 * @param contains Whether to return the index of the value or just if the array contains it
	 * @return int Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	static <T extends Comparable<T>> int find(T[] array, T value, int left, int right, boolean contains) {
		int result;

		 if (right >= left) {
			// finds the middle
			int middle = (left + right) >>> 1;
			int comp = value.compareTo(array[middle]);

			// recursively splits the array and searches the half that may contain the term
			if (comp < 0) {
				result = find(array, value, left, middle - 1, contains);
			} else if (comp > 0) {
				result = find(array, value, middle + 1, right, contains);
			} else {
				// returns -1 if contains is true or the index if contains is false
				result = contains ? -1 : middle;
			}
		} else {
			result = -2;
		}

		return result;
	}
}
