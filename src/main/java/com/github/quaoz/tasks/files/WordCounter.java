package com.github.quaoz.tasks.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCounter {
	public static int countWords() {
		int words = 0;

		try {
			// Create a FileReader and a BufferedReader
			final FileReader fr = new FileReader("C:\\Users\\16alwan_m\\IdeaProjects\\SchoolStuff\\src\\main\\java\\com\\github\\quaoz\\tasks\\files\\words.txt");
			final BufferedReader br = new BufferedReader(fr);

			// Read each line
			String line = br.readLine();
			while (line != null) {
				if (!line.isBlank()) {
					line = line.trim();
					words += line.split("\\s+").length;
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return words;
	}
}
