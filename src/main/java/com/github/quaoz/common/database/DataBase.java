package com.github.quaoz.common.database;

import com.github.quaoz.common.filehandling.RandomFileHandler;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DataBase {
	private final File location;
	private final String recordFormat;
	private long recordCount;

	public DataBase(@NotNull File location, String recordFormat) {
		if (location.exists()) {
			System.err.printf("File already exists at %s", location);
		}

		this.recordFormat = recordFormat;
		this.location = location;
		recordCount = getRecordCount();
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
	 * Returns the number of linse in the database
	 *
	 * @return The number of lines in the database
	 */
	public long getRecordCount() {
		return recordCount;
	}

	public void appendRecord(String record) {
		record = String.format(recordFormat, record);
		RandomFileHandler.randomWriteLine(location, recordCount++, record);
	}
}
