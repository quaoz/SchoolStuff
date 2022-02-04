package com.github.quaoz.common.database;

import com.github.quaoz.common.filehandling.RandomFileHandler;
import com.github.quaoz.common.filehandling.SequentialFileHandler;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class DataBase {
	private final File location;
	private final String recordFormat;
	private long recordCount;
	private final String formatDelimiter;
	private final int recordLength;

	public DataBase(@NotNull File location, String recordFormat, String formatDelimiter, int recordLength) {
		this.location = location;
		this.recordFormat = recordFormat;
		// Add one to account for newline character
		this.recordLength = recordLength + 1;
		this.recordCount = updateRecordCount();
		this.formatDelimiter = formatDelimiter;
	}

	/**
	 * Returns the number of lines in the database
	 *
	 * @return The number of lines in the database
	 *
	 * @throws RuntimeException Unable to access the file
	 */
	public long updateRecordCount() throws RuntimeException {
		try {
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
	 * Appends a record to the file
	 *
	 * @param record The record to append to the file
	 */
	public void appendRecord(String record) {
		// Formats the record
		record = String.format(recordFormat, (Object[]) record.split(formatDelimiter)).concat("\n");

		// Calculate the position in the file and increase the record count
		final long pos = recordLength * recordCount++;

		// Write the String to the file as bytes
		RandomFileHandler.writeBytes(location, pos, record.getBytes(StandardCharsets.UTF_8));
	}

	public void insertRecord(String record, long pos) {
		// Formats the record
		record = String.format(recordFormat, (Object[]) record.split(formatDelimiter)).concat("\n");

		// Calculate the position in the file and increase the record count
		pos = pos * recordLength;
		recordCount++;

		// Write the String to the file as bytes
		RandomFileHandler.writeBytes(location, pos, record.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Gets a record from the file
	 *
	 * @param record The record to return
	 *
	 * @return The specified record from the file
	 */
	public byte[] getRecord(long record) {
		return RandomFileHandler.readBytes(location, record * recordLength, recordLength);
	}

	/**
	 * Gets a record as a String from the file using UTF-8 encoding
	 *
	 * @param record The record to return
	 *
	 * @return The specified record as a String from the file
	 */
	public String getRecordString(long record) {
		return new String(RandomFileHandler.readBytes(location, record * recordLength, recordLength), StandardCharsets.UTF_8);
	}
}
