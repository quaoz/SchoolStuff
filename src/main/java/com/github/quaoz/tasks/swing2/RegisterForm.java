package com.github.quaoz.tasks.swing2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm implements ActionListener {
	private JFrame frame;
	private final JButton registerButton;
	private final JLabel usernameLabel;
	private final JLabel passwordLabel;
	private final JLabel repeatPasswordLabel;
	private final JLabel message;
	private final JTextField username;
	private final JTextField password;
	private final JTextField repeatPassword;

	private static final int FRAME_WIDTH = 100;
	private static final int FRAME_HEIGHT = 200;

	public RegisterForm() {
		frame = new JFrame("Demo");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(0,0, 400, 300);
		frame.setLayout(new GridBagLayout());

		frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setLayout(null);

		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(100, 8, 70, 20);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(100, 55, 70, 20);

		repeatPasswordLabel = new JLabel("Repeat password");
		repeatPasswordLabel.setBounds(100, 103, 70, 20);

		message = new JLabel("");
		message.setBounds(100, 130, 193, 28);

		username = new JTextField();
		username.setBounds(100, 27, 193, 28);
		username.addActionListener(this);

		password = new JTextField();
		password.setBounds(100, 75, 193, 28);
		password.addActionListener(this);

		repeatPassword = new JTextField();
		repeatPassword.setBounds(100, 123, 193, 28);
		repeatPassword.addActionListener(this);

		registerButton = new JButton("Login");
		registerButton.setBounds(100, 110, 90, 25);
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(Color.BLACK);
		registerButton.addActionListener(this);

		frame.add(usernameLabel);
		frame.add(passwordLabel);
		frame.add(message);
		frame.add(username);
		frame.add(password);
		frame.add(repeatPassword);
		frame.add(registerButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void show() {
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}
}
