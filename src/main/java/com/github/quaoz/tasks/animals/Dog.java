package com.github.quaoz.tasks.animals;

public class Dog extends Animal {
	private String carrying;

	public Dog(String name) {
		super(name, 4, "Woof!");
		carrying = "";
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
}
