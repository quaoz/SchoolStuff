package com.github.quaoz.common.database;

import org.jetbrains.annotations.NotNull;

public abstract class Record {
	public abstract String toString();

	public abstract Record fromString(String source);

	public abstract boolean equals(@NotNull Record record);
}
