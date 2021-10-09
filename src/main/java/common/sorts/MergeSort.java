package common.sorts;

public class MergeSort {

	/**
	 * Generic merge sort algorithm implements.
	 *
	 * @param array The array which should be sorted.
	 * @return sorted array.
	 */
	public static <T extends Comparable<T>> T[] sort(T[] array) {
		return sort(array, 0, array.length - 1);
	}

	/**
	 * @param array The array to be sorted.
	 * @param left The first index of the array.
	 * @param right The last index of the array.
	 */
	private static <T extends Comparable<T>> T[] sort(T[] array, int left, int right) {
		if (left < right) {
			int mid = (left + right) >>> 1;
			sort(array, left, mid);
			sort(array, mid + 1, right);
			return merge(array, left, mid, right);
		}
		return array;
	}

	/**
	 * Merges two parts of an array.
	 *
	 * @param array the array to be merged.
	 * @param left the first index of the array.
	 * @param mid the middle index of the array.
	 * @param right the last index of the array merges two parts of an array in increasing order.
	 */
	private static <T extends Comparable<T>> T[] merge(T[] array, int left, int mid, int right) {
		int length = right - left + 1;

		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Comparable[length];
		int i = left;
		int j = mid + 1;
		int k = 0;

		while (i <= mid && j <= right) {
			temp[k++] = array[i].compareTo(array[j]) <= 0
					? array[i++]
					: array[j++];
		}

		while (i <= mid) {
			temp[k++] = array[i++];
		}

		while (j <= right) {
			temp[k++] = array[j++];
		}

		System.arraycopy(temp, 0, array, left, length);
		return array;
	}
}
