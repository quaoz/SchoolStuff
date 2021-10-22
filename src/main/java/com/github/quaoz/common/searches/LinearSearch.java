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
	 * Implements a generic linear search without a sentinel value which returns the index by default
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int    Whether the value was found or not, -2 = not found, anything else is the index
	 */
	public static <T extends Comparable<T>> int find(T @NotNull [] array, @NotNull T value) {
		return find(array, value, false);
	}

	/**
	 * Implements a generic linear search without a sentinel value
	 *
	 * @param array    The array to be searched
	 * @param value    The value being searched for
	 * @param contains Whether to return the index of the value or just if the array contains it
	 * @param <T>      The array type
	 *
	 * @return int        Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	public static <T extends Comparable<T>> int find(T @NotNull [] array, @NotNull T value, boolean contains) {
		int result = -2;

		// Loops until it reaches the term or the end of the array
		for (int i = 0; i < array.length; i++) {
			if (Comparisons.equal(array[i], value)) {
				result = contains ? -1 : i;
				break;
			}
		}

		return result;
	}

	/**
	 * Implements a generic linear search with a sentinel value that returns the index by default, the list must have
	 * one empty element on the end as it will be overwritten with the sentinel value
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int    Whether the value was found or not, -2 = not found, anything else is the index
	 */
	public static <T extends Comparable<T>> int findWithSentinel(T @NotNull [] array, @NotNull T value) {
		return findWithSentinel(array, value, false);
	}

	/**
	 * Implements a generic linear search with a sentinel value, the list must have one empty element on the end as it
	 * will be overwritten with the sentinel value
	 *
	 * @param array    The array to be searched
	 * @param value    The value being searched for
	 * @param contains Whether to return the index of the value or just if the array contains it
	 * @param <T>   The array type
	 *
	 * @return int        Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	public static <T extends Comparable<T>> int findWithSentinel(T @NotNull [] array, @NotNull T value, boolean contains) {
		final int result;

		// Adds the sentinel value
		array[array.length - 1] = value;

		int count = 0;

		// Loops until it reaches the term or sentinel value
		while (Comparisons.notEqual(value, array[count])) {
			count++;
		}

		if (count == array.length - 1) {
			// Returns -2 if the term was the sentinel value
			result = -2;
		} else {
			// Returns -1 if contains is true or the index if contains is false
			result = contains ? -1 : count;
		}

		return result;
	}

	/**
	 * Implements a generic linear search with a sentinel value that checks to see if the last element is the value
	 * and replaces it with a sentinel value if it isn't, returns the index by default
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int    Whether the value was found or not, -2 = not found, anything else is the index
	 */
	public static <T extends Comparable<T>> int findForceSentinel(T @NotNull [] array, @NotNull T value) {
		return findForceSentinel(array, value, false);
	}

	/**
	 * Implements a generic linear search which checks to see if the last element is the value and replaces it with a
	 * sentinel value if it isn't
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return int    Whether the value was found or not, -2 = not found, anything else is the index
	 */
	public static <T extends Comparable<T>> int findForceSentinel(T @NotNull [] array, @NotNull T value, boolean contains) {
		return Comparisons.equal(array[array.length - 1], value)
				? contains ? -1 : array.length - 1
				: findWithSentinel(array, value, contains);
	}

	/**
	 * Implements a generic linear search algorithm to find the indexes of all the occurrences of a value
	 *
	 * @param array The array being searched
	 * @param value The value being searched for
	 * @param <T>   The array type
	 *
	 * @return Integer    ArrayList All the indexes the value was found at
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
	 * @return int    The total number of times the value occurred
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
	 * @return int        The total number of times the value occurred
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

}
