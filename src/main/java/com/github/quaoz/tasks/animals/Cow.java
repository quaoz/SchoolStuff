package com.github.quaoz.tasks.animals;

public class Cow extends Animal {
	public Cow(String name) {
		super(name, 4, "moo!");
	}

	public double milk() {
		double yield = Math.random() * 20 + 20;
		System.out.println(name + " gives " + yield + " litres of milk");
		return yield;
	}
}
