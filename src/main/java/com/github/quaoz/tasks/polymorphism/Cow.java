package com.github.quaoz.tasks.polymorphism;

public class Cow extends FarmAnimal {

	public Cow(String name) {
		super(name);
	}

	public Cow() {
		super();
	}

	@Override
	public String noise() {
		return "moo";
	}
}
