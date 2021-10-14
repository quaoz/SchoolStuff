package com.github.quaoz.tasks.functions;

import java.util.Scanner;

public class Main {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		printTwenty();

		System.out.println("------------------------------");

		System.out.print("Enter a number: ");
		final int number = scanner.nextInt();
		printUpToInt(number);

		System.out.println("------------------------------");

		final double pi = getPi();
		System.out.println("PI: " + pi);

		System.out.println("------------------------------");

		final boolean isOdd = isOdd(number);
		if (isOdd) {
			System.out.println(number + " is odd");
		} else {
			System.out.println(number + "is even");
		}

		System.out.println("------------------------------");

		System.out.print("Enter a message: ");
		final String message = scanner.nextLine();
		System.out.print("How many repetitions: ");
		final int repetitions = scanner.nextInt();
		repeatMessage(message, repetitions);

		System.out.println("------------------------------");

		final int length = lengthOf(message);
		System.out.println("\"" + message + "\" has " + length + " characters");

		System.out.println("------------------------------");

		final int letters = lettersIn(message);
		System.out.println("\"" + message + "\" has " + letters + " letters");

		System.out.println("------------------------------");

		System.out.print("Enter a string: ");
		final String stringOne = scanner.nextLine();
		System.out.print("Enter another string: ");
		final String stringTwo = scanner.nextLine();
		if (sameLength(stringOne, stringTwo)) {
			System.out.println("They are the same length");
		} else {
			System.out.println("They aren't the same length");
		}

		System.out.println("------------------------------");

		final int factorial = factorial(number);
		System.out.println(number + " factorial is " + factorial);
	}

	/* prints the numbers from one to twenty */
	private static void printTwenty() {
		for (int i=1; i<=20; i++) {
			System.out.println(i);
		}
	}

	/* prints the numbers from one to n */
	private static void printUpToInt(final int n) {
		for (int i=1; i<=n; i++) {
			System.out.println(i);
		}
	}

	/* returns Pi */
	private static double getPi() {
		return Math.PI;
	}

	/* returns whether a number is odd */
	private static boolean isOdd(final int n) {
		return n%2!=0;
	}

	/* repeats a message count times */
	private static void repeatMessage(final String message, final int count) {
		for (int i=0; i<count; i++) {
			System.out.println(message);
		}
	}

	/* returns tha length of a message */
	private static int lengthOf(final String message) {
		return message.length();
	}

	/* returns the number of non-space characters in a message */
	private static int lettersIn(String message) {
		return message.replace(" ", "").length();
	}

	/* returns whether two strings are the same length */
	private static boolean sameLength(final String m1, final String m2) {
		return m1.length() == m2.length();
	}

	/* returns n factorial */
	private static int factorial(int n) {
		int factorial = 0;

		for (int i=1; i<n; i++) {
			factorial += n*i;
		}

		return factorial;
	}
}
