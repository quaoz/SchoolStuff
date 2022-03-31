package com.github.quaoz.scripts;

import com.github.quaoz.common.database.v1.DataBase;
import com.github.quaoz.common.database.v1.ExampleRecord;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class DataBaseTester {
	public static void main(String[] args) {
		final File file = new File("src/main/java/com/github/quaoz/tmp/database.txt");

		DataBase<ExampleRecord> dataBase = new DataBase<>(file, ExampleRecord.getLength());

		dataBase.appendRecord(new ExampleRecord("Joe", 22));
		dataBase.appendRecord(new ExampleRecord("Adam", 34));
		dataBase.appendRecord(new ExampleRecord("Sahra", 21));
		dataBase.appendRecord(new ExampleRecord("May", 25));

		dataBase.writeRecord(new ExampleRecord("Write", 0), 2);
		dataBase.insertRecord(new ExampleRecord("Insert", 100), 3);

		ExampleRecord exampleRecord = new ExampleRecord(new String(dataBase.getRecord(0), StandardCharsets.UTF_8));
		System.out.println("exampleRecord.getAge() = " + exampleRecord.getAge());
		System.out.println("exampleRecord.getName() = " + exampleRecord.getName());

		System.out.println("dataBase.getRecordCount() = " + dataBase.getRecordCount());
		System.out.println("dataBase.findRecord(new ExampleRecord(\"May\", 0)) = " + dataBase.findRecord(new ExampleRecord("May", 0)));
	}
}
