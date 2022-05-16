package com.github.quaoz.tasks.swing;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Basic extends JPanel {
	private JFrame frame;
	private int[] values;

	public Basic(int width, int height, int[] values) {
		frame = new JFrame("Basic Graph");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(width, height);
		frame.getContentPane().add(this);
		frame.setVisible(true);

		this.values = values;
	}

	@Override
	public void paintComponent(Graphics g) {
		int left = 20;
		int top = Arrays.stream(values).max().getAsInt() + 10;
		int width = 5;

		for (int value : values) {
			g.fillRect(left, top - value, width, value);
			left += width + 2;
		}
	}
}
