package com.github.quaoz.tasks.files;

public class Main {
	public static void main(String[] args) {
		final String[] names = ReadNames.getNames();

		// Prints the names
		for (String name : names) {
			System.out.println(name);
		}

		if (ReadNames.findName("bob")) {
			System.out.println("Name found!");
		} else {
			System.out.println("Name not found.");
		}

		if (ReadNames.findName("alice")) {
			System.out.println("Name found!");
		} else {
			System.out.println("Name not found.");
		}

		System.out.println("There are " + WordCounter.countWords() + " words");
	}
}
