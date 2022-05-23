package com.github.quaoz.tasks.swing2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
	private JFrame frame;
	private final JButton loginButton;
	private final JLabel usernameLabel;
	private final JLabel passwordLabel;
	private final JTextField username;
	private final JTextField password;

	private static final int FRAME_WIDTH = 20;

	public GUI() {
		frame = new JFrame("Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0, 400, 300);
		frame.setLayout(null);

		frame.setPreferredSize(new Dimension(width, height));
		setLayout(null);

		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(100, 8, 70, 20);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(100, 55, 70, 20);

		username = new JTextField();
		username.setBounds(100, 27, 193, 28);

		password = new JTextField();
		password.setBounds(100, 75, 193, 28);

		loginButton = new JButton("Login");
		loginButton.setBounds(100, 110, 90, 25);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Color.BLACK);
		loginButton.addActionListener(this);

		frame.add(usernameLabel);
		frame.add(passwordLabel);
		frame.add(loginButton);
		frame.add(username);
		frame.add(password);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
