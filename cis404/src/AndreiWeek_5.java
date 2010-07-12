import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

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
 * @fileName AndreiWeek_5
 * 
 */
public class AndreiWeek_5 extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * The text field for the user inputed query .
	 */
	private JTextField userQuery;

	/**
	 * The buttons 'Run query' and 'Record by record browser' buttons.
	 */
	private JButton runQuery;
	private JButton recordByRecordBrowser;

	/**
	 * The results table.
	 */
	private JTable table;

	/**
	 * The label for the status message.
	 */
	private JLabel statusMessage;

	/**
	 * Reference to the record by record browser dialog.
	 */
	private RecordByRecordDialog recordByRecordDialog;

	/**
	 * Reference to the current result set.
	 */
	private ResultSet resultSet;

	/**
	 * Constructs the GUI that connects to a database, collects information and
	 * then displays it based on the user's input
	 */
	public AndreiWeek_5() {
		super("AndreiWeek_5");

		// Initialize the frame
		initFrame();
	}

	/**
	 * Initializes the frame.
	 */
	private void initFrame() {

		// Set the default query
		userQuery = new JTextField("SELECT * FROM AndreiSuperBowl");

		// Initialize the run query button
		runQuery = new JButton("Run query");
		runQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runUserQuery();
			}
		});

		// Initialize the button that opens the record by record browser dialog
		recordByRecordBrowser = new JButton("Record by record browser");
		recordByRecordBrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openRecordByRecordBrowser();
			}
		});

		// Initialize the table and status message label
		table = new JTable();
		statusMessage = new JLabel();

		// Build the panels
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(userQuery, BorderLayout.NORTH);
		JPanel panel2 = new JPanel(new FlowLayout());
		panel2.add(runQuery);
		panel2.add(recordByRecordBrowser);
		panel1.add(panel2, BorderLayout.SOUTH);

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.white);
		contentPane.add(panel1, BorderLayout.NORTH);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		contentPane.add(statusMessage, BorderLayout.SOUTH);
	}

	/**
	 * Called before the form is closed.
	 */
	private void shutDown() {
		int returnVal = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?");
		if (returnVal == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * Displays the frame.
	 */
	private void showFrame() {
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				shutDown();
			}
		});

		setVisible(true);

		// Populate the UI table using the default query
		runUserQuery();
	}

	/**
	 * Opens the dialog that displays the records from the UI table one at a
	 * time.
	 */
	private void openRecordByRecordBrowser() {
		recordByRecordDialog = new RecordByRecordDialog(AndreiWeek_5.this, resultSet);
		recordByRecordDialog.showDialog();
	}

	private void runUserQuery() {
		// It may take a while to get the results, so give the user some
		// immediate feedback that their query was accepted.
		statusMessage.setText("Connecting to the database...");

		// In order to allow the feedback message to be displayed, we don't
		// run the query directly, but instead place it on the event queue
		// to be run after all pending events and redisplays are done.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Connection connection = null;
				Statement statement = null;
				try {
					Class.forName(Constants.DB_DRIVER_CLASS);
					connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);

					statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

					// Initialize the result set attribute.
					resultSet = statement.executeQuery(userQuery.getText());

					// Set the table model.
					table.setModel(new ResultSetTableModel(resultSet));

					// Update the status label.
					statusMessage.setText("");
				} catch (Exception ex) {

					System.err.println(ex);

					// If something goes wrong, clear the message line
					statusMessage.setText("");

					// Then display the error in a dialog box
					JOptionPane.showMessageDialog(AndreiWeek_5.this, new String[] {
					// Display a 2-line message
							ex.getClass().getName() + ": ", ex.getMessage() });
				}
			}
		});
	}

	public static void main(String args[]) {

		// Drop and recreate the table.
		CreateTable.execute();

		// Populate the table with seed data.
		InsertData.execute();

		// Launch the frame.
		AndreiWeek_5 frame = new AndreiWeek_5();
		frame.showFrame();
	}

	/**
	 * This class takes a JDBC ResultSet object and implements the TableModel
	 * interface in terms of it so that a Swing JTable component can display the
	 * contents of the ResultSet. </br>
	 * 
	 * Note that it requires a scrollable JDBC 2.0 ResultSet.</br>
	 * 
	 * Also note that it provides read-only access to the results.</br>
	 * 
	 * The code of this inner-class was obtained and adapted from the following
	 * source:
	 * http://www.oreillynet.com/pub/a/oreilly/java/news/javaex_1000.html
	 **/
	public class ResultSetTableModel implements TableModel {

		/**
		 * The ResultSet to interpret
		 */
		private ResultSet results;

		/**
		 * Additional information about the results
		 */
		private ResultSetMetaData metadata;

		/**
		 * How many rows and columns in the table
		 */
		private int numcols, numrows;

		/**
		 * This constructor creates a TableModel from a ResultSet. It is package
		 * private because it is only intended to be used by
		 * ResultSetTableModelFactory, which is what you should use to obtain a
		 * ResultSetTableModel
		 **/
		public ResultSetTableModel(ResultSet results) throws SQLException {

			// Save the results
			this.results = results;

			// Get meta-data on them
			metadata = results.getMetaData();

			// How many columns?
			numcols = metadata.getColumnCount();

			// Move to last row
			results.last();

			// How many rows?
			numrows = results.getRow();
		}

		/**
		 * Call this when done with the table model. It closes the ResultSet and
		 * the Statement object used to create it.
		 **/
		private void close() {
			try {
				results.getStatement().close();
			} catch (SQLException e) {
			}
		}

		/** Automatically close when we're garbage collected */
		protected void finalize() {
			close();
		}

		public int getColumnCount() {
			return numcols;
		}

		public int getRowCount() {
			return numrows;
		}

		/**
		 * Returns columns names from the ResultSetMetaData
		 */
		public String getColumnName(int column) {
			try {
				return metadata.getColumnLabel(column + 1);
			} catch (SQLException e) {
				return e.toString();
			}
		}

		/**
		 * This TableModel method specifies the data type for each column. We
		 * could map SQL types to Java types, but for this example, we'll just
		 * convert all the returned data to strings.
		 */
		@SuppressWarnings("unchecked")
		public Class getColumnClass(int column) {
			return String.class;
		}

		/**
		 * This is the key method of TableModel: it returns the value at each
		 * cell of the table. We use strings in this case. If anything goes
		 * wrong, we return the exception as a string, so it will be displayed
		 * in the table. Note that SQL row and column numbers start at 1, but
		 * TableModel column numbers start at 0.
		 **/
		public Object getValueAt(int row, int column) {
			try {

				// Go to the specified row
				results.absolute(row + 1);

				// Get value of the column
				Object o = results.getObject(column + 1);

				if (o == null) {
					return null;
				} else {

					// Convert it to a string
					return o.toString();
				}
			} catch (SQLException e) {
				return e.toString();
			}
		}

		public boolean isCellEditable(int row, int column) {

			// Our table isn't editable
			return false;
		}

		public void setValueAt(Object value, int row, int column) {
			// Since its not editable, we don't need to implement these methods
		}

		public void addTableModelListener(TableModelListener l) {
		}

		public void removeTableModelListener(TableModelListener l) {
		}

	}

}