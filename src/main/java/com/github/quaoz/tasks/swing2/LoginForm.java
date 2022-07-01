package com.github.quaoz.tasks.swing2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm implements ActionListener {
	private static final int FRAME_WIDTH = 100;
	private static final int FRAME_HEIGHT = 200;
	private static final Rectangle FRAME_BOUNDS = new Rectangle(0, 0, 400, 300);
	private static final Rectangle USERNAME_LABEL_BOUNDS = new Rectangle(100, 8, 70, 20);
	private static final Rectangle PASSWORD_LABEL_BOUNDS = new Rectangle(100, 55, 70, 20);
	private static final Rectangle MESSAGE_BOUNDS = new Rectangle(100, 130, 193, 28);
	private static final Rectangle USERNAME_BOUNDS = new Rectangle(100, 27, 193, 28);
	private static final Rectangle PASSWORD_BOUNDS = new Rectangle(100, 75, 193, 28);
	private static final Rectangle LOGIN_BUTTON_BOUNDS = new Rectangle(100, 110, 90, 25);

	private final JFrame frame;
	private final JButton loginButton;
	private final JLabel usernameLabel;
	private final JLabel passwordLabel;
	private final JLabel message;
	private final JTextField username;
	private final JTextField password;

	public LoginForm() {
		frame = new JFrame("Login");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(FRAME_BOUNDS);
		frame.setLayout(new GridBagLayout());

		frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setLayout(null);

		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(USERNAME_LABEL_BOUNDS);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(PASSWORD_LABEL_BOUNDS);

		message = new JLabel("");
		message.setBounds(MESSAGE_BOUNDS);

		username = new JTextField();
		username.setBounds(USERNAME_BOUNDS);
		username.addActionListener(this);

		password = new JTextField();
		password.setBounds(PASSWORD_BOUNDS);
		password.addActionListener(this);

		loginButton = new JButton("Login");
		loginButton.setBounds(LOGIN_BUTTON_BOUNDS);
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
