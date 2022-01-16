package com.github.quaoz.common.sorts;

import com.github.quaoz.common.arrayutils.IsSorted;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class SortTester {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			testSort();
		}
	}

	public static void testSort() {
		Integer[] array = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(20))
				.limit(20)
				.unordered()
				.boxed()
				.toArray(Integer[]::new);

		System.out.println("\nArray: " + Arrays.toString(array) + "\n");

		isSorted(BubbleSort.class.getSimpleName(), BubbleSort.sort(array.clone()));
		isSorted(SelectionSort.class.getSimpleName(), SelectionSort.sort(array.clone()));
		isSorted(MergeSort.class.getSimpleName(), MergeSort.sort(array.clone()));
		isSorted(InsertionSort.class.getSimpleName(), InsertionSort.sort(array.clone()));
		isSorted(ShellSort.class.getSimpleName(), ShellSort.sort(array.clone()));
		isSorted(QuickSort.class.getSimpleName(), QuickSort.sort(array.clone()));
		isSorted(DualPivotQuickSort.class.getSimpleName(), DualPivotQuickSort.sort(array.clone()));
		isSorted(IntroSort.class.getSimpleName(), IntroSort.sort(array.clone()));
		isSorted(DualPivotIntroSort.class.getSimpleName(), DualPivotIntroSort.sort(array.clone()));
		isSorted(TimSort.class.getSimpleName(), TimSort.sort(array.clone()));
		isSorted(HeapSort.class.getSimpleName(), HeapSort.sort(array.clone()));
	}

	private static <T extends Comparable<T>> void isSorted(String name, T[] array) {
		if (!IsSorted.isSorted(array)) {
			System.out.println("- Failed : " + name);
			System.out.println(Arrays.toString(array));
		} else {
			System.out.println("  Passed : " + name);
		}
	}
}
