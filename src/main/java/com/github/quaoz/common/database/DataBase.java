package com.github.quaoz.common.database;

import com.github.quaoz.common.filehandling.RandomFileHandler;
import com.github.quaoz.common.filehandling.SequentialFileHandler;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class DataBase {
	private final File location;
	private final String recordFormat;
	private long recordCount;
	private String delimiter;
	private final int recordLength;

	public DataBase(@NotNull File location, String recordFormat, String formatDelimiter, int recordLength) {
		this.recordFormat = recordFormat;
		this.location = location;
		recordCount = updateRecordCount();
		delimiter = formatDelimiter;
		this.recordLength = recordLength;
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

	public void appendRecord(String record) {
		record = String.format(recordFormat, (Object[]) record.split(delimiter));
		SequentialFileHandler.write(location, record, true);
		recordCount++;
	}

	public byte[] getRecord(long record) {
		return Objects.requireNonNull(RandomFileHandler.randomReadLine(location, recordLength * record)).getBytes();
	}
}
