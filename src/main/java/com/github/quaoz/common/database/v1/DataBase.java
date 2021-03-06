package com.github.quaoz.common.database.v1;

import com.github.quaoz.common.filehandling.RandomFileHandler;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Iterator;

public class DataBase<T extends Record> {
	private final File location;
	private final int length;
	private long recordCount;

	public DataBase(@NotNull File location, int length, int cacheSize) {
		this.recordCount = updateRecordCount();
		this.location = location;
		this.length = length;
	}


	public DataBase(@NotNull File location, int length) {
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
		RandomFileHandler.insertBytes(location, getRecordBytes(record), pos * length, length);

		//TODO cache

		recordCount++;
	}

	/**
	 * Deletes a record
	 *
	 * @param pos The record to delete
	 */
	public void deleteRecord(long pos) {
		//TODO cache
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
		/* byte[] cacheRecord = cache.getRecord(record);

		if (cacheRecord == null) {
			byte[] fileRecord = RandomFileHandler.readBytes(location, record * length, length);
			cache.addRecord(record, fileRecord);

			return fileRecord;
		} else {
			return cacheRecord;
		}*/
		return null;
	}

	/**
	 * Performs a linear search for the record
	 *
	 * @param record The record to search for
	 *
	 * @return The line the record was found at
	 */
	public long findRecord(T record) {

		//TODO cache

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

	static class DataBaseCache {

	}
}
