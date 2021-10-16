package com.github.quaoz.tasks.tasks;

public class Main {

	public static void main(String[] args) {

		/* numbers from one to ten */
		for (int i = 1; i < 11; i++) {
			System.out.println(i);
		}

		/* 12 times table */

		for (int i = 1; i < 13; i++) {
			System.out.println(i * 12);
		}

		/* box */

		int width = 6;
		int height = 4;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print("#");
			}
			System.out.println();
		}

		/* hollow box */

		for (int i = 0; i < width; i++) {
			System.out.print("#");
		}

		System.out.println();

		for (int i = 1; i < height - 1; i++) {
			System.out.print("#");
			for (int j = 1; j < width - 1; j++) {
				System.out.print(" ");
			}
			System.out.println("#");
		}

		for (int i = 0; i < width; i++) {
			System.out.print("#");
		}

		System.out.println();

		/* prime numbers from 1 to 100*/

		boolean isPrime;

		for (int i = 1; i < 100; i++) {
			isPrime = true;
			for (int j = 2; j < (i / 2) + 1; j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				System.out.println(i + " is prime");
			}
		}
	}
}
