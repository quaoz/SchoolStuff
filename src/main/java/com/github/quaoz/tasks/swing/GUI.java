package com.github.quaoz.tasks.swing;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener {
	JButton loginButton;
	JLabel usernameLabel;
	JLabel passwordLabel;
	JTextField username;
	JTextField password;

	public GUI(int width, int height) {
		System.out.println("SEQUENCE: GUI constructor");
		this.setPreferredSize(new Dimension(width, height));
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

		add(usernameLabel);
		add(passwordLabel);
		add(loginButton);
		add(username);
		add(password);
	}

	@Override
	public void actionPerformed(@NotNull ActionEvent e) {
		if (e.getSource() == loginButton) {
			if (username.getText().equals("user") && password.getText().equals("psswd")) {
				JOptionPane.showMessageDialog(null, "Login Successful");
			} else {
				JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
			}
		}
	}
}
