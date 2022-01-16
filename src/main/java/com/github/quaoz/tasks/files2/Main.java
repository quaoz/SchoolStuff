package com.github.quaoz.tasks.files2;

public class Main {
	public static void main(String[] args) {
		Reader reader = new Reader("src/main/java/com/github/quaoz/tasks/files2/people.csv");
		reader.readLines().forEach(System.out::println);
		System.out.println();

		// Sorts the lines by last name
		reader.sortLines(2);
		reader.getLines().forEach(System.out::println);
	}
}
