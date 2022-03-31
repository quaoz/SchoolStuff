package com.github.quaoz.common.filehandling;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;

public class RandomFileHandler {

	/**
	 * Reads a byte at a specified position from a file
	 *
	 * @param file The file to read from
	 * @param pos  The position to read at
	 *
	 * @return The byte at that position
	 *
	 * @throws RuntimeException Unable to read the byte from the file
	 */
	public static byte readByte(File file, long pos) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
			// Seeks to the given position
			randomAccessFile.seek(pos);

			return randomAccessFile.readByte();
		} catch (IOException e) {
			System.out.printf("Failed to read byte at %d in %s", pos, file);
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
	 *
	 * @throws RuntimeException Unable to read the bytes from the file
	 */
	public static byte @NotNull [] readBytes(File file, long pos, int numBytes) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
			// Seeks to the given position
			randomAccessFile.seek(pos);

			byte[] bytes = new byte[numBytes];
			randomAccessFile.read(bytes, 0, numBytes);

			return bytes;
		} catch (IOException e) {
			System.err.printf("Failed to read %d bytes at %d in %s", numBytes, pos, file);
			e.printStackTrace();
		}

		throw new RuntimeException("Failed to read bytes from file");
	}

	/**
	 * Reads a line at a specified position in a file
	 *
	 * @param file The file to read from
	 * @param pos  The position to read at
	 *
	 * @return The line at that position
	 *
	 * @throws RuntimeException Unable to read the line from the file
	 */
	public static @NotNull String readLine(File file, long pos) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
			// Seeks to the given position
			randomAccessFile.seek(pos);

			return randomAccessFile.readUTF();
		} catch (IOException e) {
			System.out.printf("Failed to read line at %d in %s", pos, file);
			e.printStackTrace();
		}

		throw new RuntimeException("Failed to read line from file");
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
			// Seeks to the given position
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
			// Seeks to the given position
			randomAccessFile.seek(pos);

			randomAccessFile.write(bytes);
		} catch (IOException e) {
			System.out.printf("Failed to write bytes %s at %d in %s", Arrays.toString(bytes), pos, file);
			e.printStackTrace();
		}
	}

	/**
	 * Writes a UTF string to a file at the given location
	 *
	 * @param file The file to write to
	 * @param pos  The position to write at
	 * @param line The line to write
	 */
	public static void writeLine(File file, long pos, String line) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws")) {
			// Seeks to the given position
			randomAccessFile.seek(pos);

			randomAccessFile.writeUTF(line);
		} catch (IOException e) {
			System.out.printf("Failed to write line %s at %d in %s", line, pos, file);
			e.printStackTrace();
		}
	}

	/**
	 * Deletes the given line from a file
	 *
	 * @param file 		 The file to write to
	 * @param pos        The line to delete
	 * @param lineLength The length of the line
	 */
	public static void deleteLine(File file, long pos, int lineLength) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws")) {
			// Moves all the lines down one place, overwriting the unwanted line
			while (pos < randomAccessFile.length() - lineLength) {
				pos += lineLength;
				randomAccessFile.seek(pos);

				byte[] bytes = new byte[lineLength];
				randomAccessFile.read(bytes, 0, lineLength);
				randomAccessFile.seek(pos - lineLength);
				randomAccessFile.write(bytes);
			}

			// Trim the file
			randomAccessFile.setLength(randomAccessFile.length() - lineLength);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserts the given line into the file
	 *
	 * @param file       The file to write to
	 * @param line       The line to insert
	 * @param pos        The position to write at
	 * @param lineLength The length of the line
	 */
	public static void insertBytes(File file, @NotNull String line, long pos, int lineLength) {
		insertBytes(file, line.getBytes(StandardCharsets.UTF_8), pos, lineLength);
	}

	/**
	 * Inserts the given line into the file
	 *
	 * @param file       The file to write to
	 * @param bytes      The bytes to insert
	 * @param pos        The position to write at
	 * @param lineLength The length of the line
	 */
	public static void insertBytes(File file, byte[] bytes, long pos, int lineLength) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws")) {
			// Seek to the last line in the file
			long index = randomAccessFile.length() - lineLength;
			randomAccessFile.seek(index);

			while (index >= pos) {
				byte[] line = new byte[lineLength];
				// Copy the current line one place forwards
				randomAccessFile.read(line, 0, lineLength);
				randomAccessFile.seek(index + lineLength);
				randomAccessFile.write(line);

				// Seek back
				index -= lineLength;
				randomAccessFile.seek(index);
			}

			// Insert the line
			randomAccessFile.seek(index + lineLength);
			randomAccessFile.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates an iterator which iterates over the lines of the file returning each line as an array of bytes
	 *
	 * @param file       The file to iterate over
	 * @param lineLength The length of the lines
	 *
	 * @return An iterator which iterates over the lines of the file returning each line as an array of bytes
	 *
	 * @throws FileNotFoundException Unable to find the file
	 */
	@NotNull
	public static Iterator<byte[]> iterator(File file, int lineLength) throws FileNotFoundException {
		return new Iterator<>() {
			final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws");
			long pos = 0;

			@Override
			public boolean hasNext() {
				// Check for the next line
				try {
					return randomAccessFile.length() > pos;
				} catch (IOException e) {
					e.printStackTrace();
				}

				throw new RuntimeException("Unable to access file");
			}

			@Override
			public byte[] next() {
				// Read the next line
				byte[] bytes = new byte[lineLength];

				try {
					randomAccessFile.seek(pos);
					randomAccessFile.read(bytes, 0, lineLength);
				} catch (IOException e) {
					e.printStackTrace();
				}

				pos += lineLength;
				return bytes;
			}
		};
	}
}
