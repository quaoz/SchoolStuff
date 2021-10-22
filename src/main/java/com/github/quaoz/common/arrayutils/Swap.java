package com.github.quaoz.common.arrayutils;

import org.jetbrains.annotations.NotNull;

/**
 * Class to swap elements of arrays
 */
public class Swap {

	/**
	 * Helper method to swap a block of an array
	 *
	 * @param array      The array to perform the swap on
	 * @param blockStart The index of the start of the block to swap
	 * @param blockEnd   The index of the end of the block to swap
	 * @param dest       The index to start copying to
	 * @param <T>        The array type
	 */
	public static <T extends Comparable<T>> void swapBlock(T[] array, int blockStart, int blockEnd, int dest) {
		int i = 0;

		while (i < blockEnd - blockStart) {
			swap(array, blockStart + i, dest + i);
			i++;
		}
	}

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 * @param <T>   The value type
	 */
	public static <T extends Comparable<T>> void swap(T @NotNull [] array, int idx, int idy) {
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
	 */
	public static void swap(int @NotNull [] array, int idx, int idy) {
		array[idx] = (array[idx] + array[idy]) - (array[idy] = array[idx]);
	}

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 */
	public static void swap(float @NotNull [] array, int idx, int idy) {
		array[idx] = (array[idx] + array[idy]) - (array[idy] = array[idx]);
	}

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 */
	public static void swap(double @NotNull [] array, int idx, int idy) {
		array[idx] = (array[idx] + array[idy]) - (array[idy] = array[idx]);
	}

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 */
	public static void swap(byte @NotNull [] array, int idx, int idy) {
		array[idx] = (byte) ((array[idx] + array[idy]) - (array[idy] = array[idx]));
	}

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 */
	public static void swap(long @NotNull [] array, int idx, int idy) {
		array[idx] = (array[idx] + array[idy]) - (array[idy] = array[idx]);
	}

	/**
	 * Helper method for swapping places in array
	 *
	 * @param array The array which elements we want to swap
	 * @param idx   The index of the first element
	 * @param idy   The index of the second element
	 */
	public static void swap(short @NotNull [] array, int idx, int idy) {
		array[idx] = (short) ((array[idx] + array[idy]) - (array[idy] = array[idx]));
	}
}
