package com.github.quaoz.tasks.swing;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		// Demo GUI
		System.out.println("SEQUENCE: Program started");

		JFrame frame = new JFrame("Demo frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GUI gui = new GUI(600, 400);
		frame.add(gui);
		frame.setVisible(true);
	}
}
