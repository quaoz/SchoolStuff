package com.github.quaoz.tasks.inheritance;

public class SalesPerson extends Employee {
	private int commission;

	public SalesPerson(String name, int age, double rate) {
		super(name, age, rate);
		this.commission = 0;
	}

	public void addCommission(int c) {
		this.commission = this.commission + c;
	}

	public void resetCommission() {
		this.commission = 0;
	}

	public double weeklySalary(int weekNumber) {
		return super.weeklySalary(weekNumber) + commission;
	}
}
