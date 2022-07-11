package com.github.quaoz.tasks.swing2;

import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

public class AdvancedSearchForm {
	private JTextField nameField;
	private JTextField locationField;
	private JTextField habitatField;
	private JTextField foodField;
	private JTextField sizeStartField;
	private JTextField sizeEndField;
	private JButton searchButton;
	private JLabel nameLabel;
	private JLabel locationLabel;
	private JLabel sizeLabel;
	private JLabel flightTimeLabel;
	private JLabel habitatLabel;
	private JLabel foodLabel;
	private JTextField flightStartField;
	private JTextField flightEndField;
	private JLabel titleLabel;
	private JButton cancelButton;
	private JPanel panel;

	{
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	public AdvancedSearchForm() {
		cancelButton.addActionListener(e -> {
			// Go back
		});

		searchButton.addActionListener(e -> {
			// Do search
		});
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("AdvancedSearchForm");
		frame.setContentPane(new AdvancedSearchForm().panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public JPanel get() {
		return panel;
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
		panel.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,center:max(d;4px):noGrow"));
		panel.setMinimumSize(new Dimension(360, 250));
		panel.setPreferredSize(new Dimension(360, 250));
		final Spacer spacer1 = new Spacer();
		CellConstraints cc = new CellConstraints();
		panel.add(spacer1, cc.xyw(5, 1, 5, CellConstraints.DEFAULT, CellConstraints.FILL));
		final Spacer spacer2 = new Spacer();
		panel.add(spacer2, cc.xyw(5, 18, 5, CellConstraints.DEFAULT, CellConstraints.FILL));
		final Spacer spacer3 = new Spacer();
		panel.add(spacer3, cc.xy(1, 18, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		final Spacer spacer4 = new Spacer();
		panel.add(spacer4, cc.xy(11, 2, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		nameField = new JTextField();
		panel.add(nameField, cc.xyw(5, 5, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
		locationField = new JTextField();
		panel.add(locationField, cc.xyw(5, 7, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
		habitatField = new JTextField();
		panel.add(habitatField, cc.xyw(5, 13, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
		foodField = new JTextField();
		panel.add(foodField, cc.xyw(5, 15, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
		sizeStartField = new JTextField();
		sizeStartField.setMinimumSize(new Dimension(49, 30));
		panel.add(sizeStartField, cc.xy(5, 9, CellConstraints.FILL, CellConstraints.DEFAULT));
		sizeEndField = new JTextField();
		panel.add(sizeEndField, cc.xy(9, 9, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JLabel label1 = new JLabel();
		label1.setText("to");
		panel.add(label1, cc.xy(7, 9, CellConstraints.CENTER, CellConstraints.DEFAULT));
		flightStartField = new JTextField();
		panel.add(flightStartField, cc.xy(5, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
		flightEndField = new JTextField();
		panel.add(flightEndField, cc.xy(9, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JLabel label2 = new JLabel();
		label2.setText("to");
		panel.add(label2, cc.xy(7, 11, CellConstraints.CENTER, CellConstraints.DEFAULT));
		nameLabel = new JLabel();
		nameLabel.setText("Name");
		panel.add(nameLabel, cc.xy(3, 5));
		locationLabel = new JLabel();
		locationLabel.setText("Location");
		panel.add(locationLabel, cc.xy(3, 7));
		sizeLabel = new JLabel();
		sizeLabel.setText("Size");
		panel.add(sizeLabel, cc.xy(3, 9));
		flightTimeLabel = new JLabel();
		flightTimeLabel.setText("Flys from");
		panel.add(flightTimeLabel, cc.xy(3, 11));
		habitatLabel = new JLabel();
		habitatLabel.setText("Habitat");
		panel.add(habitatLabel, cc.xy(3, 13));
		foodLabel = new JLabel();
		foodLabel.setText("Food Sources");
		panel.add(foodLabel, cc.xy(3, 15));
		searchButton = new JButton();
		searchButton.setText("Search");
		panel.add(searchButton, cc.xy(9, 17));
		cancelButton = new JButton();
		cancelButton.setLabel("Cancel");
		cancelButton.setText("Cancel");
		panel.add(cancelButton, cc.xy(5, 17));
		titleLabel = new JLabel();
		titleLabel.setText("Advanced Search");
		panel.add(titleLabel, cc.xyw(5, 3, 5));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return panel;
	}

}
