package com.github.quaoz.tasks.polymorphism;


public class FarmAnimal {
	private final String name;
	private final boolean named;
	private final long serialNumber;

	/**
	 * Initialises a FarmAnimal with a name and a flag serialNumber
	 *
	 * @param name The name to give the FarmAnimal
	 */
	public FarmAnimal(String name) {
		serialNumber = -1L;
		this.name = name;
		named = true;
	}

	/**
	 * Initialises a FarmAnimal with a serialnumber and a flag name
	 */
	public FarmAnimal() {
		serialNumber = System.currentTimeMillis();

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		named = false;
		name = null;
	}

	/**
	 * Returns the name or the serialNumber as approptiate
	 *
	 * @return Returns the name or the serialNumber
	 */
	public String display() {
		return named ? name : String.valueOf(serialNumber);
	}

	/**
	 * Returns the default sound
	 *
	 * @return The FarmAnimals sound
	 */
	public String noise() {
		return "a vague snuffling noise";
	}
}
