package tasks.intarray;

import org.jetbrains.annotations.NotNull;

public class IntArray {
	private int[] array;

	IntArray(int @NotNull [] array) {
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

		// returns if the term was the sentinel value
		if (count == array.length - 1) {
			// returns -2 if the value isn't found
			return -2;
		}
		// returns -1 if contains is true (you only want to know whether the array has teh value in it) or the
		// index if contains is false
		return contains ? -1 : count;
	}

	// overloads binary search as it recursively calls itself (means we don't have to give it the extra parameters)
	public int binarySearch(int term, boolean contains) {
		return binarySearch(array, term, contains, 0, array.length);
	}

	public int binarySearch(int[] array, int term, boolean contains, int left, int right) {
		// returns -2 if the value isn't found
		if (right < left) {
			return -2;
		}

		// finds the middle value
		int median = (left + right) / 2;

		// recursively searches the array
		if (term == array[median]) {
			// returns -1 if contains is true (you only want to know whether the array has teh value in it) or the
			// index if contains is false
			return contains ? -1 : median;
		} else if (term > array[median]) {
			return binarySearch(array, term, contains, median - 1, right);
		} else {
			return binarySearch(array, term, contains, left, median + 1);
		}
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

	// overloads merge sort as it recursively calls itself (means we don't have to give it the extra parameters)
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
