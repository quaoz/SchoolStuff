package com.github.quaoz.tasks.swing2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
	private static final int FRAME_WIDTH = 100;
	private static final int FRAME_HEIGHT = 200;
	private final JButton loginButton;
	private final JButton registerButton;
	private final LoginFormOld loginForm;
	private final RegisterForm registerForm;
	private final JFrame frame;

	public GUI() {
		frame = new JFrame("Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 400, 300);
		frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setLayout(null);

		loginButton = new JButton("Login");
		loginButton.setBounds(100, 110, 90, 25);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Color.BLACK);
		loginButton.addActionListener(this);

		registerButton = new JButton("Register");
		registerButton.setBounds(100, 80, 90, 25);
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(Color.BLACK);
		registerButton.addActionListener(this);

		loginForm = new LoginFormOld();
		registerForm = new RegisterForm();

		frame.add(loginButton);
		frame.add(registerButton);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			loginForm.show();
		} else if (e.getSource() == registerButton) {
			registerForm.show();
		}
	}
}
