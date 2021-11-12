package com.github.quaoz.tasks.inheritance;

import java.time.LocalDate;

public class Employee extends Person {
	private final double hourlyRate;
	private final double hours;

	public Employee(String name, int age, double rate) {
		super(name, age);
		this.hourlyRate = rate;
		this.hours = 37.5;
	}

	public double weeklySalary(int weekNumber) {
		return weekNumber == 50
				? hourlyRate * hours * 1.2
				: hourlyRate * hours;
	}

	public double takeHomePay(int weekNumber) {
		return weeklySalary(weekNumber) * 0.75;
	}

	public int retiringInYear() {
		final int currentYear = LocalDate.now().getYear();
		return currentYear + 68 - getAge();
	}
}
