package com.github.quaoz.common.filehandling;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandler {
	public static void writeToFile(String fileName, String text) {
		try	(
				// Create a file writer and print writer
				FileWriter fileWriter = new FileWriter(fileName, true);
				PrintWriter printWriter = new PrintWriter(fileWriter)
		) {
			printWriter.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static @NotNull ArrayList<String> readFromFile(String fileName) {
		ArrayList<String> lines = new ArrayList<>();

		try (
				// Create a FileReader and a BufferedReader
				final FileReader fileReader = new FileReader(fileName);
				final BufferedReader bufferedReader = new BufferedReader(fileReader)
		) {
			// Read each line into an array list
			String line = bufferedReader.readLine();
			while (line != null) {
				lines.add(line);
				line = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

	public static @NotNull ArrayList<String> readFromFile(String fileName, int startLine, int endLine) {
		ArrayList<String> lines = new ArrayList<>();

		try (
				// Create a FileReader and a BufferedReader
				final FileReader fileReader = new FileReader(fileName);
				final BufferedReader bufferedReader = new BufferedReader(fileReader);
				Stream<String> stream = Files.lines(Paths.get(fileName))
		) {
			int line = startLine;
			stream = stream.skip(startLine);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}
}
