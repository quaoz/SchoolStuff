package com.github.quaoz.scripts;

import com.github.quaoz.common.filehandling.SequentialFileHandler;

public class FileHandlerTester {
	public static void main(String[] args) {
		final String fileName = "src/main/java/com/github/quaoz/scripts/testfile.txt";

		// Reads the 9th line of the file (lines are 0 indexed)
		SequentialFileHandler.read(fileName, 9, 9).forEach(System.out::println);

		SequentialFileHandler.deleteLine(fileName, 1);
		SequentialFileHandler.insert(fileName, "inserted", 3);
		SequentialFileHandler.writeAt(fileName, "written", 5);
	}
}
