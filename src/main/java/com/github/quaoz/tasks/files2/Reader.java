package com.github.quaoz.tasks.files2;

import com.github.quaoz.common.arrayutils.Swap;
import com.github.quaoz.common.datastructures.ListInterpreter;
import com.github.quaoz.common.sorts.IntroSort;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
	private final String fileName;
	private ArrayList<String> lines;

	public Reader(String fileName) {
		this.fileName = fileName;
		lines = new ArrayList<>();
	}

	/**
	 * Reads the lines from a file
	 *
	 * @return An array list containing the lines in the file
	 */
	public @NotNull ArrayList<String> readLines() {
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

		lines.remove(0);
		return lines;
	}

	/**
	 * Returns the lines
	 *
	 * @return The lines
	 */
	public ArrayList<String> getLines() {
		return lines;
	}

	/**
	 * Sorts the lines by the first value
	 */
	public void sortLines() {
		sortLines(1);
	}

	/**
	 * Sorts the lines by the specified parameter
	 *
	 * @param sortBy The value to sort by
	 */
	public void sortLines(int sortBy) {
		assert lines.get(0).split(",").length >= sortBy && sortBy > 0;
		sortMode(sortBy);

		// Construct an Interpreter from the ArrayList
		ListInterpreter<String> interpreter = new ListInterpreter<>(lines);

		// Sort the Interpreter, cast it to a ListInterpreter, get the List and cast it to an ArrayList
		lines = (ArrayList<String>) ((ListInterpreter<String>) IntroSort.sort(interpreter)).getList();
		sortMode(sortBy);
	}

	/**
	 * Moves the feature to sort by to the front of the string
	 *
	 * @param sortBy The value to sort by
	 */
	private void sortMode(int sortBy) {
		if (sortBy != 1) {
			for (int i = 0; i < lines.size(); i++) {
				// Split the line by commas
				String[] parts = lines.get(i).split(",");

				// Swap the first value and the value to sort by
				Swap.swap(parts, sortBy - 1, 0);

				// Recombine the parts in their new order
				lines.set(i, String.join(",", parts));
			}
		}
	}
}
