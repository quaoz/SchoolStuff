package com.github.quaoz.common.sorts;

import com.github.quaoz.common.arrayutils.Shuffle;
import com.github.quaoz.common.timer.Timer;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

public class SortTimer {
	private static final Timer timer = new Timer();
	private static final Random random = new Random();

	/**
	 * Stops the timer, saves the results and prints them
	 *
	 * @param results     A hash map containing the algorithms names and the results
	 * @param currentSort The current sorting algorithms name
	 */
	private static void printResults(@NotNull Object2LongOpenHashMap<String> results, String currentSort) {
		results.put(currentSort, timer.stopAndGetElapsedTime());
		System.out.printf("%s%s sort took %d nanoseconds, %d times faster than bubble sort%n",
				currentSort.substring(0, 1).toUpperCase(Locale.ROOT),
				currentSort.substring(1),
				results.getLong(currentSort),
				results.getLong("bubble") / results.getLong(currentSort));
		timer.resetTimer();
	}

	/**
	 * Finds the fastest sorting algorithm for an array
	 *
	 * @param array The array to test
	 * @param <T>   The array type
	 *
	 * @return Long[] The sorting times
	 */
	public static <T extends Comparable<T>> @NotNull Object2LongOpenHashMap<String> fastestSort(T @NotNull [] array) {
		Object2LongOpenHashMap<String> results = new Object2LongOpenHashMap<>();

		timer.startTimerNano();
		BubbleSort.sort(array.clone());
		printResults(results, "bubble");

		timer.startTimerNano();
		MergeSort.sort(array.clone());
		printResults(results, "merge");

		timer.startTimerNano();
		InsertionSort.sort(array.clone());
		printResults(results, "insertion");

		timer.startTimerNano();
		ShellSort.sort(array.clone());
		printResults(results, "shell");

		timer.startTimerNano();
		QuickSort.sort(array.clone());
		printResults(results, "quick");

		timer.startTimerNano();
		DualPivotQuickSort.sort(array.clone());
		printResults(results, "dual-pivot quick");

		timer.startTimerNano();
		IntroSort.sort(array.clone());
		printResults(results, "intro");

		timer.startTimerNano();
		TimSort.sort(array.clone());
		printResults(results, "tim");

		timer.startTimerNano();
		HeapSort.sort(array.clone());
		printResults(results, "heap");

		System.out.println();
		return results;
	}

	public static void main(String[] args) {
		final int repeatsPerArray = 10;
		final int numArrays = 10;
		final int maxElement = 100000;
		final int size = 10000;

		final int totalRepeats = numArrays * repeatsPerArray;

		Object2LongOpenHashMap<String> results;
		Integer[] array;

		Object2DoubleOpenHashMap<String> means = new Object2DoubleOpenHashMap<>();
		means.put("bubble", 0);
		means.put("merge", 0);
		means.put("insertion", 0);
		means.put("shell", 0);
		means.put("quick", 0);
		means.put("dual-pivot quick", 0);
		means.put("intro", 0);
		means.put("tim", 0);
		means.put("heap", 0);

		for (int i = 0; i < numArrays; i++) {
			// Fills array with sorted random numbers
			array = IntStream.generate(() -> random.nextInt(maxElement))
					.limit(size)
					.unordered()
					.boxed()
					.toArray(Integer[]::new);

			for (int j = 0; j < repeatsPerArray; j++) {
				// Shuffles the array
				Shuffle.shuffle(array);

				results = fastestSort(array);
				results.forEach(means::addTo);
			}
		}

		means.forEach((String s, Double aDouble) -> means.put(s, aDouble / totalRepeats));

		final String format = "┃ %-25s ┃ %-20.2f │ %-15.2f ┃%n";

		System.out.format("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━┓%n");
		System.out.format("┃ Sort Method               ┃ Mean (nanoseconds)   ┃ x bubble sort   ┃%n");
		System.out.format("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━━━━━━━━━╇━━━━━━━━━━━━━━━━━┫%n");

		means.forEach((s, aDouble) -> System.out.format(format, s.substring(0, 1).toUpperCase(Locale.ROOT) +
				s.substring(1), aDouble, means.getDouble("bubble") / aDouble));

		System.out.format("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━┷━━━━━━━━━━━━━━━━━┛%n");
	}
}
