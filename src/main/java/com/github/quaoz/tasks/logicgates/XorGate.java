package com.github.quaoz.tasks.logicgates;

public class XorGate extends Gate {
	public XorGate(Boolean inputOne, Boolean inputTwo) {
		super(inputOne, inputTwo);
	}

	@Override
	public Boolean output() {
		return (inputOne || inputTwo) && !(inputOne && inputTwo);
	}
}
