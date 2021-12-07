package com.github.quaoz.tasks.logicgates;

public class AndGate extends Gate {
	public AndGate(Boolean inputOne, Boolean inputTwo) {
		super(inputOne, inputTwo);
	}

	@Override
	public Boolean output() {
		return inputOne && inputTwo;
	}
}
