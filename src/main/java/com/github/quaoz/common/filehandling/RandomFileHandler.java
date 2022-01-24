package com.github.quaoz.common.filehandling;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomFileHandler {
	public static void main(String[] args) {
		File file = new File("src/main/java/com/github/quaoz/scripts/testfile.txt");

		for (int i = 0; i < 10; i++) {
			randomRead(file, i);
		}
	}

	public static void randomRead(File file, int start) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws")) {
			randomAccessFile.seek(start);
			char letter = randomAccessFile.readChar();
			System.out.println(letter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void randomWrite(File file, char c) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws")) {
			randomAccessFile.writeChar(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
