package com.github.quaoz.common.database;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ExampleRecord extends Record {
	private static final String format = "%-10s %04d";
	private static final String splitter = " ";
	private static final int length = 16;
	private String name;
	private int age;

	public ExampleRecord(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public ExampleRecord(@NotNull String record) {
		ArrayList<String> fields = new ArrayList<>(List.of(record.split(splitter)));
		// Remove all empty elements from the list
		fields.removeAll(Arrays.asList("", null));

		name = fields.get(0).strip();
		age = Integer.parseInt(fields.get(1).strip());
	}

	public static int getLength() {
		return length;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format(format, name, age).concat("\n");
	}

	@Override
	public Record fromString(String source) {
		return new ExampleRecord(source);
	}

	@Override
	public boolean equals(@NotNull Record record) {
		return record.getClass() == this.getClass() && Objects.equals(this.name, ((ExampleRecord) record).name);
	}
}
