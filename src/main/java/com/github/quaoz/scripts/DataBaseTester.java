package com.github.quaoz.scripts;

import com.github.quaoz.common.database.DataBase;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class DataBaseTester {
	public static void main(String[] args) {
		final File file = new File("src/main/java/com/github/quaoz/tmp/database.txt");
		final String fmt = "%-10s %-10s";

		DataBase dataBase = new DataBase(file, fmt, ",", 21);

		System.out.println("dataBase.getRecordCount() = " + dataBase.getRecordCount());

		dataBase.appendRecord("record,field");
		dataBase.appendRecord("one,two");
		dataBase.appendRecord("three,four");

		System.out.println("dataBase.getRecordCount() = " + dataBase.getRecordCount());
		System.out.print("dataBase.getRecord(1) = " + new String(dataBase.getRecord(1), StandardCharsets.UTF_8));
		System.out.print("dataBase.getRecordString(1) = " + dataBase.getRecordString(1));

		System.out.println("dataBase.updateRecordCount() = " + dataBase.updateRecordCount());

		dataBase.insertRecord("insert,record", 2);
	}
}
