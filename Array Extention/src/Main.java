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

		RotatedString[] rotatedString = Arrays.stream(wordArray).map(Main::rotateString).toArray(RotatedString[]::new);

		for (RotatedString i : rotatedString) {
			System.out.println(i.originalString + " became " + i.offsetString + " with an offset of " + i.offset);
		}

		String unrotatedString = UnrotateString(rotatedString);
		System.out.println(unrotatedString);
	}

	private static String[] stringToWordArray(@NotNull String string) {
		return string.split(" ");
	}

	private static RotatedString rotateString(String string) {
		final int length = string.length();
		RotatedString rotatedString = new RotatedString();
		rotatedString.offset = random.nextInt(length + 1) + 1;
		rotatedString.originalString = string;

		char[] stringArray = string.toCharArray();
		char[] rotatedStringArray = new char[length];

		for (int i = 0; i < length; i++) {
			int pos = rotatedString.offset + i;

			if (pos > length - 1) {
				pos %= length;
			}

			rotatedStringArray[pos] = stringArray[i];
		}

		rotatedString.offsetString = new String(rotatedStringArray);
		return rotatedString;
	}

	private static String UnrotateString(RotatedString[] rotatedString) {
		StringBuilder deobfuscatedString = new StringBuilder();

		for (RotatedString string : rotatedString) {
			int length = string.offsetString.length();
			int offset = length - string.offset;
			char[] stringArray = string.offsetString.toCharArray();
			char[] rotatedStringArray = new char[length];

			if (string.offset > length) {
				offset = length - (string.offset % length);
			}

			for (int i = 0; i < length; i++) {
				int pos = offset + i;

				if (pos > length - 1) {
					pos %= length;
				}

				rotatedStringArray[pos] = stringArray[i];
			}

			deobfuscatedString.append(new String(rotatedStringArray));
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
