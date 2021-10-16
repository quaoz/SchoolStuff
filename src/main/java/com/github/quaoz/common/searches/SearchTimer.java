package com.github.quaoz.common.searches;

import com.github.quaoz.common.timer.Timer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class SearchTimer {
	private static final Timer timer = new Timer();
	private static final Random random = new Random();
	private static int randomIndex;

	/**
	 * Finds the fastest search algorithm for an array
	 *
	 * @param array The array to test
	 * @return Long[] The search times
	 */
	public static <T extends Comparable<T>> @NotNull ArrayList<Long> fastestSearch(T @NotNull [] array) {
        // picks a random element from the array
        T randomElement = array[randomIndex];

        ArrayList<Long> searchResults = new ArrayList<>();
        int index;

        int searchNumber = 0;

        System.out.println("Searching for " + randomElement.toString() + ", exists at " + randomIndex + "\n");

        timer.startTimerNano();
        index = LinearSearch.find(array, randomElement);
        searchResults.add(timer.stopAndGetElapsedTime());

        assert index == randomIndex;
        System.out.println("Linear search took " + searchResults.get(searchNumber++) + " nanoseconds");
        timer.resetTimer();

		timer.startTimerNano();
		index = BinarySearch.find(array, randomElement);
		searchResults.add(timer.stopAndGetElapsedTime());

		assert index == randomIndex;
        System.out.println("Binary search took " + searchResults.get(searchNumber) + " nanoseconds, " + searchResults.get(0) / searchResults.get(searchNumber++) + " times faster than linear search");
        timer.resetTimer();

		timer.startTimerNano();
		index = JumpSearch.find(array, randomElement);
		searchResults.add(timer.stopAndGetElapsedTime());

		assert index == randomIndex;
        System.out.println("Jump search took " + searchResults.get(searchNumber) + " nanoseconds, " + searchResults.get(0) / searchResults.get(searchNumber++) + " times faster than linear search");
        timer.resetTimer();

		timer.startTimerNano();
		index = ExponentialSearch.find(array, randomElement);
		searchResults.add(timer.stopAndGetElapsedTime());

		assert index == randomIndex;
        System.out.println("Exponential search took " + searchResults.get(searchNumber) + " nanoseconds, " + searchResults.get(0) / searchResults.get(searchNumber) + " times faster than linear search\n");
        timer.resetTimer();

		return searchResults;
	}

	private static void newRandomIndex(int max) {
		randomIndex = random.nextInt(max);
	}

	public static void main(String[] args) {
		final int repeatsPerArray = 500;
		final int numArrays = 20;
		final int maxElement = 5000000;
		final int size = 1000000;

		final int totalRepeats = numArrays * repeatsPerArray;

		ArrayList<Long> results;
		Integer[] array;

		long linearMean = 0;
		long binaryMean = 0;
		long jumpMean = 0;
		long exponentialMean = 0;

		// Generates several arrays
		for (int i = 0; i < numArrays; i++) {
			// Fills array with sorted random numbers
			array = IntStream.generate(() -> random.nextInt(maxElement))
					.limit(size)
					.sorted()
					.boxed()
					.toArray(Integer[]::new);

			// Calls fastestSearch several times for each array
			for (int j = 0; j < repeatsPerArray; j++) {
				newRandomIndex(array.length);
				results = fastestSearch(array);

				linearMean += results.get(0);
				binaryMean += results.get(1);
				jumpMean += results.get(2);
				exponentialMean += results.get(3);
			}
		}

		linearMean /= totalRepeats;
		binaryMean /= totalRepeats;
		jumpMean /= totalRepeats;
		exponentialMean /= totalRepeats;

		System.out.println("The mean for linear search was " + linearMean + " nanoseconds");
		System.out.println("The mean for binary search was " + binaryMean + " nanoseconds, " + linearMean / binaryMean + " times faster than linear search");
		System.out.println("The mean for jump search was " + jumpMean + " nanoseconds, " + linearMean / jumpMean + " times faster than linear search");
		System.out.println("The mean for exponential search was " + exponentialMean + " nanoseconds, " + linearMean / exponentialMean + " times faster than linear search\n");
	}
}
