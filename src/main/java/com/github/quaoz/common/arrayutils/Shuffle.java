package com.github.quaoz.common.arrayutils;

import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class to shuffle elements of an array
 */
public class Shuffle {

	/**
	 * Randomly swaps each element of an array
	 *
	 * @param array The array to be shuffled
	 * @param <T>   The value type
	 *
	 * @return T[]    The shuffled array
	 */
	public static <T extends Comparable<T>> T[] shuffle(T @NotNull [] array) {
		Random r = ThreadLocalRandom.current();

		for (int i = 0; i < array.length; i++) {
			Swap.swap(array, i, r.nextInt(array.length));
		}

		return array;
	}
}
