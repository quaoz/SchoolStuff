package com.github.quaoz.tasks.inheritance;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class Person implements Comparable<Person> {
	private final int userID;
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
		userID = ThreadLocalRandom.current().nextInt(999999);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getUserID() {
		return userID;
	}

	public void display() {
		System.out.println(userID + " " + name + ", age " + age);
	}

	@Override
	public int compareTo(@NotNull Person o) {
		return this.name.compareTo(o.name);
	}
}
