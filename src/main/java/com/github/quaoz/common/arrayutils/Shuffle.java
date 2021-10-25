package com.github.quaoz.common.arrayutils;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class to shuffle elements of an array
 */
public class Shuffle {

	/**
	 * Randomly swaps each element of an array
	 *
	 * @param array The array to be shuffled
	 * @param <T>   The array type
	 *
	 * @return T[]    The shuffled array
	 */
	public static <T extends Comparable<T>> T[] shuffle(T @NotNull [] array) {
		return shuffle(array, 0, array.length);
	}

	/**
	 * Randomly swaps each element within a portion of an array
	 *
	 * @param array The array to be shuffled
	 * @param start The index to start shuffling at
	 * @param end   The index to shuffle to
	 * @param <T>   The array type
	 *
	 * @return T[]    The shuffled array
	 */
	public static <T extends Comparable<T>> T[] shuffle(T @NotNull [] array, int start, int end) {
		while (start < end) {
			Swap.swap(array, start++, ThreadLocalRandom.current().nextInt(end));
		}

		return array;
	}
}
