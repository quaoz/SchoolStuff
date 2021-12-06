package com.github.quaoz.common.sorts;

import com.github.quaoz.common.arrayutils.Shuffle;
import com.github.quaoz.common.timer.Timer;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class SortTimer {
	private final Timer timer = new Timer();
	private final Object2DoubleOpenHashMap<String> means = new Object2DoubleOpenHashMap<>() {{
		put("bubble", 0);
		put("selection", 0);
		put("dual-selection", 0);
		put("merge", 0);
		put("insertion", 0);
		put("shell", 0);
		put("quick", 0);
		put("dual-pivot quick", 0);
		put("intro", 0);
		put("dual-pivot intro", 0);
		put("tim", 0);
		put("heap", 0);
		put("java arrays sort", 0);
	}};
	private boolean quiet;

	/**
	 * Constructor
	 */
	public SortTimer() {
		this.quiet = false;
	}

	/**
	 * Constructor with quiet specified
	 */
	public SortTimer(boolean quiet) {
		this.quiet = quiet;
	}

	public static void main(String[] args) {
		testRandom();
	}

	/**
	 * Example implementation to test the sorting algorithms performance on an array of random numbers
	 */
	private static void testRandom() {
		final SortTimer sortTimer = new SortTimer();

		final int repeatsPerArray = 10;
		final int numArrays = 10;
		final int maxElement = 10000;
		final int size = 10000;

		Integer[] array;

		for (int i = 0; i < numArrays; i++) {
			// Fills array with sorted random numbers
			array = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(maxElement))
					.limit(size)
					.unordered()
					.boxed()
					.toArray(Integer[]::new);

			sortTimer.repeatFastestSort(array, repeatsPerArray);
		}

		sortTimer.calculateMeans(numArrays * repeatsPerArray);
		System.out.println(sortTimer.getTable());
	}

	/**
	 * Resets the means and timer
	 */
	public void resetData() {
		means.forEach((s, aDouble) -> means.replace(s, 0));
		timer.resetTimer();
	}

	/**
	 * @return Is quiet
	 */
	public boolean isQuiet() {
		return quiet;
	}

	/**
	 * @param quiet Boolean quiet
	 */
	public void setQuiet(boolean quiet) {
		this.quiet = quiet;
	}

	/**
	 * Finds the fastest sorting algorithm for an array
	 *
	 * @param array The array to test
	 * @param <T>   The array type
	 *
	 * @return The sorting times
	 */
	public <T extends Comparable<T>> @NotNull Object2LongOpenHashMap<String> fastestSort(T @NotNull [] array) {
		Object2LongOpenHashMap<String> results = new Object2LongOpenHashMap<>();

		timer.startTimerNano();
		BubbleSort.sort(array.clone());
		printResults(results, "bubble");

		timer.startTimerNano();
		SelectionSort.sort(array.clone());
		printResults(results, "selection");

		timer.startTimerNano();
		DualSelectionSort.sort(array.clone());
		printResults(results, "dual-selection");

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
		DualPivotIntroSort.sort(array.clone());
		printResults(results, "dual-pivot intro");

		timer.startTimerNano();
		TimSort.sort(array.clone());
		printResults(results, "tim");

		timer.startTimerNano();
		HeapSort.sort(array.clone());
		printResults(results, "heap");

		timer.startTimerNano();
		Arrays.sort(array.clone());
		printResults(results, "java arrays sort");

		if (!quiet) {
			System.out.println();
		}

		return results;
	}

	/**
	 * Repeatedly times the execution for the different sorting algorithms on a given array, assumes the array should
	 * be shuffled
	 *
	 * @param array   The array to be tested
	 * @param repeats How many times to repeat the test
	 * @param <T>     The array type
	 */
	public <T extends Comparable<T>> void repeatFastestSort(T[] array, int repeats) {
		repeatFastestSort(array, repeats, true);
	}

	/**
	 * Repeatedly times the execution for the different sorting algorithms on a given array
	 *
	 * @param array   The array to be tested
	 * @param repeats How many times to repeat the test
	 * @param shuffle Whether to shuffle the array or not
	 * @param <T>     The array type
	 */
	public <T extends Comparable<T>> void repeatFastestSort(T[] array, int repeats, boolean shuffle) {
		for (int j = 0; j < repeats; j++) {
			// Shuffles the array
			if (shuffle) {
				Shuffle.shuffle(array);
			}

			// Adds the results to the means map
			fastestSort(array).forEach(means::addTo);
		}
	}

	/**
	 * Stops the timer, saves the results and prints them
	 *
	 * @param results     A hash map containing the algorithms names and the results
	 * @param currentSort The current sorting algorithms name
	 */
	private void printResults(@NotNull Object2LongOpenHashMap<String> results, String currentSort) {
		// Stops the timer and saves the time taken
		results.put(currentSort, timer.stopAndGetElapsedTime());

		if (!quiet) {
			System.out.printf("%s%s sort took %d nanoseconds, %d times faster than bubble sort%n",
					currentSort.substring(0, 1).toUpperCase(Locale.ROOT),
					currentSort.substring(1),
					results.getLong(currentSort),
					results.getLong("bubble") / results.getLong(currentSort));
		}

		timer.resetTimer();
	}

	/**
	 * Displays the results formatted into a table
	 */
	public String getTable() {
		final String format = "┃ %-25s ┃ %-20.2f │ %-15.2f ┃%n";

		StringBuilder table = new StringBuilder();
		table.append("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━┓\n");
		table.append("┃ Sort Method               ┃ Mean (nanoseconds)   ┃ x bubble sort   ┃\n");
		table.append("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━━━━━━━━━╇━━━━━━━━━━━━━━━━━┫\n");

		means.forEach((s, aDouble) -> table.append(String.format(format,
				s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1),
				aDouble, means.getDouble("bubble") / aDouble)));

		table.append("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━┷━━━━━━━━━━━━━━━━━┛\n");

		return table.toString();
	}

	/**
	 * Divides all the means by the total number of repeats
	 *
	 * @param totalRepeats The total number of repeats
	 */
	public void calculateMeans(int totalRepeats) {
		means.forEach((String s, Double aDouble) -> means.replace(s, aDouble / totalRepeats));
	}
}
