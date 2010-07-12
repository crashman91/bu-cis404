import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 * @author Andrei Tolnai
 * @date 12 July 2010
 * @assignment 5.2
 * @description Download and configure the Oracle Thin Drivers (the Oracle thin
 *              drivers .jar file is attached to the top of this assignment)
 *              </br>
 * 
 *              Create an Oracle Express account with the following user ID and
 *              password: student password </br>
 * 
 *              Create a application titled -your name-Week_5. </br>
 * 
 *              Modify the CreateTable example to create a
 *              "-your name-SuperBowl" results table. Your
 *              "-your name-SuperBowl" table will hold years played, winning
 *              teams and losing teams. The CreateTable example is attached to
 *              this assignment. Attach this application to this
 *              assignment.</br>
 * 
 *              Populate your "-your name-SuperBowl" table with Super Bowl
 *              results for the last 10 years. You may accomplish this by
 *              modifying the InsertData example attached to this assignment.
 *              Attach this application to this assignment. </br>
 * 
 *              Review the simple database access example named SelectData
 *              attached to this assignment. Understanding this example will
 *              help in your completion of your last task. </br>
 * 
 *              Create a Java GUI that connects to a database, collects
 *              information and then displays it based on the user's input. I
 *              recommend starting with the complete example attached to this
 *              assignment and making the necessary modifications. Attach this
 *              application to this assignment.
 * @company Bellevue University
 * @fileName RecordByRecordDialog
 * 
 */
public class RecordByRecordDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Reference to the ResultSet navigator. It offers mechanism to get the
	 * first, the previous, the last and the next element from a RestulSet.
	 */
	private ResultSetNavigator resultSetNavigator;

	/**
	 * Definitions for the First, Previous, Next and Last buttons.
	 */
	private JButton btnFirst;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnLast;

	/**
	 * Definition of the year, winning team and losing team text fields.
	 */
	private JTextField year;
	private JTextField winningTeam;
	private JTextField losingTeam;

	/**
	 * Definition of the year, winning team and losing team text labels.
	 */
	private JLabel lblYear;
	private JLabel lblWinningTeam;
	private JLabel lblLosingTeam;

	/**
	 * Constructs a dialog to view the records from the specified result set one
	 * at a time.
	 * 
	 * @param owner
	 *            the Frame from which the dialog is displayed
	 * @param resultSet
	 *            the result set from which to view the rows
	 */
	public RecordByRecordDialog(JFrame owner, ResultSet resultSet) {
		super(owner, "Record by record browser");

		// Initialize the result set navigator;
		resultSetNavigator = new ResultSetNavigator(resultSet);

		// Initialize the dialog
		initDialog();
	}

	/**
	 * Initializes the dialog.
	 */
	private void initDialog() {

		// Initialize the buttons
		btnFirst = new JButton("First");
		btnPrevious = new JButton("Previous");
		btnNext = new JButton("Next");
		btnLast = new JButton("Last");

		// Initialize the text fields
		year = new JTextField("");
		year.setEditable(false);
		winningTeam = new JTextField("");
		winningTeam.setEditable(false);
		losingTeam = new JTextField("");
		losingTeam.setEditable(false);

		// Initialize the labels
		lblYear = new JLabel("Year Played");
		lblWinningTeam = new JLabel("Winning Team");
		lblLosingTeam = new JLabel("Losing Team");

		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);

		// Add the buttons to the panel
		getContentPane().add(btnFirst);
		getContentPane().add(btnPrevious);
		getContentPane().add(btnNext);
		getContentPane().add(btnLast);

		// Add the text fields to the panel
		getContentPane().add(year);
		getContentPane().add(winningTeam);
		getContentPane().add(losingTeam);

		// Add the labels to the panel
		getContentPane().add(lblYear);
		getContentPane().add(lblWinningTeam);
		getContentPane().add(lblLosingTeam);

		// Set bounds for the year, winning team and losing team labels
		lblYear.setBounds(new Rectangle(25, 10, 100, 30));
		lblWinningTeam.setBounds(new Rectangle(25, 45, 100, 30));
		lblLosingTeam.setBounds(new Rectangle(25, 80, 100, 30));

		// Set bounds for the year, winning team and losing team text fields
		year.setBounds(new Rectangle(140, 10, 50, 30));
		winningTeam.setBounds(new Rectangle(140, 45, 200, 30));
		losingTeam.setBounds(new Rectangle(140, 80, 200, 30));

		// Set bounds for the First, Next, Previous and Last buttons
		btnFirst.setBounds(new Rectangle(10, 120, 90, 30));
		btnNext.setBounds(new Rectangle(100, 120, 90, 30));
		btnPrevious.setBounds(new Rectangle(190, 120, 90, 30));
		btnLast.setBounds(new Rectangle(280, 120, 90, 30));

		// Add the action listers to the First, Next, Previous and Last buttons
		btnFirst.addActionListener(buttonListener);
		btnPrevious.addActionListener(buttonListener);
		btnNext.addActionListener(buttonListener);
		btnLast.addActionListener(buttonListener);
	}

	/**
	 * Displays the modal dialog.
	 */
	public void showDialog() {

		// Display the first record from the result set
		update(resultSetNavigator.getFirst());

		setSize(390, 190);
		setResizable(false);
		setModal(true);
		setVisible(true);
	}

	/**
	 * Updates the UI text fields with the values from the parameter.
	 * 
	 * @param record
	 */
	private void update(Record record) {
		year.setText(String.valueOf(record.getYear()));
		winningTeam.setText(record.getWinningTeam());
		losingTeam.setText(record.getLosingTeam());
	}

	/**
	 * Action listener for the First, Previous, Next and Last button.
	 */
	private ActionListener buttonListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			JButton button = ((JButton) e.getSource());

			if (button.equals(btnFirst)) {

				update(resultSetNavigator.getFirst());
			}

			if (button.equals(btnPrevious)) {

				update(resultSetNavigator.getPrevious());
			}

			if (button.equals(btnNext)) {

				update(resultSetNavigator.getNext());
			}

			if (button.equals(btnLast)) {

				update(resultSetNavigator.getLast());
			}
		}
	};

}