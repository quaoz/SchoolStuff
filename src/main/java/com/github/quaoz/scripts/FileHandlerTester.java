package com.github.quaoz.scripts;

import com.github.quaoz.common.filehandling.FileHandler;

public class FileHandlerTester {
	public static void main(String[] args) {
		final String fileName = "src/main/java/com/github/quaoz/scripts/testfile.txt";

		// Reads the 9th line of the file (lines are 0 indexed)
		FileHandler.read(fileName, 9, 9).forEach(System.out::println);

		FileHandler.deleteLine(fileName, 1);
		FileHandler.insert(fileName, "inserted", 3);
		FileHandler.writeAt(fileName, "written", 5);
	}
}
