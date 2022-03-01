package com.github.quaoz.tasks.polymorphism;

public class Main {
	public static void main(String[] args) {
		Cow animal1 = new Cow("Daisy");
		Pig animal2 = new Pig();

		System.out.println(animal1.display() + " says " + animal1.noise());
		System.out.println(animal2.display() + " says " + animal2.noise());

		FarmAnimal animal3 = new Cow("Bess");
		System.out.println(animal3.display() + " says " + animal3.noise());

		FarmAnimal animal4 = new Sheep();
		System.out.println(animal4.display() + " says " + animal4.noise());
	}
}
