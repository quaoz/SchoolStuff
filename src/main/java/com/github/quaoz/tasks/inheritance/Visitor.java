package com.github.quaoz.tasks.inheritance;

public class Visitor extends Person {
	public Visitor(String name, int age) {
		super(name, age);
	}

	@Override
	public Integer getUserID() {
		return Integer.parseInt("99" + super.getUserID().toString());
	}
}
