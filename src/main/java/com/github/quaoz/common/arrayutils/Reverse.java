package com.github.quaoz.common.arrayutils;

/**
 * Class to reverse the order of elements in an array
 */
public class Reverse {

	/**
	 * Reverses the order of elements in an array, assumes the whole thing should be reversed
	 *
	 * @param array The array to reverse
	 */
	public static <T extends Comparable<T>> void reverse(T[] array) {
		reverse(array, 0, array.length);
	}

	/**
	 * Reverses the order of elements in an array
	 *
	 * @param array The array to reverse
	 * @param start The index to start from
	 * @param end   The index to end at
	 * @param <T>   The array type
	 */
	public static <T extends Comparable<T>> void reverse(T[] array, int start, int end) {
		while (start < end) {
			Swap.swap(array, start++, end--);
		}
	}
}
