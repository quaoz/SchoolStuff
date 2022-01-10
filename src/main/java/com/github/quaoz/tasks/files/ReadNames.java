package com.github.quaoz.tasks.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadNames {

	/**
	 * Reads a list of names from a file
	 *
	 * @return An array of names
	 */
	public static String[] getNames() {
		ArrayList<String> names = new ArrayList<>();

		try {
			// Create a FileReader and a BufferedReader
			final FileReader fr = new FileReader("C:\\Users\\16alwan_m\\IdeaProjects\\SchoolStuff\\src\\main\\java\\com\\github\\quaoz\\tasks\\files\\names.txt");
			final BufferedReader br = new BufferedReader(fr);

			// Read each line into an array list
			String line = br.readLine();
			while (line != null) {
				names.add(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Return the array list as a string array
		return names.toArray(String[]::new);
	}

	public static boolean findName(String name) {
		boolean result = false;

		try {
			// Create a FileReader and a BufferedReader
			final FileReader fr = new FileReader("C:\\Users\\16alwan_m\\IdeaProjects\\SchoolStuff\\src\\main\\java\\com\\github\\quaoz\\tasks\\files\\names.txt");
			final BufferedReader br = new BufferedReader(fr);

			// Read each line
			String line = br.readLine();
			while (line != null) {
				// Check for the name
				if (line.equals(name)) {
					result = true;
					break;
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
