package com.github.quaoz.tasks.bubblesort;

import com.github.quaoz.common.arrayutils.Swap;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Bubble sort is the simplest sorting algorithm, works by comparing adjacent elements and swapping them if they are
 * out of order
 *
 * <p> Worst-case performance O(n^2), Best-case performance O(n), Average performance O(n^2)
 */
public class Main {

	/**
	 * Implements a generic bubble sort algorithm
	 *
	 * @param array     The array to be sorted
	 * @param ascending Whether to sort the array in ascending or descending order
	 *
	 * @return The sorted array
	 */
	private static <T extends Comparable<T>> T[] sort(T @NotNull [] array, boolean ascending) {
		boolean swapped;
		int comparisons = 0;

		for (int i = 0, size = array.length; i < size - 1; ++i) {
			swapped = false;
			for (int j = 0; j < size - 1 - i; ++j) {
				// Swaps the elements if they are not in the right order depending on ascending
				if (array[j].compareTo(array[j + 1]) > 0 && ascending) {
					Swap.swap(array, j, j + 1);
					swapped = true;
					comparisons++;
				} else if (array[j].compareTo(array[j + 1]) < 0 && !ascending) {
					Swap.swap(array, j, j + 1);
					swapped = true;
					// If this code executes two comparisons must have been made for the if and else if
					comparisons += 2;
				} else {
					// If this code executes two comparisons must have been made for the if and else if
					comparisons += 2;
				}
			}
			comparisons++;
			if (!swapped) {
				break;
			}
		}
		// Bubble sort should average O(n^2) where n is the number of elements in the array
		System.out.println(comparisons);
		return array;
	}

	public static void main(String[] args) {
		Integer[] array = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(100))
				.limit(100)
				.unordered()
				.boxed()
				.toArray(Integer[]::new);

		System.out.println(Arrays.toString(sort(array, true)));
		System.out.println(Arrays.toString(sort(array, false)));
		System.out.println(Arrays.toString(sort(array, true)));

		final String string = "the quick brown fox jumps over the lazy dog";

		// Converts the string to a Character array
		final Character[] letters = string.chars()
				.mapToObj(c -> (char) c)
				.toArray(Character[]::new);

		System.out.println(Arrays.toString(sort(letters, true)));
	}
}
