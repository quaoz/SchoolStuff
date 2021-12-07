package com.github.quaoz.tasks.logicgates;

public class Main {
	public static void main(String[] args) {
		AndGate andGate = new AndGate(true, true);
		System.out.println(andGate.output());
		andGate.truthTable();
	}
}
