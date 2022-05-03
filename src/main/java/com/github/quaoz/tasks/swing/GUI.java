package com.github.quaoz.tasks.swing;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener, DocumentListener {
	JButton button1;
	JButton button2;
	JTextField userName;

	public GUI(int width, int height) {
		System.out.println("SEQUENCE: GUI constructor");
		this.setPreferredSize(new Dimension(width, height));
		setLayout(null);

		button1 = new JButton("b1");
		button1.setBounds(0, 0, 100, 40);
		button1.addActionListener(this);

		button2 = new JButton("b2");
		button2.setBounds(120, 0, 100, 40);
		button2.addActionListener(this);

		userName = new JTextField("user");
		userName.setBounds(0, 40, 200, 40);
		userName.getDocument().addDocumentListener(this);

		add(button1);
		add(button2);
		add(userName);
	}

	@Override
	public void actionPerformed(@NotNull ActionEvent e) {
		System.out.println("SEQUENCE: GUI actionPerformed");

		if (e.getSource() == button1) {
			System.out.println("SEQUENCE: button1 pressed");
		} else if (e.getSource() == button2) {
			System.out.println("SEQUENCE: button2 pressed");
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		try {
			System.out.println(e.getDocument().getText(0, e.getDocument().getLength()));
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		try {
			System.out.println(e.getDocument().getText(0, e.getDocument().getLength()));
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		try {
			System.out.println(e.getDocument().getText(0, e.getDocument().getLength()));
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
	}
}
