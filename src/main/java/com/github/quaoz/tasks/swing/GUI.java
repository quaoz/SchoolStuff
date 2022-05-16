package com.github.quaoz.tasks.swing;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener {
	private JFrame frame;
	private final JButton loginButton;
	private final JButton graphButton;
	private final JLabel usernameLabel;
	private final JLabel passwordLabel;
	private final JTextField username;
	private final JTextField password;

	public GUI(int width, int height) {
		System.out.println("SEQUENCE: GUI constructor");

		frame = new JFrame("Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0, 400, 300);
		frame.setLayout(null);

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

		graphButton = new JButton("Graph");
		graphButton.setBounds(100, 140, 90, 25);
		graphButton.setForeground(Color.WHITE);
		graphButton.setBackground(Color.BLACK);
		graphButton.addActionListener(this);

		add(usernameLabel);
		add(passwordLabel);
		add(loginButton);
		add(username);
		add(password);

		frame.getContentPane().add(graphButton);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(@NotNull ActionEvent e) {
		if (e.getSource() == loginButton) {
			if (username.getText().equals("user") && password.getText().equals("psswd")) {
				JOptionPane.showMessageDialog(null, "Login Successful");
			} else {
				JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
			}
		} else if (e.getSource() == graphButton) {
			Basic basic = new Basic(50, 50, new int[]{10, 20, 30, 15});
		}
	}
}
