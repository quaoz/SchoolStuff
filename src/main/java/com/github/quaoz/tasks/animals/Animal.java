package com.github.quaoz.tasks.animals;

import org.jetbrains.annotations.NotNull;

public class Animal implements Comparable<Animal> {
	private final int legs;
	private final String sound;
	protected String name;

	public Animal(String name, int legs, String sound) {
		this.name = name;
		this.legs = legs;
		this.sound = sound;
	}

	public void say(String words) {
		System.out.println(name + " says " + sound);
	}

	public int getLegs() {
		return legs;
	}

	@Override
	public int compareTo(@NotNull Animal o) {
		return Integer.compare(this.getLegs(), o.getLegs());
	}
}
