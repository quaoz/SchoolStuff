package com.github.quaoz.tasks.logicgates;

public class Main {
	public static void main(String[] args) {
		AndGate andGate = new AndGate(true, true);
		andGate.truthTable();
		System.out.println();

		OrGate orGate = new OrGate(andGate.output(), false);
		System.out.println(andGate.output());
		System.out.println(orGate.output());

		NotGate notGate = new NotGate(orGate.output());
		System.out.println(notGate.output());
		orGate.truthTable();

		XorGate xorGate = new XorGate(true, true);
		xorGate.truthTable();

		NandGate nandGate = new NandGate(true, true);
		nandGate.truthTable();

		NorGate norGate = new NorGate(true, true);
		norGate.truthTable();
	}
}
