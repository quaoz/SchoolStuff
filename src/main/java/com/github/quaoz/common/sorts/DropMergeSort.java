package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;
import com.github.quaoz.common.arrayutils.Swap;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class DropMergeSort {
	final static boolean DOUBLE_COMPARISONS = true;
	final static int RECENCY = 8;
	final static boolean FAST_BACKTRACKING = true;
	final static boolean EARLY_OUT = false; // true
	final static int EARLY_OUT_TEST_AT = 4;
	final static double EARLY_OUT_DISORDER_FRACTION = 0.60;

	public static void main(String[] args) {
		Integer[] array = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(20))
				.limit(20)
				.unordered()
				.boxed()
				.toArray(Integer[]::new);

		System.out.println(Arrays.toString(array));
		sort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}

	public static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array, int left, int right) {
		ArrayList<T> dropped = new ArrayList<>();
		int droppedInRow = 0;
		int write = 0;
		int read = 0;
		int iteration = 0;
		final int earlyOutStop = array.length / EARLY_OUT_TEST_AT;

		while (read < array.length) {
			iteration++;

			if (EARLY_OUT && iteration == earlyOutStop && dropped.size() > read * EARLY_OUT_DISORDER_FRACTION) {
				int i = write;
				for (T element : dropped) {
					array[i++] = element;
				}

				// Sort using intro sort as we have seen lots of elements and dropped a lot of them
				return IntroSort.sort(array);
			}

			if (write == 0 || Comparisons.biggerOrEqual(array[read], array[write - 1])) {
				// They are in order
				Swap.swap(array, write, read);
				read++;
				write++;
				droppedInRow = 0;
			} else {
				if (DOUBLE_COMPARISONS && droppedInRow == 0 && 2 <= write && Comparisons.biggerOrEqual(array[read], array[write - 2])) {
					dropped.add(array[write - 1]);
					Swap.swap(array, write - 1, read);
					read++;
					continue;
				}

				if (droppedInRow < RECENCY) {
					dropped.add(array[read]);
					read++;
					droppedInRow++;
				} else {
					final int truncToLength = dropped.size() - droppedInRow;

					for (int i = truncToLength; i > 0; i--) {
						dropped.remove(dropped.size() - i);
						dropped.trimToSize();
					}

					read -= droppedInRow;

					int numBacktracked = 1;
					write--;

					if (FAST_BACKTRACKING) {
						T maxOfDropped = Comparisons.max(array);
						while (1 <= write && Comparisons.smaller(maxOfDropped, array[write - 1])) {
							numBacktracked++;
							write--;
						}
					}

					dropped.ensureCapacity(numBacktracked);
					for (int i = 0; i < numBacktracked; i++) {
						dropped.add(array[i + numBacktracked]);
					}

					droppedInRow = 0;
				}
			}
		}

		Collections.sort(dropped);
		int back = array.length;

		for (int i = dropped.size() - 1; i > 1; i--) {
			T lastDropped = dropped.get(i);
			while (0 < write && Comparisons.smaller(lastDropped, array[write - 1])) {
				Swap.swap(array, back - 1, write -1);
				back--;
				write--;
			}

			array[back - 1] = lastDropped;
			back--;
			dropped.remove(dropped.size() - i);
			dropped.trimToSize();
		}

		return array;
	}
}

