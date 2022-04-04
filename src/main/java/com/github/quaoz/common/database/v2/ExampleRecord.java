package com.github.quaoz.common.database.v2;

import org.jetbrains.annotations.NotNull;

public record ExampleRecord(String name, int age) implements Comparable<ExampleRecord> {
	private static final String format = "%-10s %04d";

	@Override
	public String toString() {
		return String.format(format, name, age).concat("\n");
	}

	@Override
	public int compareTo(@NotNull ExampleRecord o) {
		return name.compareTo(o.name);
	}
}
