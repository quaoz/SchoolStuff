package com.github.quaoz.tasks.filewriting;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
	public static void writeToFile(String fileName, String text) {
		try (
				FileWriter fileWriter = new FileWriter(fileName, true);
				PrintWriter printWriter = new PrintWriter(fileWriter)
		) {
			printWriter.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
