package com.github.quaoz.tasks.logicgates;

public class NandGate extends Gate {
	public NandGate(Boolean inputOne, Boolean inputTwo) {
		super(inputOne, inputTwo);
	}

	@Override
	public Boolean output() {
		return !(inputOne && inputTwo);
	}
}
