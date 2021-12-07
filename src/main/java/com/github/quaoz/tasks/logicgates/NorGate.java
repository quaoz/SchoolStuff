package com.github.quaoz.tasks.logicgates;

public class NorGate extends Gate {
	public NorGate(Boolean inputOne, Boolean inputTwo) {
		super(inputOne, inputTwo);
	}

	@Override
	public Boolean output() {
		return !(inputOne || inputTwo);
	}
}
