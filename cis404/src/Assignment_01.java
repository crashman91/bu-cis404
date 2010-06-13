import javax.swing.*;

import java.awt.*;

/**
 * 
 * @author Andrei Tolnai
 * @date 13 June 2010
 * @assignment 1.3
 * @description Create a Swing application that looks like the example located
 *              at the top of this assignment. The following source code is
 *              provided to get you started. For this week's assignment you do
 *              not need to be concerned with adding action listeners. We will
 *              work on giving our application functionality in Week 2.
 * @company Bellevue University
 * @fileName Assignment_01.java
 * 
 */
class Assignment_01 extends JFrame {

	private static final long serialVersionUID = 1L;

	JLabel labelHeader = new JLabel("Database Browser", JLabel.CENTER);

	// The name label and text field
	JLabel labelName = new JLabel("Name");
	JTextField textFieldName = new JTextField();

	// The address label and text field
	JLabel labelAddress = new JLabel("Address");
	JTextField textFieldAddress = new JTextField();

	// The city label and text field
	JLabel labelCity = new JLabel("City");
	JTextField textFieldCity = new JTextField();

	// The state label and text field
	JLabel labelState = new JLabel("State");
	JTextField textFieldState = new JTextField();

	// The zip label and text field
	JLabel labelZip = new JLabel("Zip");
	JTextField textFieldZip = new JTextField();

	// The prev, reset and next buttons
	JButton buttonPrev = new JButton("Prev");
	JButton buttonReset = new JButton("Resest");
	JButton buttonNext = new JButton("Next");

	/**
	 * Constructor.
	 * 
	 * @param title
	 */
	public Assignment_01(String title) {

		super(title);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		JPanel cp = (JPanel) getContentPane();

		labelHeader.setFont(new Font("TimesRoman", Font.BOLD, 24));
		labelHeader.setBounds(40, 10, 300, 50);

		labelName.setBounds(10, 80, 80, 25);
		textFieldName.setBounds(120, 80, 250, 25);

		labelAddress.setBounds(10, 110, 80, 25);
		textFieldAddress.setBounds(120, 110, 250, 25);

		labelCity.setBounds(10, 140, 80, 25);
		textFieldCity.setBounds(120, 140, 250, 25);

		labelState.setBounds(10, 170, 80, 25);
		textFieldState.setBounds(120, 170, 250, 25);

		labelZip.setBounds(10, 200, 80, 25);
		textFieldZip.setBounds(120, 200, 250, 25);

		buttonPrev.setBounds(30, 250, 80, 25);
		buttonReset.setBounds(150, 250, 80, 25);
		buttonNext.setBounds(270, 250, 80, 25);

		cp.setLayout(null);

		// Add the header label to the content pane
		cp.add(labelHeader);

		// Add the labels and text fields to the content pane
		cp.add(labelName);
		cp.add(textFieldName);
		cp.add(labelAddress);
		cp.add(textFieldAddress);
		cp.add(labelCity);
		cp.add(textFieldCity);
		cp.add(labelState);
		cp.add(textFieldState);
		cp.add(labelZip);
		cp.add(textFieldZip);

		// Add the buttons to the content pane
		cp.add(buttonPrev);
		cp.add(buttonReset);
		cp.add(buttonNext);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				shutDown();
			}
		});
	}

	/**
	 * Method invoked when the window is in the process of being closed.
	 */
	public void shutDown() {

		int returnVal = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?");

		if (returnVal == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public static void main(String args[]) {

		Assignment_01 assignment_01 = new Assignment_01("Database Browser");

		assignment_01.setSize(400, 350);
		assignment_01.setVisible(true);
	}

}