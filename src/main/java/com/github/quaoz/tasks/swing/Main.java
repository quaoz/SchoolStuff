package com.github.quaoz.tasks.swing;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static void main(String[] args) {
		// Demo GUI
		System.out.println("START");

		JFrame frame = new JFrame("User Login");
		frame.setLocation(new Point(500, 300));
		frame.setSize(new Dimension(400, 200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GUI gui = new GUI(600, 400);
		frame.add(gui);
		frame.setVisible(true);
	}
}
