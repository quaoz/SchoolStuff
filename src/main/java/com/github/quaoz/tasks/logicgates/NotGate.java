package com.github.quaoz.tasks.logicgates;

public class NotGate extends Gate {
	public NotGate(boolean inputOne) {
		super(inputOne, null);
	}

	@Override
	public Boolean output() {
		return !inputOne;
	}
}
