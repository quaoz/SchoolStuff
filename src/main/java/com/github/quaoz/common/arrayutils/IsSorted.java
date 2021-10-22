package com.github.quaoz.common.arrayutils;

import com.github.quaoz.common.Comparisons;
import org.jetbrains.annotations.NotNull;

/**
 * Class to check if an array is sorted
 */
public class IsSorted {

	/**
	 * Checks to see if an array is sorted, checks the whole array
	 *
	 * @param array The array to check
	 *
	 * @return Boolean Whether the array is sorted
	 */
	public static <T extends Comparable<T>> boolean isSorted(T @NotNull [] array) {
		return isSorted(array, 0, array.length - 1);
	}

	/**
	 * Checks to see if a portion of an array is sorted
	 *
	 * @param array The array to check
	 * @param start The index to start checking from
	 * @param end   The index to checking to
	 * @param <T>   The array type
	 *
	 * @return Boolean Whether the array is sorted
	 */
	public static <T extends Comparable<T>> boolean isSorted(T @NotNull [] array, int start, int end) {
		boolean sorted = true;

		for (int i = start; i < end; i++) {
			if (Comparisons.bigger(array[i], array[i + 1])) {
				sorted = false;
				break;
			}
		}

		return sorted;
	}

	/**
	 * Checks to see if a portion of an array is reverse sorted, checks the whole array
	 *
	 * @param array The array to check
	 *
	 * @return Boolean Whether the array is reverse sorted
	 */
	public static <T extends Comparable<T>> boolean isReverseSorted(T @NotNull [] array) {
		return isReverseSorted(array, 0, array.length - 1);
	}

	/**
	 * Checks to see if a portion of an array is reverse sorted
	 *
	 * @param array The array to check
	 * @param start The index to start checking from
	 * @param end   The index to checking to
	 * @param <T>   The array type
	 *
	 * @return Boolean Whether the array is reverse sorted
	 */
	public static <T extends Comparable<T>> boolean isReverseSorted(T @NotNull [] array, int start, int end) {
		boolean sorted = true;

		for (int i = start; i < end; i++) {
			if (Comparisons.smaller(array[i], array[i + 1])) {
				sorted = false;
				break;
			}
		}

		return sorted;
	}

	/**
	 * Checks to see if an array is sorted or reverse sorted, checks the whole array
	 *
	 * @param array The array to check
	 *
	 * @return Boolean Whether the array is sorted in any way
	 */
	public static <T extends Comparable<T>> boolean isAnySorted(T @NotNull [] array) {
		return isAnySorted(array, 0, array.length - 1);
	}

	/**
	 * Checks to see if a portion of an array is sorted or reverse sorted
	 *
	 * @param array The array to check
	 * @param start The index to start checking from
	 * @param end   The index to checking to
	 * @param <T>   The array type
	 *
	 * @return Boolean Whether the array is sorted in any way
	 */
	public static <T extends Comparable<T>> boolean isAnySorted(T @NotNull [] array, int start, int end) {
		return Comparisons.smaller(array[start], array[start + 1])
				? isSorted(array, start + 1, end)
				: isReverseSorted(array, start + 1, end);
	}
}
