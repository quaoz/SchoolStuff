package com.github.quaoz.common.database.v2;

import org.jetbrains.annotations.NotNull;

public class ExampleRecord implements Comparable<ExampleRecord> {
	private static final String format = "%-10s %04d";
	private final String name;
	private final int age;

	public ExampleRecord(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return String.format(format, name, age).concat("\n");
	}

	@Override
	public int compareTo(@NotNull ExampleRecord o) {
		return name.compareTo(o.name);
	}
}
