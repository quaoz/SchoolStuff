package com.github.quaoz.tasks.swing2;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm implements ActionListener {
	private final JFrame frame;

	private final JButton registerButton;

	private final JLabel usernameLabel;
	private final JLabel passwordLabel;
	private final JLabel repeatPasswordLabel;

	private final JLabel message;

	private final JTextField username;
	private final JTextField password;
	private final JTextField repeatPassword;

	private final JLabel usernameCheck;
	private final JLabel passwordCheck;
	private final JLabel repeatPasswordCheck;

	private static final int FRAME_WIDTH = 100;
	private static final int FRAME_HEIGHT = 200;

	public RegisterForm() {
		/* Frames */
		frame = new JFrame("Register");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(0,0, 400, 300);
		frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setLayout(null);

		/* Labels */

		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(100, 8, 193, 20);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(100, 55, 193, 20);

		repeatPasswordLabel = new JLabel("Repeat password");
		repeatPasswordLabel.setBounds(100, 103, 193, 20);

		usernameCheck = new JLabel("");
		usernameCheck.setBounds(300, 27, 40, 20);

		passwordCheck = new JLabel("");
		passwordCheck.setBounds(300, 75, 40, 20);

		repeatPasswordCheck = new JLabel("");
		repeatPasswordCheck.setBounds(300, 123, 40, 20);

		message = new JLabel("");
		message.setBounds(100, 180, 193, 28);

		/* Text Fields */

		username = new JTextField();
		username.setBounds(100, 27, 193, 28);
		username.addActionListener(this);

		password = new JTextField();
		password.setBounds(100, 75, 193, 28);
		password.addActionListener(this);

		repeatPassword = new JTextField();
		repeatPassword.setBounds(100, 123, 193, 28);
		repeatPassword.addActionListener(this);
		repeatPassword.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				verify(repeatPassword);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				verify(repeatPassword);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				verify(repeatPassword);
			}
		});

		/* Buttons */

		registerButton = new JButton("Register");
		registerButton.setBounds(100, 158, 90, 25);
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(Color.BLACK);
		registerButton.addActionListener(this);

		/* Add */

		frame.add(usernameLabel);
		frame.add(passwordLabel);
		frame.add(repeatPasswordLabel);
		frame.add(usernameCheck);
		frame.add(passwordCheck);
		frame.add(repeatPasswordCheck);
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

	private void verify(JTextField element) {

		if (element == repeatPassword || element == password) {
			if (repeatPassword.getText().equals(password.getText())) {
				repeatPasswordCheck.setText("\u2713");
				message.setText("");
			} else {
				repeatPasswordCheck.setText("\u2717");
				message.setText("Passwords don't match");
			}

			if (password.getText().length() >= 8) {
				passwordCheck.setText("\u2713");
				// TODO: prevent use of common passwords https://github.com/danielmiessler/SecLists/tree/master/Passwords/Common-Credentials
			} else {
				passwordCheck.setText("");
			}
		}
	}
}
