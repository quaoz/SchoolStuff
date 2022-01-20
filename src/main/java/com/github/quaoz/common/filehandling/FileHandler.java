package com.github.quaoz.common.filehandling;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileHandler {

	/**
	 * Writes to a file, appending by default
	 *
	 * @param fileName The file to write to
	 * @param text     The text to write
	 */
	public static void write(String fileName, String text) {
		write(fileName, text, true);
	}


	/**
	 * Writes to a file
	 *
	 * @param fileName The file to write to
	 * @param text     The text to write
	 * @param append   Whether to append the file or not
	 */
	public static void write(String fileName, String text, boolean append) {
		try (
				// Create a file writer and print writer
				FileWriter fileWriter = new FileWriter(fileName, append);
				PrintWriter printWriter = new PrintWriter(fileWriter)
		) {
			printWriter.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeAt(String fileName, String text, Integer line) {
		try (
				// Create a file writer and print writer
				PrintWriter printWriter = new PrintWriter(new FileWriter("src/main/java/com/github/quaoz/tmp/copying.txt", false));

				// Create a BufferedReader
				BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))
		) {
			String currentLineText;
			int currentLine = 0;

			while ((currentLineText = bufferedReader.readLine()) != null) {
				if (Objects.equals(line, currentLine++)) {
					printWriter.println(text);
				} else {
					printWriter.println(currentLineText);
				}
			}

			try (
					FileChannel source = new FileInputStream("src/main/java/com/github/quaoz/tmp/copying.txt").getChannel();
					FileChannel destination = new FileOutputStream(fileName).getChannel()
			) {
				destination.transferFrom(source, 0, source.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes to a file, appending by default
	 *
	 * @param fileName The file to write to
	 * @param c        The collection of objects to write
	 */
	public static void writeAll(String fileName, Collection<?> c) {
		writeAll(fileName, c, true);
	}

	/**
	 * Writes to a file
	 *
	 * @param fileName The file to write to
	 * @param c        The collection of objects to write
	 * @param append   Whether to append the file or not
	 */
	public static void writeAll(String fileName, @NotNull Collection<?> c, boolean append) {
		try (
				// Create a file writer and print writer
				FileWriter fileWriter = new FileWriter(fileName, append);
				PrintWriter printWriter = new PrintWriter(fileWriter)
		) {
			for (Object o : c) {
				printWriter.println(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the whole contents of a file
	 *
	 * @param fileName The file to read from
	 *
	 * @return An ArrayList containing all the lines in a file
	 */
	public static @NotNull ArrayList<String> read(String fileName) {
		return read(fileName, 0, -1);
	}

	/**
	 * Reads the portion of the file within the given bounds
	 *
	 * @param fileName  The file to read from
	 * @param startLine The line to start reading from
	 * @param endLine   The line to stop reading at (set to -1 to read to the end)
	 *
	 * @return An ArrayList containing the lines within the given bounds
	 */
	public static @NotNull ArrayList<String> read(String fileName, Integer startLine, Integer endLine) {
		ArrayList<String> lines = new ArrayList<>();

		try (
				// Create a FileReader and a BufferedReader
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader)
		) {
			if (startLine == 0 && endLine == -1) {
				// If reading the whole file we can just collect all the lines and cast it to an ArrayList
				lines = (ArrayList<String>) bufferedReader.lines().collect(Collectors.toList());
			} else {
				// Skips to the requested line and returns an iterator
				Iterator<String> iterator = bufferedReader.lines().skip(startLine).iterator();

				if (endLine == -1) {
					// If we are reading from this line to the end of the file we can just iterate through the remaining
					// elements and add them to the ArrayList
					iterator.forEachRemaining(lines::add);
				} else {
					// Otherwise, we read until the end of the file or until we reach the specified line to stop at
					int line = startLine;
					while (iterator.hasNext() && line <= endLine) {
						lines.add(iterator.next());
						line++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}
}
