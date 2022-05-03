package com.github.quaoz.tasks.swing;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {
	JButton button1;
	JButton button2;

	public GUI(int width, int height) {
		System.out.println("SEQUENCE: GUI constructor");
		this.setPreferredSize(new Dimension(width, height));
		setLayout(null);

		button1 = new JButton("b1");
		button1.setBounds(0, 0, 100, 40);

		button2 = new JButton("b2");
		button2.setBounds(120, 0, 100, 40);

		add(button1);
		add(button2);
	}
}
