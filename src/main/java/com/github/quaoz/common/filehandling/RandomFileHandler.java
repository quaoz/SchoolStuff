package com.github.quaoz.common.filehandling;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomFileHandler {

	/**
	 * Reads a byte at a specified position from a file
	 *
	 * @param file  The file to read from
	 * @param pos   The position to read at
	 *
	 * @return The byte at that position
	 */
	public static @Nullable byte readByte(File file, long pos) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
			randomAccessFile.seek(pos);
			return randomAccessFile.readByte();
		} catch (IOException e) {
			System.err.printf("Failed to read byte at %d in %s", pos, file);
			e.printStackTrace();
		}

		throw new RuntimeException("Failed to read byte from file");
	}

	/**
	 * Reads a set number of bytes at a specified position from a file
	 *
	 * @param file  The file to read from
	 * @param pos   The position to read at
	 *
	 * @return The bytes at that position
	 */
	public static @Nullable byte @NotNull [] readBytes(File file, long pos, int numBytes) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
			randomAccessFile.seek(pos);

			byte[] bytes = new byte[numBytes];
			randomAccessFile.read(bytes, 0, numBytes);

			return bytes;
		} catch (IOException e) {
			System.err.printf("Failed to read byte at %d in %s", pos, file);
			e.printStackTrace();
		}

		throw new RuntimeException("Failed to read byte from file");
	}

	/**
	 * Reads a line at a specified position in a file
	 *
	 * @param file  The file to read from
	 * @param pos   The position to read at
	 *
	 * @return The line at that position
	 */
	public static @Nullable String randomReadLine(File file, long pos) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
			randomAccessFile.seek(pos);

			randomAccessFile.readUTF()
			return randomAccessFile.readLine();
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

	/**
	 * Writes the given character to a specific position in a file
	 *
	 * @param file The file to write to
	 * @param pos  The position to write at
	 * @param line The string to write
	 */
	public static void randomWriteLine(File file, long pos, String line) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws")) {
			// seeks to the given position
			randomAccessFile.seek(pos);

			randomAccessFile.writeUTF(line);
		} catch (IOException e) {
			System.out.printf("Failed to write line %s at %d in %s", line, pos, file);
			e.printStackTrace();
		}
	}
}
