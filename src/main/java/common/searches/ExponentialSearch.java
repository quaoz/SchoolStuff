package common.searches;

import org.jetbrains.annotations.NotNull;

public class ExponentialSearch {
	public static <T extends Comparable<T>> int find(T @NotNull [] array, T value, boolean contains) {
		int size = array.length;
		int bound = 1;

		while (bound < size && array[bound].compareTo(value) < 0) {
			bound *= 2;
		}

		return BinarySearch.find(array, value, bound / 2, Math.min(bound + 1, size), contains);
	}
}
