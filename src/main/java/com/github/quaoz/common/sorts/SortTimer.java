package com.github.quaoz.common.sorts;

import com.github.quaoz.common.arrayutils.Shuffle;
import com.github.quaoz.common.timer.Timer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class SortTimer {
	private static final Timer timer = new Timer();
    private static final Random random = new Random();

	/**
	 * Finds the fastest sorting algorithm for an array
	 *
	 * @param array The array to test
	 * @return Long[] The sorting times
	 */
	public static <T extends Comparable<T>> @NotNull ArrayList<Long> fastestSort(T @NotNull [] array) {
		ArrayList<Long> sortResults = new ArrayList<>();
        int index = 0;

		timer.startTimerNano();
		BubbleSort.sort(array.clone());
		sortResults.add(timer.stopAndGetElapsedTime());

        System.out.println("Bubble sort took " + sortResults.get(index++) + " nanoseconds");
        timer.resetTimer();

		timer.startTimerNano();
		MergeSort.sort(array.clone());
		sortResults.add(timer.stopAndGetElapsedTime());

        System.out.println("Merge sort took " + sortResults.get(index) + " nanoseconds, " + sortResults.get(0) / sortResults.get(index++) + " times faster than bubble sort");
        timer.resetTimer();

		timer.startTimerNano();
		InsertionSort.sort(array.clone());
		sortResults.add(timer.stopAndGetElapsedTime());

        System.out.println("Insertion sort took " + sortResults.get(index) + " nanoseconds, " + sortResults.get(0) / sortResults.get(index++) + " times faster than bubble sort");
        timer.resetTimer();

		timer.startTimerNano();
		ShellSort.sort(array.clone());
		sortResults.add(timer.stopAndGetElapsedTime());

        System.out.println("Shell sort took " + sortResults.get(index) + " nanoseconds, " + sortResults.get(0) / sortResults.get(index++) + " times faster than bubble sort");
        timer.resetTimer();

        timer.startTimerNano();
        QuickSort.sort(array.clone());
        sortResults.add(timer.stopAndGetElapsedTime());

        System.out.println("Quick sort took " + sortResults.get(index) + " nanoseconds, " + sortResults.get(0) / sortResults.get(index++) + " times faster than bubble sort");
        timer.resetTimer();

        timer.startTimerNano();
        DualPivotQuickSort.sort(array.clone());
        sortResults.add(timer.stopAndGetElapsedTime());

        System.out.println("Dual-pivot quick sort took " + sortResults.get(index) + " nanoseconds, " + sortResults.get(0) / sortResults.get(index++) + " times faster than bubble sort");
        timer.resetTimer();

        timer.startTimerNano();
        IntroSort.sort(array.clone());
        sortResults.add(timer.stopAndGetElapsedTime());

        System.out.println("Intro sort took " + sortResults.get(index) + " nanoseconds, " + sortResults.get(0) / sortResults.get(index++) + " times faster than bubble sort");
        timer.resetTimer();

        timer.startTimerNano();
        TimSort.sort(array.clone());
        sortResults.add(timer.stopAndGetElapsedTime());

        System.out.println("Tim sort took " + sortResults.get(index) + " nanoseconds, " + sortResults.get(0) / sortResults.get(index++) + " times faster than bubble sort");
        timer.resetTimer();

        timer.startTimerNano();
        HeapSort.sort(array.clone());
        sortResults.add(timer.stopAndGetElapsedTime());

        System.out.println("Heap sort took " + sortResults.get(index) + " nanoseconds, " + sortResults.get(0) / sortResults.get(index) + " times faster than bubble sort\n");
        timer.resetTimer();

        return sortResults;
    }

	public static void main(String[] args) {
        final int repeatsPerArray = 10;
        final int numArrays = 100;
        final int maxElement = 100000;
        final int size = 1000;

        final int totalRepeats = numArrays * repeatsPerArray;

        ArrayList<Long> results;
        Integer[] array;

        double bubbleMean = 0;
        double mergeMean = 0;
        double insertionMean = 0;
        double shellMean = 0;
        double quickMean = 0;
        double dualPivotQuickMean = 0;
        double introMean = 0;
        double timMean = 0;
        double heapMean = 0;

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
                dualPivotQuickMean += results.get(5);
                introMean += results.get(6);
                timMean += results.get(7);
                heapMean += results.get(8);
            }
		}

        bubbleMean /= totalRepeats;
        mergeMean /= totalRepeats;
        insertionMean /= totalRepeats;
        shellMean /= totalRepeats;
        quickMean /= totalRepeats;
        dualPivotQuickMean /= totalRepeats;
        introMean /= totalRepeats;
        timMean /= totalRepeats;
        heapMean /= totalRepeats;

        final String format = "┃ %-25s ┃ %-20.2f │ %-15.2f ┃%n";

        System.out.format("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━┓%n");
        System.out.format("┃ Sort Method               ┃ Mean (nanoseconds)   ┃ x bubble sort   ┃%n");
        System.out.format("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━━━━━━━━━╇━━━━━━━━━━━━━━━━━┫%n");

        System.out.format(format, "Bubble sort", bubbleMean, 0f);
        System.out.format(format, "Merge sort", mergeMean, bubbleMean / mergeMean);
        System.out.format(format, "Insertion sort", insertionMean, bubbleMean / insertionMean);
        System.out.format(format, "Shell sort", shellMean, bubbleMean / shellMean);
        System.out.format(format, "Quick sort", quickMean, bubbleMean / quickMean);
        System.out.format(format, "Dual-pivot quick sort", dualPivotQuickMean, bubbleMean / dualPivotQuickMean);
        System.out.format(format, "Intro sort", introMean, bubbleMean / introMean);
        System.out.format(format, "Tim sort", timMean, bubbleMean / timMean);
        System.out.format(format, "Heap sort", heapMean, bubbleMean / heapMean);

        System.out.format("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━┷━━━━━━━━━━━━━━━━━┛%n");
    }
}
