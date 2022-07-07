package com.github.quaoz.tasks.swing2;

import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

public class HomeLoggedOut {
	private JTextField searchField;
	private JPanel panel;
	private JButton advancedSearchButton;
	private JButton signInButton;
	private JButton signUpButton;

	public HomeLoggedOut() {
		advancedSearchButton.addActionListener(e -> {
			JFrame frame = new JFrame("Advanced Search Record");
			frame.setContentPane(new AdvancedSearchForm().get());
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		});

		searchField.addActionListener(e -> {
			// Do search
		});

		signInButton.addActionListener(e -> {
			// Go to sign in
		});

		signUpButton.addActionListener(e -> {
			// Go to sign up
		});
	}

	{
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		panel = new JPanel();
		panel.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:grow", "center:d:noGrow"));
		panel.setMinimumSize(new Dimension(550, 300));
		panel.setPreferredSize(new Dimension(550, 300));
		searchField = new JTextField();
		searchField.setMinimumSize(new Dimension(200, 30));
		searchField.setPreferredSize(new Dimension(200, 30));
		searchField.setText("Search");
		CellConstraints cc = new CellConstraints();
		panel.add(searchField, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		advancedSearchButton = new JButton();
		advancedSearchButton.setText("Advanced Search");
		panel.add(advancedSearchButton, cc.xy(5, 1));
		signInButton = new JButton();
		signInButton.setText("Sign In");
		panel.add(signInButton, cc.xy(7, 1));
		signUpButton = new JButton();
		signUpButton.setText("Sign Up");
		panel.add(signUpButton, cc.xy(9, 1));
		final Spacer spacer1 = new Spacer();
		panel.add(spacer1, cc.xy(11, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer2 = new Spacer();
		panel.add(spacer2, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return panel;
	}

}
