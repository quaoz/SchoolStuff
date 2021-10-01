public class IntArray {
	private int[] array;

	IntArray(int[] array) {
		// copies the value to a new array with one extra element for a sentinel value
		this.array = new int[array.length];
		System.arraycopy(array, 0, this.array, 0, array.length);
	}

	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	public int linearSearch(int term, boolean contains) {
		// adds the sentinel value
		array[array.length - 1] = term;
		int count = 0;

		// loops until it reaches the term or sentinel value
		while (term != array[count]) {
			count++;
		}

		// returns false if the term was the sentinel value
		if (count != array.length - 1) {
			// -2 indicates that the value was not found
			return -2;
		}
		// -1 indicates that the array contains the value
		// the position is returned if it is asked for (contains is false)
		return contains ? -1 : count;
	}

	public int[] bubbleSort(boolean reverse) {
		boolean swapped = true;
		int sortedCount = 0;

		// loops until it makes a pass without swapping something
		while (swapped) {
			swapped = false;

			// loops through each element in the array
			for (int i = 0; i < array.length - 1 - sortedCount; i++) {
				// swaps the elements without storing the in a temporary variable
				if (reverse ? array[i] < array[i + 1] : array[i] > array[i + 1]) {
					array[i] = (array[i] + array[i + 1]) - (array[i + 1] = array[i]);
					swapped = true;
				}
			}
			sortedCount++;
		}

		return array;
	}

	// as merge sort recursively calls itself we have to overload it without any parameters to get it to operate
	// on the array which is part of this class, we can now call intArray.mergeSort() instead of having to do
	// intArray.mergeSort(intArray.getArray(), intArray.getArray().length), this also allows us to return the sorted
	// array as we do with the bubbleSort function
	public int[] mergeSort() {
		mergeSort(array, array.length);
		return array;
	}

	public static void mergeSort(int[] array, int pos) {
		if (pos < 2) {
			return;
		}

		// creates two new arrays
		int mid = pos / 2;
		int[] leftArray = new int[mid];
		int[] rightArray = new int[pos - mid];

		// splits the content of the main array
		System.arraycopy(array, 0, leftArray, 0, mid);

		if (pos - mid >= 0) {
			System.arraycopy(array, mid, rightArray, 0, pos - mid);
		}

		// recursively sorts the arrays
		mergeSort(leftArray, mid);
		mergeSort(rightArray, pos - mid);

		// merges the resulting arrays
		merge(array, leftArray, rightArray, mid, pos - mid);
	}

	public static void merge(int[] array, int[] leftArray, int[] rightArray, int left, int right) {
		int i = 0, j = 0, k = 0;

		// merges the two arrays with the smaller number first
		while (i < left && j < right) {
			array[k++] = leftArray[i] <= rightArray[j]
					? leftArray[i++]
					: rightArray[j++];
		}

		while (i < left) {
			array[k++] = leftArray[i++];
		}
		while (j < right) {
			array[k++] = rightArray[j++];
		}
	}
}
