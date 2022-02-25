package com.github.quaoz.tasks.polymorphism;

public class Pig extends FarmAnimal {

	public Pig(String name) {
		super(name);
	}

	public Pig() {
		super();
	}

	@Override
	public String noise() {
		return "oink";
	}
}
