package com.github.quaoz.scripts;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		addInPlace(1, list);
		addInPlace(2, list);
		addInPlace(0, list);
		addInPlace(3, list);
		addInPlace(-10, list);
		addInPlace(3, list);
		addInPlace(10, list);
		addInPlace(3, list);
		addInPlace(3, list);
		addInPlace(1, list);
		addInPlace(2, list);
		addInPlace(3, list);
		addInPlace(4, list);
		addInPlace(5, list);
		addInPlace(6, list);
		addInPlace(7, list);
		addInPlace(8, list);
		addInPlace(9, list);
		addInPlace(10, list);
		addInPlace(4, list);
		addInPlace(2000, list);
		addInPlace(0, list);

		System.out.println(list);
	}

	private static void addInPlace(Integer integer, @NotNull ArrayList<Integer> list) {
		if (list.size() == 0) {
			list.add(integer);
		} else {
			list.add(find(list, integer), integer);
		}
	}

	private static <E extends Comparable<E>> int find(List<E> list, E value) {
		return find(list, value, 0, list.size() - 1);
	}

	private static <E extends Comparable<E>> int find(List<E> list, E value, int left, int right) {
		int result;

		if (left <= right) {
			// Finds the middle
			final int middle = (left + right) >>> 1;
			final int comp = value.compareTo(list.get(middle));

			// Recursively splits the array and searches the half that may contain the term
			if (comp < 0) {
				result = find(list, value, left, middle - 1);
			} else if (comp > 0) {
				result = find(list, value, middle + 1, right);
			} else {
				result = middle;
			}
		} else {
			result = left;
		}

		return result;
	}
}
