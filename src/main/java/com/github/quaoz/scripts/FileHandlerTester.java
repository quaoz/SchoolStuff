package com.github.quaoz.scripts;

import com.github.quaoz.common.filehandling.SequentialFileHandler;

import java.io.File;
import java.io.IOException;

public class FileHandlerTester {
	public static void main(String[] args) {
		File file = new File("src/main/java/com/github/quaoz/scripts/testfile.txt");

		// Reads the 9th line of the file (lines are 0 indexed)
		SequentialFileHandler.read(file, 9, 9).forEach(System.out::println);

		try {
			SequentialFileHandler.deleteLine(file, 1);
			SequentialFileHandler.insert(file, "inserted", 3);
			SequentialFileHandler.writeAt(file, "written", 5);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
