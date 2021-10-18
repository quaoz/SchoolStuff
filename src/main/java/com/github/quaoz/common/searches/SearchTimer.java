package com.github.quaoz.common.searches;

import com.github.quaoz.common.timer.Timer;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

public class SearchTimer {
	private static final Timer timer = new Timer();
	private static final Random random = new Random();
	private static int randomIndex;

	/**
	 * Stops the timer, saves the results and prints them
	 *
	 * @param results       A hash map containing the algorithms names and the results
	 * @param currentSearch The current sorting algorithms name
	 */
	private static void printResults(@NotNull Object2LongOpenHashMap<String> results, String currentSearch) {
		results.put(currentSearch, timer.stopAndGetElapsedTime());
		System.out.printf("%s%s search took %d nanoseconds, %d times faster than linear search%n", currentSearch.substring(0, 1).toUpperCase(),
				currentSearch.substring(1), results.getLong(currentSearch), results.getLong("linear") / results.getLong(currentSearch));
		timer.resetTimer();
	}

	/**
	 * Finds the fastest search algorithm for an array
	 *
	 * @param array The array to test
	 * @param <T>   The array type
	 *
	 * @return Long[]    The search times
	 */
	public static <T extends Comparable<T>> @NotNull Object2LongOpenHashMap<String> fastestSearch(T @NotNull [] array) {
		// picks a random element from the array
		T randomElement = array[randomIndex];
		Object2LongOpenHashMap<String> results = new Object2LongOpenHashMap<>();

		System.out.println("Searching for " + randomElement.toString() + ", exists at " + randomIndex + "\n");

		timer.startTimerNano();
		LinearSearch.find(array, randomElement);
		printResults(results, "linear");

		timer.startTimerNano();
		BinarySearch.find(array, randomElement);
		printResults(results, "binary");

		timer.startTimerNano();
		JumpSearch.find(array, randomElement);
		printResults(results, "jump");

		timer.startTimerNano();
		ExponentialSearch.find(array, randomElement);
		printResults(results, "exponential");

		return results;
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

		Object2LongOpenHashMap<String> results;
		Integer[] array;

		Object2DoubleOpenHashMap<String> means = new Object2DoubleOpenHashMap<>();
		means.put("linear", 0);
		means.put("binary", 0);
		means.put("jump", 0);
		means.put("exponential", 0);

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
				results.forEach(means::addTo);
			}
		}

		means.forEach((String s, Double aDouble) -> means.put(s, aDouble / totalRepeats));

		final String format = "┃ %-25s ┃ %-20.2f │ %-15.2f ┃%n";

		System.out.format("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━┓%n");
		System.out.format("┃ Search Method             ┃ Mean (nanoseconds)   ┃ x linear search ┃%n");
		System.out.format("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━━━━━━━━━╇━━━━━━━━━━━━━━━━━┫%n");

		means.forEach((s, aDouble) -> System.out.format(format, s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1), aDouble, means.getDouble("linear") / aDouble));

		System.out.format("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━┷━━━━━━━━━━━━━━━━━┛%n");
	}
}
