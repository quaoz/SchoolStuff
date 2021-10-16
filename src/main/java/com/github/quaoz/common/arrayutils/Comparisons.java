package com.github.quaoz.common.arrayutils;

import org.jetbrains.annotations.NotNull;

/**
 * Class to simplify comparisons between two objects
 */
public class Comparisons {

	/**
	 * Method to see if base is smaller than value
	 *
	 * @param base  Base value to be compared to
	 * @param value Value to compare
	 * @param <T>   The value type
	 *
	 * @return boolean    Whether base is smalled than value
	 */
	public static <T extends Comparable<T>> boolean smaller(@NotNull T base, T value) {
		return base.compareTo(value) < 0;
	}

	/**
	 * Method to see if base is bigger than value
	 *
	 * @param base  Base value to be compared to
	 * @param value Value to compare
	 * @param <T>   The value type
	 *
	 * @return boolean    Whether base is bigger than value
	 */
	public static <T extends Comparable<T>> boolean bigger(@NotNull T base, T value) {
		return base.compareTo(value) > 0;
	}

	/**
	 * Method to see if base is equal to value
	 *
	 * @param base  Base value to be compared to
	 * @param value Value to compare
	 * @param <T>   The value type
	 *
	 * @return boolean    Whether base equals value
	 */
	public static <T extends Comparable<T>> boolean equal(@NotNull T base, T value) {
		return base.compareTo(value) == 0;
	}

	/**
	 * Method to see if base is not equal to value
	 *
	 * @param base  Base value to be compared to
	 * @param value Value to compare
	 * @param <T>   The value type
	 *
	 * @return boolean    Whether base doesn't equals value
	 */
	public static <T extends Comparable<T>> boolean notEqual(@NotNull T base, T value) {
		return base.compareTo(value) != 0;
	}
}
