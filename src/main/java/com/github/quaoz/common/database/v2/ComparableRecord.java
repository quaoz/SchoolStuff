package com.github.quaoz.common.database.v2;

import org.jetbrains.annotations.NotNull;

public abstract class ComparableRecord implements Comparable<ComparableRecord> {
	/**
	 * Gets the length of the record
	 *
	 * @return The length of the record
	 */
	public abstract int length();

	/**
	 * Creates a new record from the given string, uses strings generated by {@link #toString()}
	 *
	 * @param source The string to create the record from
	 *
	 * @return The new record
	 */
	public abstract ComparableRecord fromString(String source);

	/**
	 * Gets the string representation of the record, must be a fixed length
	 *
	 * @return The string representation of the record
	 */
	@Override
	public abstract String toString();

	/**
	 * Compares this record to another
	 *
	 * @param o The other record
	 *
	 * @return The result of the comparison
	 */
	@Override
	public abstract int compareTo(@NotNull ComparableRecord o);
}
