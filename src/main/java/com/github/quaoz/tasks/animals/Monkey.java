package com.github.quaoz.tasks.animals;

public class Monkey extends Animal {
	private boolean standing;

	public Monkey(String name) {
		this(name, false);
	}

	public Monkey(String name, boolean standing) {
		super(name, 4, "OohAahAah");
		this.standing = standing;
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
