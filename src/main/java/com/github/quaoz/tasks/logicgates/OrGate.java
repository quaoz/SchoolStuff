package com.github.quaoz.tasks.logicgates;

public class OrGate extends Gate {
	public OrGate(Boolean inputOne, Boolean inputTwo) {
		super(inputOne, inputTwo);
	}

	@Override
	public Boolean output() {
		return inputOne || inputTwo;
	}
}
