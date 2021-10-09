package common.sorts;

import common.timer.Timer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class SortTimer {
	private static final Timer timer = new Timer();
	public static final Random random = new Random();

	/**
	 * Finds the fastest sorting algorithm for an array
	 *
	 * @param array The array to test
	 * @return Long[] The sorting times
	 */
	public static <T extends Comparable<T>> @NotNull ArrayList<Long> fastestSort(T @NotNull [] array) {
		ArrayList<Long> sortResults = new ArrayList<>();

		timer.startTimerNano();
		MergeSort.sort(array);
		sortResults.add(timer.stopAndGetElapsedTime());

		System.out.println("Merge sort took " + sortResults.get(0) + " nanoseconds\n");
		timer.resetTimer();

		return sortResults;
	}

	public static void main(String[] args) {
		final int repeats = 10;
		final int maxElement = 1000000;
		final int size = 1000000;

		ArrayList<Long> results;
		Integer[] array;

		long mergeMean = 0;

		// generates several arrays
		for (int i = 0; i < repeats; i++) {
			// fills array with sorted random numbers
			array = IntStream.generate(() -> random.nextInt(maxElement))
					.limit(size)
					.unordered()
					.boxed()
					.toArray(Integer[]::new);

			results = fastestSort(array);

			mergeMean += results.get(0);
		}

		mergeMean /= repeats;

		System.out.println("The mean for merge sort was " + mergeMean);
	}
}