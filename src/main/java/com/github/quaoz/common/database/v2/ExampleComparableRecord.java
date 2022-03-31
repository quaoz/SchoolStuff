package com.github.quaoz.common.database.v2;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExampleComparableRecord extends ComparableRecord {
	private static final String format = "%-10s %04d";
	private static final int length = 16;
	private final String name;
	private final int age;

	public ExampleComparableRecord(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public int length() {
		return length;
	}

	@Override
	public ComparableRecord fromString(@NotNull String source) {
		// Splits the database entry and gets the name and age
		ArrayList<String> fields = new ArrayList<>(List.of(source.split("")));
		fields.removeAll(Arrays.asList("", null));

		return new ExampleComparableRecord(fields.get(0).strip(), Integer.parseInt(fields.get(1).strip()));
	}

	@Override
	public String toString() {
		return String.format(format, name, age).concat("\n");
	}

	@Override
	public int compareTo(@NotNull ComparableRecord o) {
		int result = 0;

		if (o != this && o instanceof ExampleComparableRecord) {
			result = this.name.compareTo(((ExampleComparableRecord) o).name);
		}

		return result;
	}
}
