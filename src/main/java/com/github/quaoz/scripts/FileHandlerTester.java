package com.github.quaoz.scripts;

import com.github.quaoz.common.filehandling.FileHandler;

public class FileHandlerTester {
	public static void main(String[] args) {
		// Reads the 9th line of the file (lines are 0 indexed)
		FileHandler.readFromFile("src/main/java/com/github/quaoz/scripts/testfile.txt", 9, 9).forEach(System.out::println);
	}
}
