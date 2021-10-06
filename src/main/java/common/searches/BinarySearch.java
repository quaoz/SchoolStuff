package common.searches;

public class BinarySearch {
	/**
	 * Implements a generic binary search
	 *
	 * @param array The array to make the binary search
	 * @param key The number you are looking for
	 * @param left The lower bound
	 * @param right The upper bound
	 * @return the location of the key
	 */
	private <T extends Comparable<T>> int search(T[] array, T key, int left, int right, boolean contains) {
		if (right < left) {
			return -2;
		}

		// finds median
		int median = left + right >>> 1;
		int comp = key.compareTo(array[median]);

		if (comp == 0) {
			return median;
		} else if (comp < 0) {
			return search(array, key, left, median - 1, contains);
		} else {
			return search(array, key, median + 1, right, contains);
		}
	}
}
