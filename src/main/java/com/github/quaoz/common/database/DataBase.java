package com.github.quaoz.common.database;

import com.github.quaoz.common.filehandling.RandomFileHandler;
import it.unimi.dsi.fastutil.longs.LongObjectImmutablePair;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;

// TODO: Create flowchart
// https://app.code2flow.com/
// https://stackoverflow.com/questions/22411136/automatic-flowchart-tool

public class DataBase<T extends Record> {
	private static final int defaultCacheSize = 10;
	private final File location;
	private final int length;
	private long recordCount;
	private DataBaseCache cache;

	static class DataBaseCache {
		private final ArrayList<LongObjectImmutablePair<byte[]>> cache;
		private final int defaultCacheSize = 10;
		private final int cacheSize;
		private int cacheIndex;

		public DataBaseCache(int cacheSize) {
			this.cacheSize = cacheSize;
			cacheIndex = 0;

			cache = new ArrayList<>(cacheSize);
		}

		public DataBaseCache() {
			this.cacheSize = defaultCacheSize;
			cacheIndex = 0;

			cache = new ArrayList<>(cacheSize);
		}

		public void addRecord(byte[] record, long pos) {
			if (cache.size() < cacheSize) {
				cache.add(cacheIndex++, new LongObjectImmutablePair<>(pos, record));
			} else {
				if (cacheIndex > cacheSize) {
					cacheIndex = 0;
				}
				cache.set(cacheIndex++, new LongObjectImmutablePair<>(pos, record));
			}
		}

		public byte[] getRecord(long pos) {
			for (LongObjectImmutablePair<byte[]> longObjectImmutablePair : cache) {
				if (longObjectImmutablePair.leftLong() == pos) {
					return longObjectImmutablePair.right();
				}
			}

			return null;
		}
	}

	public DataBase(@NotNull File location, int length, int cacheSize) {
		this.recordCount = updateRecordCount();
		cache = new DataBaseCache(cacheSize);
		this.location = location;
		this.length = length;
	}

	public DataBase(@NotNull File location, int length) {
		cache = new DataBaseCache(defaultCacheSize);
		this.recordCount = updateRecordCount();
		this.location = location;
		this.length = length;
	}

	/**
	 * Returns the number of lines in the database
	 *
	 * @return The number of lines in the database
	 *
	 * @throws RuntimeException Unable to access the file
	 */
	private long updateRecordCount() throws RuntimeException {
		try {
			// Recounts the lines
			recordCount = Files.lines(location.toPath()).count();
			return getRecordCount();
		} catch (IOException e) {
			System.err.printf("Unable to access the file at %s", location);
			e.printStackTrace();
		}

		throw new RuntimeException("Failed to count the lines in the database file");
	}

	/**
	 * Returns the number of lines in the database
	 *
	 * @return The number of lines in the database
	 */
	public long getRecordCount() {
		return recordCount;
	}

	/**
	 * Returns a record as a formatted array of bytes
	 *
	 * @param record The record to format
	 *
	 * @return The formatted record as an array of bytes
	 */
	private byte @NotNull [] getRecordBytes(@NotNull T record) {
		// Return the formatted record as an array of bytes
		return record.toString().getBytes(StandardCharsets.UTF_8);
	}

	/**
	 * Appends a record to the file
	 *
	 * @param record The record to append to the file
	 */
	public void appendRecord(@NotNull T record) {
		// Calculate the position in the file and increase the record count
		final long pos = length * recordCount++;

		// Write the String to the file as bytes
		RandomFileHandler.writeBytes(location, pos, getRecordBytes(record));
	}

	/**
	 * Overwrites a record
	 *
	 * @param pos    The line to overwrite
	 * @param record The record to write
	 */
	public void writeRecord(@NotNull T record, long pos) {
		// Calculate the position in the file
		pos *= length;

		// Write the String to the file as bytes
		RandomFileHandler.writeBytes(location, pos, getRecordBytes(record));
	}

	/**
	 * Inserts a record at a given position
	 *
	 * @param pos    The position to insert at
	 * @param record The record to insert
	 */
	public void insertRecord(T record, long pos) {
		RandomFileHandler.insertLine(location, getRecordBytes(record), pos * length, length);
		recordCount++;
	}

	/**
	 * Deletes a record
	 *
	 * @param pos The record to delete
	 */
	public void deleteRecord(long pos) {
		RandomFileHandler.deleteLine(location, pos * length, length);
	}

	/**
	 * Gets a record from the file
	 *
	 * @param record The record to return
	 *
	 * @return The specified record from the file
	 */
	public byte[] getRecord(long record) {
		return RandomFileHandler.readBytes(location, record * length, length);
	}

	/**
	 * Performs a linear search for the record
	 *
	 * @param record The record to search for
	 *
	 * @return The line the record was found at
	 */
	public long findRecord(T record) {
		try {
			Iterator<byte[]> iterator = RandomFileHandler.iterator(location, length);
			long line = 0;

			while (iterator.hasNext()) {
				if (record.equals(record.fromString(new String(iterator.next(), StandardCharsets.UTF_8)))) {
					break;
				} else {
					line++;
				}
			}

			return line;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		throw new RuntimeException("Unable to access the file");
	}
}
