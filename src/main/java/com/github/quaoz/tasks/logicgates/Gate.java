package com.github.quaoz.tasks.logicgates;

public abstract class Gate {
	protected Boolean inputOne;
	protected Boolean inputTwo;

	public Gate(Boolean inputOne, Boolean inputTwo) {
		this.inputOne = inputOne;
		this.inputTwo = inputTwo;
	}

	public void setInputOne(Boolean inputOne) {
		this.inputOne = inputOne;
	}

	public void setInputTwo(Boolean inputTwo) {
		this.inputTwo = inputTwo;
	}

	public abstract Boolean output();

	public void truthTable() {
		Boolean[] possible = {false, true};

		for (Boolean a : possible) {
			setInputOne(a);
			for (Boolean b : possible) {
				setInputTwo(b);
				System.out.printf("%3d %3d %3d %n" , a ? 1 : 0, b ? 1 : 0, output() ? 1 : 0);
			}
		}
	}
}
