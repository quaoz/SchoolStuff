package com.github.quaoz.tasks.randomfiles;

import com.github.quaoz.common.filehandling.RandomFileHandler;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class Main {
	public static void main(String[] args) {
		File file = new File("src/main/java/com/github/quaoz/tasks/randomfiles/testfile.txt");

		readEveryOther(file);
		replaceSpaces(file);
		readReverse(file);
	}

	private static void readEveryOther(@NotNull File file) {
		// Jump forwards by two bytes until it reaches the end of the file
		for (long i = 0; i < file.length(); i += 2) {
			System.out.println(RandomFileHandler.readByte(file, i));
		}
	}

	private static void replaceSpaces(@NotNull File file) {
		// Iterates from the start of the file to the end
		for (long i = 0; i < file.length(); i++) {
			// Replaces all spaces with x
			if (Objects.equals(RandomFileHandler.readByte(file, i), ' ')) {
				RandomFileHandler.writeByte(file, i, (byte) 'x');
			}
		}
	}

	private static void readReverse(@NotNull File file) {
		// Iterates from the end of the file to the start
		for (long i = file.length(); i >= 0; i--) {
			System.out.println(RandomFileHandler.readByte(file, i));
		}
	}
}
