package com.github.quaoz.common.filehandling;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;

public class SequentialFileHandler {

	/**
	 * Writes to a file, appending by default
	 *
	 * @param file The file to write to
	 * @param text The text to write
	 */
	public static void write(File file, String text) {
		write(file, text, true);
	}


	/**
	 * Writes to a file
	 *
	 * @param file   The file to write to
	 * @param text   The text to write
	 * @param append Whether to append the file or not
	 */
	public static void write(File file, String text, boolean append) {
		try (
				// Create a PrintWriter
				PrintWriter printWriter = new PrintWriter(new FileWriter(file, append))
		) {
			printWriter.println(text);
		} catch (IOException e) {
			System.out.printf("Failed to write \"%s\" to %s", text, file);
			e.printStackTrace();
		}
	}

	/**
	 * Writes to a specific line in a file, overwriting the previous contents
	 *
	 * @param file The file to write to
	 * @param text The text to write
	 * @param line The line to start writing at
	 */
	public static void writeAt(File file, String text, Integer line) throws IOException {
		// Create a temp file
		File tmp = File.createTempFile("copying", ".tmp");

		try (
				// Create print writer and buffered reader
				PrintWriter printWriter = new PrintWriter(new FileWriter(tmp, false));
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file))
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

		} catch (IOException e) {
			System.out.printf("Failed to write \"%s\" at line %d in %s", text, line, file);
			e.printStackTrace();
		}

		copy(tmp.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);

		if (!tmp.delete()) {
			tmp.deleteOnExit();
			System.err.printf("Failed to delete the temp file %s", tmp.getPath());
		}
	}

	/**
	 * Writes to a specific line in a file, overwriting the previous contents
	 *
	 * @param file  The file to write to
	 * @param lines The lines to write
	 * @param line  The line to start writing at
	 */
	public static void writeAllAt(File file, String[] lines, Integer line) throws IOException {
		// Create tmp file
		File tmp = File.createTempFile("copying", ".tmp");

		try (
				// Create a PrintWriter and BufferedReader
				PrintWriter printWriter = new PrintWriter(new FileWriter(tmp, false));
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file))
		) {
			String currentLineText;
			int currentLine = 0;

			while ((currentLineText = bufferedReader.readLine()) != null) {
				if (Objects.equals(line, currentLine++)) {
					for (String s : lines) {
						printWriter.println(s);
						currentLine++;
					}
					currentLine--;
				} else {
					printWriter.println(currentLineText);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		copy(tmp.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);

		if (!tmp.delete()) {
			tmp.deleteOnExit();
			System.err.printf("Failed to delete the temp file %s", tmp.getPath());
		}
	}

	/**
	 * Writes to a file, appending by default
	 *
	 * @param file The file to write to
	 * @param c    The collection of objects to write
	 */
	public static void writeAll(File file, Collection<?> c) {
		writeAll(file, c, false);
	}

	/**
	 * Writes to a file
	 *
	 * @param file   The file to write to
	 * @param c      The collection of objects to write
	 * @param append Whether to append the file or not
	 */
	public static void writeAll(File file, @NotNull Collection<?> c, boolean append) {
		try (
				// Create a PrintWriter
				PrintWriter printWriter = new PrintWriter(new FileWriter(file, append))
		) {
			for (Object o : c) {
				printWriter.println(o);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes a specific line from a file
	 *
	 * @param file The file to write to
	 * @param line The line to delete
	 */
	public static void deleteLine(File file, Integer line) throws IOException {
		// Create tmp file
		File tmp = File.createTempFile("copying", ".tmp");

		try (
				// Create a file writer and print writer
				PrintWriter printWriter = new PrintWriter(new FileWriter(tmp, false));
				// Create a BufferedReader
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file))
		) {
			String currentLineText;
			int currentLine = 0;

			while ((currentLineText = bufferedReader.readLine()) != null) {
				if (Objects.equals(line, currentLine++)) {
					currentLine++;
				} else {
					printWriter.println(currentLineText);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		copy(tmp.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);

		if (!tmp.delete()) {
			tmp.deleteOnExit();
			System.err.printf("Failed to delete the temp file %s", tmp.getPath());
		}
	}

	/**
	 * Deletes all the lines between two bounds in a file
	 *
	 * @param file      The file to write to
	 * @param startLine The line to start deleting at
	 * @param endLine   the line to delete to
	 */
	public static void deleteLines(File file, Integer startLine, Integer endLine) throws IOException {
		// Create tmp file
		File tmp = File.createTempFile("copying", ".tmp");

		try (
				// Create a file writer and print writer
				PrintWriter printWriter = new PrintWriter(new FileWriter(tmp, false));
				// Create a BufferedReader
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file))
		) {
			String currentLineText;
			int currentLine = 0;

			while ((currentLineText = bufferedReader.readLine()) != null) {
				if (currentLine >= startLine && currentLine < endLine) {
					currentLine++;
				} else {
					printWriter.println(currentLineText);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		copy(tmp.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);

		if (!tmp.delete()) {
			tmp.deleteOnExit();
			System.err.printf("Failed to delete the temp file %s", tmp.getPath());

		}
	}

	/**
	 * Inserts the given string into a file
	 *
	 * @param file The file to use
	 * @param text The text to insert
	 * @param line The line to insert at
	 */
	public static void insert(File file, String text, Integer line) throws IOException {
		// Create tmp file
		File tmp = File.createTempFile("copying", ".tmp");

		try (
				// Create a file writer and print writer
				PrintWriter printWriter = new PrintWriter(new FileWriter(tmp, false));

				// Create a BufferedReader
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file))
		) {
			String currentLineText;
			int currentLine = 0;

			while ((currentLineText = bufferedReader.readLine()) != null) {
				if (Objects.equals(line, currentLine++)) {
					printWriter.println(text);
				}
				printWriter.println(currentLineText);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		copy(tmp.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);

		if (!tmp.delete()) {
			tmp.deleteOnExit();
			System.err.printf("Failed to delete the temp file %s", tmp.getPath());
		}
	}

	public static void insertAll(File file, String[] lines, Integer line) throws IOException {
		// Create tmp file
		File tmp = File.createTempFile("copying", ".tmp");

		try (
				// Create a file writer and print writer
				PrintWriter printWriter = new PrintWriter(new FileWriter(tmp, false));

				// Create a BufferedReader
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file))
		) {
			String currentLineText;
			int currentLine = 0;

			while ((currentLineText = bufferedReader.readLine()) != null) {
				if (Objects.equals(line, currentLine++)) {
					for (String s : lines) {
						printWriter.println(s);
					}
				}
				printWriter.println(currentLineText);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		copy(tmp.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		tmp.deleteOnExit();
	}

	/**
	 * Reads the whole contents of a file
	 *
	 * @param file The file to read from
	 *
	 * @return An ArrayList containing all the lines in a file
	 */
	public static @NotNull ArrayList<String> read(File file) {
		return read(file, 0, -1);
	}

	/**
	 * Reads the portion of the file within the given bounds
	 *
	 * @param file      The file to read from
	 * @param startLine The line to start reading from
	 * @param endLine   The line to stop reading at (set to -1 to read to the end)
	 *
	 * @return An ArrayList containing the lines within the given bounds
	 */
	public static @NotNull ArrayList<String> read(File file, Integer startLine, Integer endLine) {
		ArrayList<String> lines = new ArrayList<>();

		try (
				// Create a FileReader and a BufferedReader
				FileReader fileReader = new FileReader(file);
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

	public static void copy(Path source, Path destination, StandardCopyOption standardCopyOption) {
		try {
			Files.copy(source, destination, standardCopyOption);
		} catch (IOException e) {
			System.out.printf("Failed to copy %s to %s with option %s", source, destination, standardCopyOption);
			e.printStackTrace();
		}
	}

	public static void copyLines(File source, File destination, long startLine, long endLine) {
		try (
				// Create a new BufferedReader
				BufferedReader bufferedReader = new BufferedReader(new FileReader(source));

				// Create a file writer and print writer
				PrintWriter printWriter = new PrintWriter(new FileWriter(destination, true))
		) {
			if (startLine == 0 && endLine == -1) {
				// Copying the whole file
				copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} else {
				// Skips to the requested line and returns an iterator
				Iterator<String> iterator = bufferedReader.lines().skip(startLine).iterator();

				if (endLine == -1) {
					// If we are reading from this line to the end of the file we can just iterate through the remaining
					// elements and write them to the file
					iterator.forEachRemaining(printWriter::println);

				} else {
					// Otherwise, we read until the end of the file or until we reach the specified line to stop at
					long line = startLine;
					while (iterator.hasNext() && line <= endLine) {
						printWriter.println(iterator.next());
						line++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
