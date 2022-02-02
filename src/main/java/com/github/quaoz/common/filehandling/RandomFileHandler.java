package com.github.quaoz.common.filehandling;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class RandomFileHandler {

	/**
	 * Reads a byte at a specified position from a file
	 *
	 * @param file  The file to read from
	 * @param pos   The position to read at
	 *
	 * @return The byte at that position
	 */
	public static byte readByte(File file, long pos) {
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
	public static byte @NotNull [] readBytes(File file, long pos, int numBytes) {
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

			return randomAccessFile.readUTF();
		} catch (IOException e) {
			System.out.printf("Failed to read byte at %s in %s", pos, file);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Writes the given byte to a specific position in a file
	 *
	 * @param file The file to write to
	 * @param pos  The position to write at
	 * @param b	   The byte to write
	 */
	public static void writeByte(File file, long pos, byte b) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws")) {
			// seeks to the given position
			randomAccessFile.seek(pos);

			randomAccessFile.writeByte(b);
		} catch (IOException e) {
			System.out.printf("Failed to write byte %s at %d in %s", b, pos, file);
			e.printStackTrace();
		}
	}

	/**
	 * Writes an array of bytes to the file
	 *
	 * @param file  The file to write to
	 * @param pos   The position to write at
	 * @param bytes The bytes to write
	 */
	public static void writeBytes(File file, long pos, byte[] bytes) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws")) {
			// seeks to the given position
			randomAccessFile.seek(pos);

			randomAccessFile.write(bytes);
		} catch (IOException e) {
			System.out.printf("Failed to write bytes %s at %d in %s", Arrays.toString(bytes), pos, file);
			e.printStackTrace();
		}
	}

	public static void writeLine(File file, long pos, String line) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
			randomAccessFile.seek(pos);

			randomAccessFile.writeUTF(line);
		} catch (IOException e) {
			System.out.printf("Failed to write line %s at %s in %s", line, pos, file);
			e.printStackTrace();
		}
	}
}
