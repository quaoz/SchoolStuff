package common.searches;

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
	 * An overload for find(); A generic linear search without a sentinel value which returns the index by default
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @return int Whether the value was found or not, -2 = not found, anything else is the index
	 */
	public static <T extends Comparable<T>> int find(T @NotNull [] array, @NotNull T value) {
		return find(array, value, false);
	}

	/**
	 * Generic linear search without a sentinel value
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param contains Whether to return the index of the value or just if the array contains it
	 * @return int Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	public static <T extends Comparable<T>> int find(T @NotNull [] array, @NotNull T value, boolean contains) {
		int result = -2;

		// Loops until it reaches the term or the end of the array
		for (int i = 0; i < array.length; i++) {
			if (array[i].compareTo(value) == 0) {
				result = contains ? -1 : i;
				break;
			}
		}

		return result;
	}

	/**
	 * An overload for findWithSentinel(); A generic linear search with a sentinel value which returns the index by
	 * default, the list must have one empty element on the end as it will be overwritten with the sentinel value
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @return int Whether the value was found or not, -2 = not found, anything else is the index
	 */
	public static <T extends Comparable<T>> int findWithSentinel(T @NotNull [] array, @NotNull T value) {
		return findWithSentinel(array, value, false);
	}

	/**
	 * Generic linear search with a sentinel value, the list must have one empty element on the end as it will be
	 * overwritten with the sentinel value
	 *
	 * @param array The array to be searched
	 * @param value The value being searched for
	 * @param contains Whether to return the index of the value or just if the array contains it
	 * @return int Whether the value was found or not, -2 = not found, -1 = found, anything else is the index
	 */
	public static <T extends Comparable<T>> int findWithSentinel(T @NotNull [] array, @NotNull T value, boolean contains) {
		final int result;

		// Adds the sentinel value
		array[array.length - 1] = value;

		int count = 0;

		// Loops until it reaches the term or sentinel value
		while (value.compareTo(array[count]) != 0) {
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
	 * Generic linear search algorithm to find the indexes of all the occurrences of a value
	 *
	 * @param array The array being searched
	 * @param value The value being searched for
	 * @return Integer ArrayList All the indexes the value was found at
	 */
	public static <T extends Comparable<T>> @NotNull ArrayList<Integer> findOccurrences(T @NotNull [] array, @NotNull T value) {
		ArrayList<Integer> indexes = new ArrayList<>();

		// loops until it reaches the term or the end of the array
		for (int i = 0; i < array.length; i++) {
			if (array[i].compareTo(value) == 0) {
				indexes.add(i);
			}
		}

		return indexes;
	}

	/**
	 * Generic linear search algorithm to find the total number of occurrences of a value
	 *
	 * @param array The array being searched
	 * @param value The value being searched for
	 * @return int The total number of times the value occurred
	 */
	public static <T extends Comparable<T>> int findTotalOccurrences(T @NotNull [] array, @NotNull T value) {
		int occurrences = 0;

		// Loops until it reaches the term or the end of the array
		for (T t : array) {
			if (t.compareTo(value) == 0) {
				occurrences++;
			}
		}

		return occurrences;
	}

	/**
	 * Generic linear search algorithm to find the total number of occurrences of an array of values
	 *
	 * @param array The array being searched
	 * @param values The array of values being searched for
	 * @return int The total number of times the value occurred
	 */
	public static <T extends Comparable<T>> int findTotalOccurrences(T @NotNull [] array, T @NotNull [] values) {
		int occurrences = 0;

		// Loops until it reaches the term or the end of the array
		for (T t : array) {
			for (T v : values) {
				if (t.compareTo(v) == 0) {
					occurrences++;
					break;
				}
			}
		}

		return occurrences;
	}

}
