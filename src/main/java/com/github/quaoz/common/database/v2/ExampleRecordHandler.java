package com.github.quaoz.common.database.v2;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExampleRecordHandler implements RecordHandler<ExampleRecord> {
	private static final int length = 16;

	@Override
	public int recordLength() {
		return length;
	}

	@Override
	public ExampleRecord getRecord(@NotNull String source) {
		// Splits the database entry and gets the name and age
		ArrayList<String> fields = new ArrayList<>(List.of(source.split("")));
		fields.removeAll(Arrays.asList("", null));

		return new ExampleRecord(fields.get(0).strip(), Integer.parseInt(fields.get(1).strip()));
	}
}
