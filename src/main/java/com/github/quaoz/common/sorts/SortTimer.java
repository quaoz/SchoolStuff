package com.github.quaoz.common.sorts;

import com.github.quaoz.common.arrayutils.Shuffle;
import com.github.quaoz.common.timer.Timer;
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
		BubbleSort.sort(array.clone());
		sortResults.add(timer.stopAndGetElapsedTime());

		System.out.println("Bubble sort took " + sortResults.get(0) + " nanoseconds");
		timer.resetTimer();

		timer.startTimerNano();
		MergeSort.sort(array.clone());
		sortResults.add(timer.stopAndGetElapsedTime());

		System.out.println("Merge sort took " + sortResults.get(1) + " nanoseconds, " + sortResults.get(0) / sortResults.get(1) + " times faster than bubble sort");
		timer.resetTimer();

		timer.startTimerNano();
		InsertionSort.sort(array.clone());
		sortResults.add(timer.stopAndGetElapsedTime());

		System.out.println("Insertion sort took " + sortResults.get(2) + " nanoseconds, " + sortResults.get(0) / sortResults.get(2) + " times faster than bubble sort");
		timer.resetTimer();

		timer.startTimerNano();
		ShellSort.sort(array.clone());
		sortResults.add(timer.stopAndGetElapsedTime());

		System.out.println("Shell sort took " + sortResults.get(3) + " nanoseconds, " + sortResults.get(0) / sortResults.get(3) + " times faster than bubble sort");
		timer.resetTimer();

		timer.startTimerNano();
		QuickSort.sort(array.clone());
		sortResults.add(timer.stopAndGetElapsedTime());

		System.out.println("Quick sort took " + sortResults.get(4) + " nanoseconds, " + sortResults.get(0) / sortResults.get(4) + " times faster than bubble sort");
		timer.resetTimer();

		timer.startTimerNano();
		TimSort.sort(array.clone());
		sortResults.add(timer.stopAndGetElapsedTime());

		System.out.println("Tim sort took " + sortResults.get(5) + " nanoseconds, " + sortResults.get(0) / sortResults.get(5) + " times faster than bubble sort\n");
		timer.resetTimer();

		return sortResults;
	}

	public static void main(String[] args) {
		final int repeatsPerArray = 10;
		final int numArrays = 10;
		final int maxElement = 1000000;
		final int size = 10000;

		final int totalRepeats = numArrays * repeatsPerArray;

		ArrayList<Long> results;
		Integer[] array;

		long bubbleMean = 0;
		long mergeMean = 0;
		long insertionMean = 0;
		long shellMean = 0;
		long quickMean = 0;
		long timMean = 0;

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

				bubbleMean += results.get(0);
				mergeMean += results.get(1);
				insertionMean += results.get(2);
				shellMean += results.get(3);
				quickMean += results.get(4);
				timMean += results.get(5);
			}
		}

		bubbleMean /= totalRepeats;
		mergeMean /= totalRepeats;
		insertionMean /= totalRepeats;
		shellMean /= totalRepeats;
		quickMean /= totalRepeats;
		timMean /= totalRepeats;

		System.out.println("The mean for bubbles sort was " + bubbleMean + " nanoseconds");
		System.out.println("The mean for merge sort was " + mergeMean + " nanoseconds, " + bubbleMean / mergeMean + " times faster than bubble sort");
		System.out.println("The mean for insertion sort was " + insertionMean + " nanoseconds, " + bubbleMean / insertionMean + " times faster than bubble sort");
		System.out.println("The mean for shell sort was " + shellMean + " nanoseconds, " + bubbleMean / shellMean + " times faster than bubble sort");
		System.out.println("The mean for quick sort was " + quickMean + " nanoseconds, " + bubbleMean / quickMean + " times faster than bubble sort");
		System.out.println("The mean for tim sort was " + timMean + " nanoseconds, " + bubbleMean / timMean + " times faster than bubble sort");
	}
}
