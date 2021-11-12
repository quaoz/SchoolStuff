package com.github.quaoz.tasks.animals;

import com.github.quaoz.common.sorts.BubbleSort;

class Main {
	public static void main(String[] args) {
		System.out.println("Animal crackers");
		Cow daisy = new Cow("Daisy");
		daisy.milk();
		daisy.say("hello");

		Dog spot = new Dog("Spot");
		spot.say("hello");
		spot.fetch("stick");
		spot.fetch("banana");
		spot.drop();
		spot.drop();

		System.out.println("\n");

		Animal[] animals = new Animal[8];
		animals[0] = new Cow("Alice");
		animals[1] = new StBernard("Bob");
		animals[2] = new Cow("Clara");
		animals[3] = new Monkey("Dunston");
		animals[4] = new Trout("Ellie");
		animals[5] = new StickInsect("Frank");
		animals[6] = new Cow("Georgia");
		animals[7] = new Owl("Harry");

		// lists all the animals
		for (Animal animal : animals) {
			System.out.println(animal.name + " is a " + animal.getClass().getSimpleName() + ", it has " + animal.getLegs() + " legs");
		}

		BubbleSort.sort(animals);
		System.out.println("\nSorted:\n");

		for (Animal animal : animals) {
			System.out.println(animal.name + " is a " + animal.getClass().getSimpleName() + ", it has " + animal.getLegs() + " legs");
		}
	}
}
