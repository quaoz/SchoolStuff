package com.github.quaoz.tasks.swing2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm implements ActionListener {
	private final JFrame frame;
	private final JButton loginButton;
	private final JLabel usernameLabel;
	private final JLabel passwordLabel;
	private final JLabel message;
	private final JTextField username;
	private final JTextField password;

	private static final int FRAME_WIDTH = 100;
	private static final int FRAME_HEIGHT = 200;

	public LoginForm() {
		frame = new JFrame("Login");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(0,0, 400, 300);
		frame.setLayout(new GridBagLayout());

		frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setLayout(null);

		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(100, 8, 70, 20);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(100, 55, 70, 20);

		message = new JLabel("");
		message.setBounds(100, 130, 193, 28);

		username = new JTextField();
		username.setBounds(100, 27, 193, 28);
		username.addActionListener(this);

		password = new JTextField();
		password.setBounds(100, 75, 193, 28);
		password.addActionListener(this);

		loginButton = new JButton("Login");
		loginButton.setBounds(100, 110, 90, 25);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Color.BLACK);
		loginButton.addActionListener(this);

		frame.add(usernameLabel);
		frame.add(passwordLabel);
		frame.add(message);
		frame.add(username);
		frame.add(password);
		frame.add(loginButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (username.getText().isBlank()) {
			message.setText("Please enter a username");
		} else if (password.getText().isBlank()) {
			message.setText("Please enter a password");
		} else {
			String user = username.getText();
			String pass = password.getText();

			// search database for user
			//    if not found: message.setText("User not found");
			//    if found:
			//       retrieve users hashed password from db
			//       compare input password to hash
			//       if same:
			//          set login to true, create users session
			//       else:
			//       message.setText("Incorrect password")

			if (!user.equals("user")) {
				message.setText("User not found");
			} else {
				if (!pass.equals("password")) {
					message.setText("Incorrect password");
				} else {
					message.setText("Success");
				}
			}
		}
	}

	public void show() {
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}
}
