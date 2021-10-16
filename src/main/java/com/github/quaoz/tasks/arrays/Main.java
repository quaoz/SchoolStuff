package com.github.quaoz.tasks.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		/* Task one */
		final int[] pi = {3, 1, 4, 1, 5};
		System.out.println(Arrays.toString(pi));

		/* Task two */
		final String[] rainbow = {"red", "orange", "yellow", "green", "blue", "indigo", "violet"};
		System.out.println(Arrays.toString(rainbow));

		/* Task three */
		int[] oneToTen = new int[10];
		for (int i = 0; i < 10; i++) {
			oneToTen[i] = i;
		}

		System.out.println(Arrays.toString(oneToTen));

		/* Task four */
		final String[] daysOfTheWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		boolean[] hasCompSci = new boolean[7];

		for (int i = 0; i < daysOfTheWeek.length; i++) {
			System.out.println("Do you have computer science on " + daysOfTheWeek[i] + "?");
			hasCompSci[i] = scanner.nextLine().equalsIgnoreCase("yes");
		}

		System.out.println(Arrays.toString(daysOfTheWeek));
		System.out.println(Arrays.toString(hasCompSci));

		/* Task five */
		final int[] evenNumbers = {2, 4, 6, 8, 10};
		final int[] oddNumbers = {1, 3, 5, 9, 11};
		final int length = evenNumbers.length + oddNumbers.length;
		int[] sortedArray = new int[length];

		// combines both of the arrays
		System.arraycopy(evenNumbers, 0, sortedArray, 0, evenNumbers.length);
		System.arraycopy(oddNumbers, 0, sortedArray, evenNumbers.length, oddNumbers.length);

		// sorts the array
		Arrays.sort(sortedArray);
		System.out.println(Arrays.toString(sortedArray));

		/* Task six */
		final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		final int input = scanner.nextInt();
		System.out.println(months[input - 1]);

		/* Task seven */
		final char[] xArray = xArray(6);
		System.out.println(Arrays.toString(xArray));

		/* Task eight */
		final char[] arrayFromString = arrayFromString("string");
		System.out.println(Arrays.toString(arrayFromString));

		/* Task nine */
		final int aTimesB = aTimesB(10, 5);
		System.out.println(aTimesB);
	}

	private static char[] xArray(int size) {
		char[] array = new char[size];

		for (int i = 0; i < size; i++) {
			array[i] = 'X';
		}

		return array;
	}

	private static char[] arrayFromString(String string) {
		char[] array = new char[string.length()];

		for (int i = 0; i < string.length(); i++) {
			array[i] = string.charAt(i);
		}

		return array;
	}

	public static int aTimesB(final int a, final int b) {
		int[] array = new int[10000];

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				array[i * j] = i * j;
			}
		}

		return array[a * b];
	}
}
