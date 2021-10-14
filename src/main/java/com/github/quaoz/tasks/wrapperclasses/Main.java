package com.github.quaoz.tasks.wrapperclasses;

import com.github.quaoz.common.searches.LinearSearch;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Main {
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Enter a number: ");
		Integer integer = scanner.nextInt();

		System.out.println("The binary equivalent of " + integer + " has " + numberOnes(integer) + " ones in it");
		System.out.println(extension(integer));
	}

	private static int numberOnes(Integer integer) {
		final String binaryString = Integer.toBinaryString(integer);

		// Creates an array of characters for the string
		final Character[] stringCharacters = binaryString.chars()
				.mapToObj(c -> (char) c)
				.toArray(Character[]::new);

		// Searches for all occurrences of 1 in the string
		return LinearSearch.findTotalOccurrences(stringCharacters, '1');
	}

	private static @NotNull String extension(Integer integer) {
		final Character[] chars = {'a', 'b', 'c', 'd', 'e', 'f'};
		final String hexString = Integer.toHexString(integer);
		StringBuilder processedString = new StringBuilder();

		for (int i = 0; i < hexString.length(); i++) {
			String toAppend = "";
			for (char character : chars) {
				// If the string is a character appends the character converted to a hex integer, converted to a binary
				// string, otherwise just appends the character to the string builder
				toAppend = hexString.charAt(i) == character
						? Integer.toBinaryString(Integer.parseInt(Character.toString(character), 16))
						: Character.toString(hexString.charAt(i));
			}
			processedString.append(toAppend);
		}

		return processedString.toString();
	}
}

