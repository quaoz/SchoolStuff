package com.github.quaoz.common.searches;

import com.github.quaoz.common.Comparisons;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Linear search is the simplest search algorithm, works on sorted and unsorted arrays by comparing the value to each
 * element
 *
 * <p> Worst-case performance O(n), Best-case performance O(1), Average performance O(n)
 */
public class LinearSearch {

	/**
	 * Implements a generic linear search without a sentinel value
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int The index or -1 if it wasn't found
	 */
	public static <T extends Comparable<T>> int find(T @NotNull [] array, @NotNull T value) {
		int result = -1;

		// Loops until it reaches the term or the end of the array
		for (int i = 0; i < array.length; i++) {
			if (Comparisons.equal(array[i], value)) {
				result = i;
				break;
			}
		}

		return result;
	}


	/**
	 * Implements a generic linear search with a sentinel value, the list must have one empty element on the end as it
	 * will be overwritten with the sentinel value
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int The index or -1 if it wasn't found
	 */
	public static <T extends Comparable<T>> int findWithSentinel(T @NotNull [] array, @NotNull T value) {

		// Adds the sentinel value
		array[array.length - 1] = value;

		int count = 0;

		// Loops until it reaches the term or sentinel value
		while (Comparisons.notEqual(value, array[count])) {
			count++;
		}

		return count < array.length - 1
				? count
				: -1;
	}


	/**
	 * Implements a generic linear search which checks to see if the last element is the value and replaces it with a
	 * sentinel value if it isn't
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int The index or -1 if it wasn't found
	 */
	public static <T extends Comparable<T>> int findForceSentinel(T @NotNull [] array, @NotNull T value) {
		return Comparisons.equal(array[array.length - 1], value)
				? array.length - 1
				: findWithSentinel(array, value);
	}

	/**
	 * Implements a generic linear search algorithm to find the indexes of all the occurrences of a value
	 *
	 * @param array The array being searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return Integer ArrayList All the indexes the value was found at
	 */
	public static <T extends Comparable<T>> @NotNull ArrayList<Integer> findOccurrences(T @NotNull [] array, @NotNull T value) {
		ArrayList<Integer> indexes = new ArrayList<>();

		// loops until it reaches the term or the end of the array
		for (int i = 0; i < array.length; i++) {
			if (Comparisons.equal(array[i], value)) {
				indexes.add(i);
			}
		}

		return indexes;
	}

	/**
	 * Implements a generic linear search algorithm to find the total number of occurrences of a value
	 *
	 * @param array The array being searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int The total number of times the value occurred
	 */
	public static <T extends Comparable<T>> int findTotalOccurrences(T @NotNull [] array, @NotNull T value) {
		int occurrences = 0;

		// Loops until it reaches the term or the end of the array
		for (T t : array) {
			if (Comparisons.equal(t, value)) {
				occurrences++;
			}
		}

		return occurrences;
	}

	/**
	 * Implements a generic linear search algorithm to find the total number of occurrences of an array of values
	 *
	 * @param array  The array being searched
	 * @param values The array of values being searched for
	 * @param <T>    The array type
	 *
	 * @return int The total number of times the value occurred
	 */
	public static <T extends Comparable<T>> int findTotalOccurrences(T @NotNull [] array, T @NotNull [] values) {
		int occurrences = 0;

		// Loops until it reaches the term or the end of the array
		for (T t : array) {
			for (T v : values) {
				if (Comparisons.equal(t, v)) {
					occurrences++;
					break;
				}
			}
		}

		return occurrences;
	}

	/**
	 * Implements a generic linear search which will fail fast if it passes the point where the element should be,
	 * the array must be sorted
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int The index or -1 if it wasn't found
	 */
	public static <T extends Comparable<T>> int findSorted(T @NotNull [] array, T value) {
		int result = -1;
		int i = 0;

		while (Comparisons.smallerOrEqual(array[i], value)) {
			if (Comparisons.equal(array[i++], value)) {
				result = i - 1;
				break;
			}
		}

		return result;
	}


}
