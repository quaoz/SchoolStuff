package com.github.quaoz.common.arrayutils;

import org.jetbrains.annotations.NotNull;

/**
 * Class to reverse the order of elements in an array
 */
public class Reverse {

	/**
	 * Reverses the order of elements in an array
	 *
	 * @param array The array to reverse
	 * @param <T>   The array type
	 */
	public static <T extends Comparable<T>> void reverse(T @NotNull [] array) {
		for (int i = 0; i < array.length / 2; i++) {
			Swap.swap(array, i, array.length - 1 - i);
		}
	}
}
