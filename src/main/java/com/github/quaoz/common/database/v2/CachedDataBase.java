package com.github.quaoz.common.database.v2;

import com.github.quaoz.common.datastructures.BinarySearchTree;
import com.github.quaoz.common.filehandling.RandomFileHandler;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Queue;

public class CachedDataBase<T extends ComparableRecord> {
	private final File location;
	private long recordCount;
	// private Cache<T> cache;



	/**
	 * Creates a new database or loads an existing one
	 *
	 * @param location The location of the database
	 *
	 * @throws IllegalArgumentException If the location is {@code null}
	 * @throws IllegalArgumentException If the location is a directory
	 * @throws IllegalArgumentException If the location is not readable
	 * @throws IllegalArgumentException If the location is not writable
	 * @throws IllegalArgumentException If the database cannot be created
	 */
	public CachedDataBase(File location) throws IllegalArgumentException {
		if (location == null) {
			throw new IllegalArgumentException("location cannot be null");
		} else if (location.isDirectory()) {
			throw new IllegalArgumentException("location is a directory");
		} else if (!location.canRead()) {
			throw new IllegalArgumentException("location cannot be read from");
		} else if (!location.canWrite()) {
			throw new IllegalArgumentException("location cannot be written to");
		} else if (!location.exists()) {
			// try to create a new file at location and throw an exception if it fails
			try {
				location.createNewFile();
			} catch (IOException e) {
				throw new IllegalArgumentException("location cannot be created");
			}

			this.location = location;
			updateRecordCount();
		} else {
			this.location = location;
			recordCount = 0;
		}
	}

	/**
	 * Gets the number of records in the database
	 *
	 * @return The number of records in the database
	 */
	public long getRecordCount() {
		return recordCount;
	}

	/**
	 * Updates the record count of the database.
	 */
	private void updateRecordCount() {
		try {
			recordCount = Files.lines(location.toPath()).count();
		} catch (IOException e) {
			System.err.printf("Unable to access the file at %s", location);
			e.printStackTrace();
		}
	}

	/**
	 * Adds a record to the database using RandomAccessFile to write to the file
	 */
	public void add(T record) {
		if (recordCount == 0) {
			RandomFileHandler.writeBytes(location, 0, record.toString().getBytes());
		} else {
			add(record, 0, recordCount);
		}


	}

	private static class Cache<T> {
		BinarySearchTree<T> tree;
		ArrayList<T> list;

		public Cache() {
			// TODO: fix this
			tree = new BinarySearchTree<T>();
			list = new ArrayList<>();
		}

		public void add(T record) {
			list.add(record);

			if (list.size() > 100) {
				tree.remove(list.get(0));
				list.remove(0);
			}

			tree.add(record);
		}

		public T get(T record) {
			return tree.get(record);
		}

		public void remove(T record) {
			tree.remove(record);
		}
	}

	/**
	 * Adds a record to the database using RandomAccessFile to write to the file
	 */
	@SuppressWarnings("unchecked")
	private void add(@NotNull T record, long start, long end) {
		long mid = (start + end) / 2;
		T midRecord = (T) record.fromString(get(mid, record.length()));
		int comparison = midRecord.compareTo(record);

		if (comparison > 0) {
			add(record, start, mid);
		} else if (comparison < 0) {
			add(record, mid, end);
		} else {
			RandomFileHandler.insertBytes(location, record.toString().getBytes(), mid * record.length(), record.length());
		}
	}

	/**
	 * Gets a record from the database
	 *
	 * @param index  The index of the record to get
	 * @param length The length of the record to get
	 *
	 * @return The string representation of the record at the given index
	 */
	public String get(long index, int length) {
		return new String(RandomFileHandler.readBytes(location, index * length, length));
	}

	/**
	 * Gets a record from the database
	 *
	 * @param record The record to get
	 *
	 * @return The record from the database
	 */
	public T get(T record) {
		return get(record, 0, recordCount);
	}

	/**
	 * Gets a record from the database
	 *
	 * @param record The record to get
	 * @param start  The start index of the search
	 * @param end    The end index of the search
	 *
	 * @return The record from the database
	 */
	@SuppressWarnings("unchecked")
	private T get(@NotNull T record, long start, long end) {
		long mid = (start + end) / 2;
		T midRecord = (T) record.fromString(get(mid, record.length()));
		int comparison = midRecord.compareTo(record);

		if (comparison > 0) {
			return get(record, start, mid);
		} else if (comparison < 0) {
			return get(record, mid, end);
		} else {
			return midRecord;
		}
	}

	/**
	 * Removes a record from the database
	 *
	 * @param record The record to remove
	 */
	public void remove(T record) {
		remove(record, 0, recordCount);
	}

	/**
	 * Removes a record from the database
	 *
	 * @param record The record to remove
	 * @param start  The start index of the search
	 * @param end    The end index of the search
	 */
	@SuppressWarnings("unchecked")
	private void remove(@NotNull T record, long start, long end) {
		long mid = (start + end) / 2;
		T midRecord = (T) record.fromString(get(mid, record.length()));
		int comparison = midRecord.compareTo(record);

		if (comparison > 0) {
			remove(record, start, mid);
		} else if (comparison < 0) {
			remove(record, mid, end);
		} else {
			RandomFileHandler.deleteLine(location, mid * record.length(), record.length());
		}
	}
}
