import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Andrei Tolnai
 * @date 19 July 2010
 * @assignment 6.2
 * @description Create a servlet that allows a user to select their favorite
 *              football sports team. Based on their selection return a tabled
 *              format of all Super Bowl years where their selected team was a
 *              player. Indicate whether their team was the winner or loser for
 *              that year and which team they played. <li>
 *              Use the Oracle Express account created in Week 5 that was
 *              configured with the following user ID and password: student
 *              password <li>
 *              Create a Web application folder titled <your name>Week_6. <li>
 *              Use the tables created in the Week 5 assignment. Place the
 *              application (.java source file) created in Week 5 to create the
 *              tables in your Web application folder titled <your name>Week_6.
 *              <li>
 *              Modify your application created in Week 5 to populate your
 *              "-yourname-SuperBowl" table so that it includes the Super Bowl
 *              results for all years. Place the application (.java source file)
 *              created in Week 5 to populate your "-yourname-SuperBowl" table
 *              with the previous Super Bowl results in your Web application
 *              folder titled -your name-Week_6. <li>
 *              Review the simple servlet example included in this assignment.
 *              Understanding this example will help in your completion of your
 *              last task. <li>
 *              Create a Servlet that connects to a database, collects
 *              information and then displays it based on the user's input. Name
 *              your servlet "-yourName-Servlet" and add it (.java source file)
 *              to your Web application folder titled <your name>Week_6. <li>
 *              Create a web.xml file for mapping your servlet and add it to
 *              your Web application folder titled <your name>Week_6 in the
 *              proper place. <li>
 *              Read the fine print at the bottom of this assignment for
 *              submission requirements!
 * @company Bellevue University
 * @fileName Week6CreateTable.java
 * 
 */
public class Week6CreateTable {

	private static final String TABLE_NAME = "AndreiSuperBowl";

	private static Connection con;
	private static Statement stmt;

	private Week6CreateTable() {
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
			Class.forName(Week6Constants.DB_DRIVER_CLASS);

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
			con = DriverManager.getConnection(Week6Constants.DB_URL, Week6Constants.DB_USER, Week6Constants.DB_PASSWORD);

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
			stmt
					.executeUpdate("CREATE TABLE "
							+ TABLE_NAME
							+ " (year INT NOT NULL PRIMARY KEY, winning_city VARCHAR(20) NOT NULL, winning_team VARCHAR(20) NOT NULL, losing_city VARCHAR(20) NOT NULL, losing_team VARCHAR(20) NOT NULL)");
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

		Week6CreateTable.execute();
	}

}