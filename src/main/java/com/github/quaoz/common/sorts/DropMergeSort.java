package com.github.quaoz.common.sorts;

import com.github.quaoz.common.Comparisons;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

// BROKEN DO NOT USE
// TODO: fix

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

	@SuppressWarnings("unchekced")
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
				for (int i = 0; i < droppedIndex; i++) {
					array[write + i] = dropped[i];
				}

				// Sort using intro sort as we have seen lots of elements and dropped a lot of them
				return IntroSort.sort(array);
			}

			int lastWrite = write - 1;
			T currentRead = array[read];
			T previous = write == 0 ? array[0] : array[lastWrite];

			if (write == 0 || Comparisons.biggerOrEqual(currentRead, previous)) {
				// They are in order
				array[write++] = currentRead;
				read++;
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
					final int truncToLength = dropped.length - droppedInRow;
					droppedIndex -= droppedInRow;
					read -= droppedInRow;

					int numBacktracked = 1;
					write--;

					if (FAST_BACKTRACKING) {
						T maxOfDropped = Comparisons.max(Arrays.copyOfRange(array, read, read + droppedInRow + 1));
						while (1 <= write && Comparisons.smaller(maxOfDropped, array[write - 1])) {
							numBacktracked++;
							write--;
						}
					}

					for (int i = 0; i < numBacktracked; i++) {
						dropped[droppedIndex++] = array[write + i];
					}

					droppedInRow = 0;
				}
			}
		}

		System.out.println(Arrays.toString(dropped));
		System.out.println(Arrays.toString(array));

		dropped = Arrays.copyOf(dropped, droppedIndex);
		dropped = IntroSort.sort(dropped);

		System.out.println(Arrays.toString(dropped));

		int back = length;
		int pos = 0;
		while (pos < dropped.length) {
			T lastDropped = dropped[pos++];
			while (0 < write && Comparisons.smaller(lastDropped, array[write - 1])) {
				array[--back] = array[--write];
			}
			array[--back] = lastDropped;
		}

		return array;
	}
}

