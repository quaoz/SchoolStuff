package com.github.quaoz.common.filehandling;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomFileHandler {

	/**
	 * Reads a character at a specified position from a file
	 *
	 * @param file  The file to read from
	 * @param pos   The position to read at
	 *
	 * @return The character at that postion
	 */
	public static @Nullable Character randomRead(File file, long pos) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rs")) {
			randomAccessFile.seek(pos);

			// randomAccessFile.readChar() didn't work as it reads the first two bytes however the text file encoding
			// used one byte per character, so instead we read the byte and cast it to a char
			return (char) randomAccessFile.readByte();
		} catch (IOException e) {
			System.out.printf("Failed to read byte at %d in %s", pos, file);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Writes the given character to a specific position in a file
	 *
	 * @param file The file to write to
	 * @param pos  The position to write at
	 * @param c	   The character to write
	 */
	public static void randomWrite(File file, long pos, char c) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws")) {
			// seeks to the given position
			randomAccessFile.seek(pos);

			// casts the char to a byte and writes it to the file
			randomAccessFile.writeByte((byte) c);
		} catch (IOException e) {
			System.out.printf("Failed to write char %c at %d in %s", c, pos, file);
			e.printStackTrace();
		}
	}
}
