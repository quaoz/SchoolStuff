package com.github.quaoz.tasks.randomfiles;

import com.github.quaoz.common.filehandling.RandomFileHandler;
import com.github.quaoz.common.filehandling.SequentialFileHandler;

import java.io.File;
import java.util.ArrayList;

public class Extension {
	public static void main(String[] args) {
		File file = new File("src/main/java/com/github/quaoz/tasks/randomfiles/user_data.txt");

		//standardise(file, "%-20s %-15s %-15s");
		System.out.println(get(file, 5));
	}

	public static void standardise(File file, String format) {
		ArrayList<String> lines = SequentialFileHandler.read(file, 1, -1);

		for (int i = 0; i < lines.size(); i++) {
			lines.set(i, String.format(format, (Object[]) lines.get(i).trim().split(",")));
		}

		SequentialFileHandler.writeAll(file, lines);
	}

	public static String get(File file, long record) {
		return RandomFileHandler.readLine(file, record * 52);
	}
}
