import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
 * @fileName CreateTable.java
 * 
 */
public class CreateTable {

	private static final String TABLE_NAME = "AndreiSuperBowl";

	private static Connection con;
	private static Statement stmt;

	private CreateTable() {
		// No constructor needed.
	}

	/**
	 * Opens a database connection.
	 */
	private static void initializeConnection() {
		try {
			/*
			 * 
			 * As part of its initialization, the DriverManager class will
			 * attempt to load the driver classes referenced in the
			 * "jdbc.drivers" system property. This allows a user to customize
			 * the JDBC Drivers used by their applications.
			 * 
			 * Load database driver class
			 */
			Class.forName(Constants.DB_DRIVER_CLASS);

			/*
			 * 
			 * Connection to the Oracle database on Academic 3
			 * getConnection(String url, String user, String password) ;
			 * 
			 * Attempts to establish a connection to the given database URL. The
			 * DriverManager attempts to select an appropriate driver from the
			 * set of registered JDBC drivers.
			 * 
			 * A connection (session) with a specific database. SQL statements
			 * are executed and results are returned within the context of a
			 * connection.
			 * 
			 * Connect to database
			 */
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);

			/*
			 * 
			 * The object used for executing a static SQL statement and
			 * returning the results it produces.
			 * 
			 * Create statement to access database
			 */
			stmt = con.createStatement();
		} catch (Exception e) {

			System.out.println("Error connecting to the database.");
			System.err.println(e);
			System.exit(0);
		}
	}

	/**
	 * Closes the database connection.
	 */
	private static void closeConnection() {
		try {

			/*
			 * 
			 * Releases this Statement object's database and JDBC resources
			 * immediately instead of waiting for this to happen when it is
			 * automatically closed.
			 * 
			 * Close statement
			 */
			stmt.close();

			/*
			 * 
			 * Releases this Connection object's database and JDBC resources
			 * immediately instead of waiting for them to be automatically
			 * released.
			 * 
			 * Close connection
			 */
			con.close();
			System.out.println("Database connections closed");
		} catch (SQLException e) {

			System.out.println("Connection close failed");
			System.err.println(e);
		}
	}

	/**
	 * Drops the database table.
	 */
	private static void dropTable() {
		try {
			stmt.executeUpdate("DROP TABLE " + TABLE_NAME);
			System.out.println("Table " + TABLE_NAME + " Dropped");
		} catch (SQLException e) {

			System.out.println("Table " + TABLE_NAME + " does not exist");
			System.err.println(e);
		}
	}

	/**
	 * Creates the database table.
	 */
	private static void createTable() {
		try {
			stmt.executeUpdate("CREATE TABLE " + TABLE_NAME
					+ " (year INT NOT NULL PRIMARY KEY, winning_team VARCHAR(20) NOT NULL, losing_team VARCHAR(20) NOT NULL)");
			System.out.println("Table " + TABLE_NAME + " Created");
		} catch (SQLException e) {

			System.out.println("Table " + TABLE_NAME + " Creation failed");
			System.err.println(e);
		}
	}

	/**
	 * Will open a connection to the database, will try to drop the configured
	 * table, will try to create the table and close the database connection.
	 */
	public static void execute() {
		initializeConnection();
		dropTable();
		createTable();
		closeConnection();
	}

	public static void main(String args[]) {

		CreateTable.execute();
	}

}