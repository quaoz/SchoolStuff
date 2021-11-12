package com.github.quaoz.tasks.animals;

public class StBernard extends Dog {
	public StBernard(String name) {
		super(name);
	}

	public void rescue(String name) {
		if (getCarrying().equals("")) {
			setCarrying(name);
			System.out.println(name + " runs off and grabs " + getCarrying());
			System.out.println(name + " gives some brandy to " + getCarrying());
		} else {
			System.out.println(name + " is already carrying " + getCarrying());
		}
	}
}
