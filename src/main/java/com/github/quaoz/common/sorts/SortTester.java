package com.github.quaoz.common.sorts;

import com.github.quaoz.common.arrayutils.Reverse;
import com.github.quaoz.common.arrayutils.Swap;
import com.github.quaoz.common.console.PrintProgress;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Class containing methods to test different sorts with different sets of data
 */
public class SortTester {
	private static final SortTimer sortTimer = new SortTimer(true);
	private static final int[] arraySizes = {16, 64, 256, 1024, 4096};
	private static final int numTests = 5;
	private static final int runsPerArray = 2;
	private static final int numArrays = 50;
	private static final int totalRepeats = runsPerArray * numArrays;
	private static final String[][] tables = new String[numTests][arraySizes.length];
	private static int arraySize;
	private static long globalStartTime;
	private static int count;

	public static void main(String[] args) {
		final String[] testNames = {"Random Integers", "Sorted Integers", "Reverse Sorted Integers", "Block Sorted Integers", "Random Strings"};

		for (String[] table : tables) {
			Arrays.fill(table, "");
		}

		for (int size : arraySizes) {
			globalStartTime = System.currentTimeMillis();
			count = 0;
			arraySize = size;

			randomIntegers();
			sortedIntegers();
			reverseSortedIntegers();
			blockSortedIntegers();
			randomStrings();
		}

		for (int i = 0; i < numTests; i++) {
			String[][] tablesSplit = new String[arraySizes.length][];
			System.out.println("\n" + testNames[i] + " for lengths " + Arrays.toString(arraySizes) + ":\n");

			for (int j = 0; j < arraySizes.length; j++) {
				tablesSplit[j] = tables[i][j].split("\n");
			}

			for (int j = 0; j < arraySizes.length / 3; j++) {
				for (int k = 0; k < tablesSplit[0].length; k++) {
					System.out.format("%-70.70s %-70.70s %-70.70s %n", tablesSplit[j][k], tablesSplit[j + 1][k], tablesSplit[j + 2][k]);
				}
			}

			if (arraySizes.length % 3 == 1) {
				for (int k = 0; k < tablesSplit[0].length; k++) {
					System.out.format("%-70.70s %n", tablesSplit[arraySizes.length / 3][k]);
				}
			} else if (arraySizes.length % 3 == 2) {
				for (int k = 0; k < tablesSplit[0].length; k++) {
					System.out.format("%-70.70s %-70.70s %n", tablesSplit[arraySizes.length / 3][k], tablesSplit[(arraySizes.length / 3) + 1][k]);
				}
			}
		}
	}

	private static void randomIntegers() {
		for (int i = 0; i < numArrays; i++) {
			Integer[] randomIntegers = IntStream.generate(() -> ThreadLocalRandom.current().nextInt())
					.limit(arraySize)
					.unordered()
					.boxed()
					.toArray(Integer[]::new);

			runAndPrintProgress(randomIntegers);
		}
		printAndReset();
	}

	private static void sortedIntegers() {
		for (int i = 0; i < numArrays; i++) {
			Integer[] sortedIntegers = IntStream.generate(() -> ThreadLocalRandom.current().nextInt())
					.limit(arraySize)
					.sorted()
					.boxed()
					.toArray(Integer[]::new);

			runAndPrintProgress(sortedIntegers);
		}
		printAndReset();
	}

	private static void reverseSortedIntegers() {
		for (int i = 0; i < numArrays; i++) {
			Integer[] reverseSortedIntegers = IntStream.generate(() -> ThreadLocalRandom.current().nextInt())
					.limit(arraySize)
					.sorted()
					.boxed()
					.toArray(Integer[]::new);

			Reverse.reverse(reverseSortedIntegers);
			runAndPrintProgress(reverseSortedIntegers);
		}
		printAndReset();
	}

	private static void blockSortedIntegers() {
		final int blockSize = (int) (arraySize / (5 * (Math.floor((float) arraySize / 512))));

		for (int i = 0; i < numArrays; i++) {
			Integer[] blockSortedIntegers = new Integer[arraySize];
			Arrays.setAll(blockSortedIntegers, j -> j++);

			for (int j = 0; j < (arraySize / blockSize); j++) {
				Swap.swapBlock(blockSortedIntegers, j * blockSize, ((j + 1) * blockSize),
						ThreadLocalRandom.current().nextInt(0, arraySize / blockSize) * blockSize);
			}

			runAndPrintProgress(blockSortedIntegers);
		}
		printAndReset();
	}

	private static void randomStrings() {
		String[] randomStrings = new String[arraySize];

		for (int i = 0; i < numArrays; i++) {
			for (int j = 0; j < arraySize; j++) {
				randomStrings[j] = RandomStringUtils.randomAlphanumeric(10);
			}

			runAndPrintProgress(randomStrings);
		}
		printAndReset();
	}

	private static <T extends Comparable<T>> void runAndPrintProgress(T[] array) {
		sortTimer.repeatFastestSort(array, runsPerArray, false);
		count += runsPerArray;
		PrintProgress.printProgressBar(globalStartTime, totalRepeats * numTests, count);
	}

	private static void printAndReset() {
		sortTimer.calculateMeans(totalRepeats);

		for (int i = 0; i < arraySizes.length; i++) {
			for (int j = 0; j < numTests; j++) {
				if (tables[j][i].equals("")) {
					tables[j][i] = sortTimer.getTable();
					return;
				}
			}
		}

		sortTimer.resetData();
	}
}
