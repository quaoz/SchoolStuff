package com.github.quaoz.tasks.polymorphism;

public class Sheep extends FarmAnimal {

	public Sheep(String name) {
		super(name);
	}

	public Sheep() {
		super();
	}

	@Override
	public String noise() {
		return "baa";
	}
}
