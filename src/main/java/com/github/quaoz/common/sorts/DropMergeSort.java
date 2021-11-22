package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

// BROKEN DO NOT USE
// TODO: fix

public class DropMergeSort {
	final static boolean DOUBLE_COMPARISONS = false;
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

		Integer[] test = IntroSort.sort(array.clone());

		System.out.println("Array: " + Arrays.toString(array));
		sort(array, 0, array.length - 1);
		System.out.println("Sorted array: " + Arrays.toString(array));
		System.out.println("Sorted array: " + Arrays.toString(test));
	}

	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> T @NotNull [] sort(T @NotNull [] array, int left, int right) {
		final int length = array.length - 1;
		T[] dropped = (T[]) new Comparable[length];

		int droppedInRow = 0;
		int droppedIndex = 0;
		int write = 0;
		int read = 0;

		final int earlyOutStop = length / EARLY_OUT_TEST_AT;

		while (read < length) {
			if (EARLY_OUT && read == earlyOutStop && droppedIndex > read * EARLY_OUT_DISORDER_FRACTION) {
				if (droppedIndex >= 0) {
					System.arraycopy(dropped, 0, array, write, droppedIndex);
				}

				// Sort using intro sort as we have seen lots of elements and dropped a lot of them
				return IntroSort.sort(array);
			}

			int lastWrite = write - 1;
			T currentRead = array[read];
			T previous;

			if (lastWrite >= 0) {
				previous = array[lastWrite];
			} else {
				previous = array[0];
			}

			if (write == 0 || Comparisons.biggerOrEqual(currentRead, previous)) {
				// They are in order
				array[write] = currentRead;
				read++;
				write++;
				droppedInRow = 0;
			} else {
				if (DOUBLE_COMPARISONS && droppedInRow == 0 && 2 <= write && Comparisons.biggerOrEqual(currentRead, array[write - 2])) {
					dropped[droppedIndex++] = previous;
					array[lastWrite] = currentRead;
					read++;
					continue;
				}

				if (droppedInRow < RECENCY) {
					dropped[droppedIndex++] = currentRead;
					read++;
					droppedInRow++;
				} else {
					droppedIndex -= droppedInRow;
					read -= droppedInRow;

					int numBacktracked = 1;
					write--;

					if (FAST_BACKTRACKING) {
						final T maxOfDropped = Comparisons.max(Arrays.copyOfRange(array, read, read + droppedInRow + 1));
						while (1 <= write && Comparisons.smaller(maxOfDropped, array[write - 1])) {
							numBacktracked++;
							write--;
						}
					}

					int i = write;
					while (i < numBacktracked) {
						dropped[droppedIndex++] = array[i++];
					}

					droppedInRow = 0;
				}
			}
		}

		System.out.println("Array: " + Arrays.toString(array));

		dropped = Arrays.copyOf(dropped, droppedIndex);
		System.out.println("Dropped: " + Arrays.toString(dropped));

		dropped = IntroSort.sort(dropped);
		System.out.println("Sorted dropped: " + Arrays.toString(dropped));

		int back = array.length;
		for (int i = dropped.length - 1; i >= 0; i--) {
			T lastDropped = dropped[i];
			while (write > 0 && Comparisons.smaller(lastDropped, array[write - 1])) {
				array[back - 1] = array[write - 1];
				back--;
				write--;
			}
			array[back - 1] = lastDropped;
			back--;
		}

		return array;
	}
}

