package com.github.quaoz.tasks.animals;

public class AnyAnimal extends Animal {
	// dog methods
	private String carrying;
	// monkey methods
	private boolean standing;

	public AnyAnimal(String name, int legs, String sound) {
		super(name, legs, sound);
	}

	// cow methods
	public double milk() {
		double yield = Math.random() * 20 + 20;
		System.out.println(name + " gives " + yield + " litres of milk");
		return yield;
	}

	public String getCarrying() {
		return carrying;
	}

	public void setCarrying(String carrying) {
		this.carrying = carrying;
	}

	public void fetch(String thing) {
		if (carrying.equals("")) {
			System.out.println(name + " runs off and grabs " + thing);
			carrying = thing;
		} else {
			System.out.println(name + " is already carrying " + carrying);
		}
	}

	public String drop() {
		if (!carrying.equals("")) {
			System.out.println(name + " gives you " + carrying);
			String temp = carrying;
			carrying = "";
			return temp;
		} else {
			System.out.println(name + " isn't carrying anything!");
			return "";
		}
	}

	// stBernard methods
	public void rescue(String name) {
		if (getCarrying().equals("")) {
			setCarrying(name);
			System.out.println(name + " runs off and grabs " + getCarrying());
			System.out.println(name + " gives some brandy to " + getCarrying());
		} else {
			System.out.println(name + " is already carrying " + getCarrying());
		}
	}

	public boolean isStanding() {
		return standing;
	}

	public void setStanding(boolean standing) {
		this.standing = standing;
	}

	@Override
	public int getLegs() {
		return standing ? 2 : 4;
	}
}
