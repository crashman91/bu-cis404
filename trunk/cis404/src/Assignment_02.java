import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Andrei Tolnai
 * @date 20 June 2010
 * @assignment 2.2
 * @description Create a Swing application that looks and behaves like the
 *              example located at the top of this assignment. If you can't run
 *              the sample application due to Java version incompatibility, etc.
 *              read the following description: This application uses the
 *              program from last week and populates the textfields with data
 *              from the dataClassArray. Initially, the textfields are populated
 *              with the data at array index zero. When the "Next" button is
 *              pressed, the textfields should change to the next incremented
 *              array index until the end of the array is reached. The "Reset"
 *              button should re-initialize the textfields with the data at
 *              array index zero. When the "Prev" button is pressed, the
 *              textfields should change to the next decremented array index
 *              until the beginning of the array is reached.
 * @company Bellevue University
 * @fileName Assignment_02.java
 * 
 */
class Assignment_02 extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelHeader = new JLabel("Database Browser", JLabel.CENTER);

	// The name label and text field
	private JLabel labelName = new JLabel("Name");
	private JTextField textFieldName = new JTextField();

	// The address label and text field
	private JLabel labelAddress = new JLabel("Address");
	private JTextField textFieldAddress = new JTextField();

	// The city label and text field
	private JLabel labelCity = new JLabel("City");
	private JTextField textFieldCity = new JTextField();

	// The state label and text field
	private JLabel labelState = new JLabel("State");
	private JTextField textFieldState = new JTextField();

	// The zip label and text field
	private JLabel labelZip = new JLabel("Zip");
	private JTextField textFieldZip = new JTextField();

	// The prev, reset and next buttons
	private JButton buttonPrev = new JButton("Prev");
	private JButton buttonReset = new JButton("Reset");
	private JButton buttonNext = new JButton("Next");

	private DataClass[] dataClassArray = {
			new DataClass("Fred", "Wayne", "101 Here", "NE", "55551"),
			new DataClass("George", "Thomas", "102 There", "ME", "55552"),
			new DataClass("Mike", "Johnson", "103 No Where", "OK", "55553") };

	private int arrayPointer = 0;

	public Assignment_02(String title) {

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

		// Add the action listener for the "Prev" button
		buttonPrev.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (arrayPointer > 0) {
					setFields(--arrayPointer);

					// Enable the "Next" button because it could have been
					// disabled.
					buttonNext.setEnabled(true);
				}

				if (arrayPointer == 0) {

					// Disable the "Prev" button because we are at index zero.
					buttonPrev.setEnabled(false);
				}
			}
		});

		// Add the action listener for the "Reset" button
		buttonReset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				setFields(arrayPointer = 0);

				// Disable the "Prev" button because we are at index zero.
				buttonPrev.setEnabled(false);

				// Enable the "Next" button.
				buttonNext.setEnabled(true);
			}
		});

		// Add the action listener for the "Next" button
		buttonNext.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (arrayPointer < dataClassArray.length - 1) {
					setFields(++arrayPointer);

					// Enable the "Prev" button because it could have been
					// disabled.
					buttonPrev.setEnabled(true);
				}

				if (arrayPointer == dataClassArray.length - 1) {

					// Disable the "Next" button because we are at the end of
					// the array.
					buttonNext.setEnabled(false);
				}
			}
		});

		// Initially populate the text fields with the data at array index zero.
		setFields(arrayPointer);

		// Disable the "Prev" button because we are at index zero.
		buttonPrev.setEnabled(false);
	}

	/**
	 * Sets the text fields with the values from the <code>dataClassArray</code>
	 * variable at specified index (position).
	 * 
	 * @param position
	 */
	private void setFields(int position) {
		textFieldName.setText(dataClassArray[position].getName());
		textFieldAddress.setText(dataClassArray[position].getAddress());
		textFieldCity.setText(dataClassArray[position].getCity());
		textFieldState.setText(dataClassArray[position].getState());
		textFieldZip.setText(dataClassArray[position].getZipCode());
	}

	/**
	 * Method invoked when the window is in the process of being closed.
	 */
	private void shutDown() {
		int returnVal = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?");
		if (returnVal == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public static void main(String args[]) {
		Assignment_02 a2 = new Assignment_02("Database Browser");
		a2.setSize(400, 350);
		a2.setVisible(true);
	}

}

/**
 * 
 * POJO that stores the name, address, city, state and the ZIP code.
 * 
 */
class DataClass {

	private String name;
	private String address;
	private String city;
	private String state;
	private String zipCode;

	/**
	 * Constructor for DataClass.
	 * 
	 * @param name
	 * @param address
	 * @param city
	 * @param state
	 * @param zipCode
	 */
	public DataClass(String name, String address, String city, String state, String zipCode) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	/**
	 * Returns the name.
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the address.
	 * 
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Returns the city.
	 * 
	 * @return
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * Returns the state.
	 * 
	 * @return
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * Returns the ZIP code.
	 * 
	 * @return
	 */
	public String getZipCode() {
		return this.zipCode;
	}

}