package com.github.quaoz.common.arrayutils;

import org.jetbrains.annotations.NotNull;

/**
 * Class to swap elements of arrays
 */
public class Swap {

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 * @param <T>   The value type
	 */
	public static <T> void swap(T @NotNull [] array, int idx, int idy) {
		T swap = array[idx];
		array[idx] = array[idy];
		array[idy] = swap;
	}

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 * @param <T>   The value type
	 */
	public static <T> void swap(int @NotNull [] array, int idx, int idy) {
		array[idx] = (array[idx] + array[idy]) - (array[idy] = array[idx]);
	}

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 * @param <T>   The value type
	 */
	public static <T> void swap(float @NotNull [] array, int idx, int idy) {
		array[idx] = (array[idx] + array[idy]) - (array[idy] = array[idx]);
	}

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 * @param <T>   The value type
	 */
	public static <T> void swap(double @NotNull [] array, int idx, int idy) {
		array[idx] = (array[idx] + array[idy]) - (array[idy] = array[idx]);
	}

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 * @param <T>   The value type
	 */
	public static <T> void swap(byte @NotNull [] array, int idx, int idy) {
		array[idx] = (byte) ((array[idx] + array[idy]) - (array[idy] = array[idx]));
	}

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 * @param <T>   The value type
	 */
	public static <T> void swap(long @NotNull [] array, int idx, int idy) {
		array[idx] = (array[idx] + array[idy]) - (array[idy] = array[idx]);
	}

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 * @param <T>   The value type
	 */
	public static <T> void swap(short @NotNull [] array, int idx, int idy) {
		array[idx] = (short) ((array[idx] + array[idy]) - (array[idy] = array[idx]));
	}
}
