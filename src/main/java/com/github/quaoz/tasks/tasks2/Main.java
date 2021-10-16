package com.github.quaoz.tasks.tasks2;

import java.util.Random;
import java.util.Scanner;

public class Main {

	/* creates a scanner object */
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		potato();
		wordRepeater();
		numberGuesser();
	}

	/* iterates until the user enters the word potato*/
	private static void potato() {
		final String potato = "potato";
		String word = "";

		while (!word.equalsIgnoreCase(potato)) {
			System.out.print("Enter a word: ");
			word = scanner.nextLine();
		}
	}

	/* repeats a word for each character it has */
	private static void wordRepeater() {
		System.out.print("Enter a word: ");
		final String word = scanner.nextLine();

		for (int i = 0; i < word.length(); i++) {
			System.out.println(word);
		}
	}

	/* gives the user 10 tries to guess a random number from 0 to 50
	 * tells them if their guess it too high or low and how many guesses
	 * they have left */
	private static void numberGuesser() {
		final Random random = new Random();
		final int randomNumber = random.nextInt(50);

		System.out.println(randomNumber);

		for (int i = 0; i < 10; i++) {
			System.out.print("Guess the number: ");
			int guess = scanner.nextInt();

			if (guess < 0 || guess > 50) {
				System.out.println("Out of range (0 to 50), you have " + (9 - i) + " guesses left");
			} else if (guess > randomNumber) {
				System.out.println("Too high, you have " + (9 - i) + " guesses left");
			} else if (guess == randomNumber) {
				System.out.println("It took you " + (i + 1) + " guesses");
				return;
			} else {
				System.out.println("Too low, you have " + (9 - i) + " guesses left");
			}
		}

		System.out.println("You ran out of guesses.");
	}
}
