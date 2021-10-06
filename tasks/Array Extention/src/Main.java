import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {
	private static final Scanner scanner = new Scanner(System.in);
	private static final Random random = new Random();

	public static void main(String[] args) {
		System.out.print("Enter a string: ");
		final String string = scanner.nextLine();

		// Splits the string by spaces
		final String[] wordArray = stringToWordArray(string);

		// Iterates through each word in wordArray and parses it to the rotateString function
		// Stores the RotatedString object returned by rotateString in the rotatedStrings array
		RotatedString[] rotatedStrings = Arrays.stream(wordArray).map(Main::rotateString).toArray(RotatedString[]::new);

		// Displays all the original strings, rotated versions and offsets
		for (RotatedString i : rotatedStrings) {
			System.out.println(i.originalString + " became " + i.offsetString + " with an offset of " + i.offset);
		}

		// Un-rotates the strings
		String unrotatedString = UnrotateString(rotatedStrings);
		System.out.println(unrotatedString);
	}

	private static String[] stringToWordArray(@NotNull String string) {
		return string.split(" ");
	}

	private static RotatedString rotateString(@NotNull String string) {
		final int length = string.length();
		RotatedString rotatedString = new RotatedString();
		rotatedString.offset = random.nextInt(length) + 1;
		rotatedString.originalString = string;

		// Converts the string to an array of chars and creates an empty char array with the same number of spaces
		char[] stringArray = string.toCharArray();
		char[] rotatedStringArray = new char[length];

		// Maps each character to its offset place in the array
		for (int i = 0; i < length; i++) {
			int pos = rotatedString.offset + i;

			// Keeps the position within the array bounds
			if (pos > length - 1) {
				pos %= length;
			}

			rotatedStringArray[pos] = stringArray[i];
		}

		rotatedString.offsetString = new String(rotatedStringArray);
		return rotatedString;
	}

	/* Explanation:
	*
	* 	If the offset is smaller than the length of the string then it can be reverted to the original string by
	* 	offsetting it by the length minus the offset
	*
	* 	E.g. If the string 'hello' has an offset of 2 it will become 'lohel', taking the length (5) and subtracting the
	* 	offset (2) gives you 3 and when you offset the rotated word (lohel) by three you get back to 'hello'
	*
	* 	If the offset is larger than the length it can be reverted by offsetting it by the length minus the offset mod
	* 	the length
	*
	* 	E.g. If the string 'hello' has an offset of 6 (the maximum possible for that word) it will become 'ohell', when
	* 	you do the offset mod the length you get 1 and then by subtracting that from the length (giving you 4) you have
	* 	the offset needed to get you back to the original word
	* */

	private static String UnrotateString(RotatedString @NotNull [] rotatedString) {
		StringBuilder deobfuscatedString = new StringBuilder();

		for (RotatedString string : rotatedString) {
			int length = string.offsetString.length();
			int offset = length - string.offset;

			// Converts the string to an array of chars and creates an empty char array with the same number of spaces
			char[] stringArray = string.offsetString.toCharArray();
			char[] deobfuscatedStringArray = new char[length];

			// Keeps the offset positive
			if (string.offset > length) {
				offset = length - (string.offset % length);
			}

			// Maps each character to its original place in the array
			for (int i = 0; i < length; i++) {
				int pos = offset + i;

				// Keeps the position within the array bounds
				if (pos > length - 1) {
					pos %= length;
				}

				deobfuscatedStringArray[pos] = stringArray[i];
			}

			// Appends the deobfuscatedStringArray to the string that it will return
			deobfuscatedString.append(new String(deobfuscatedStringArray));
			deobfuscatedString.append(" ");
		}
		return deobfuscatedString.toString();
	}
}
// Used to return the original word, rotated word and the offset
class RotatedString {
	public String originalString;
	public String offsetString;
	public int offset;
}
