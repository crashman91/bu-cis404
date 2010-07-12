import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Andrei Tolnai
 * @date 12 June 2010
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
 * @fileName InsertData
 * 
 */
public class InsertData {

	/**
	 * The query used in the prepared statement.
	 */
	private static final String QUERY = "INSERT INTO AndreiSuperBowl(year, winning_team, losing_team) VALUES(?, ?, ?)";

	private static Connection con;
	private static PreparedStatement prepStmt;

	private InsertData() {
		// No constructor needed.
	}

	/**
	 * Opens a database connection.
	 */
	private static void initializeConnection() {
		try {
			Class.forName(Constants.DB_DRIVER_CLASS);
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
			prepStmt = con.prepareStatement(QUERY);

		} catch (Exception e) {

			System.out.println("Error connection to database.");
			System.err.println(e);
			System.exit(0);
		}
	}

	/**
	 * Closes the database connection.
	 */
	private static void closeConnection() {
		try {
			prepStmt.close();
			con.close();
			System.out.println("Database connections closed");

		} catch (SQLException e) {

			System.out.println("Connection close failed");
			System.err.println(e);
		}
	}

	/**
	 * Fills the prepared statement with the input parameters.
	 * 
	 * @param params
	 *            the parameters which are used to fill the prepared statement
	 * @throws SQLException
	 */
	private static void fillPreparedStatement(Object[] params) throws SQLException {
		if (params == null) {
			return;
		}
		for (int i = 0; i < params.length; i++) {
			if (params[i] != null) {
				prepStmt.setObject(i + 1, params[i]);
			} else {
				prepStmt.setNull(i + 1, Types.VARCHAR);
			}
		}
	}

	/**
	 * Inserts the input data into the table.
	 * 
	 * @param params
	 *            the parameters for the prepared statement
	 * @return the number of rows inserted
	 */
	private static int insert(Object[] params) {
		int rows = 0;
		try {
			fillPreparedStatement(params);
			rows = prepStmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("Insert Data Failed");
			System.err.println(e);
		}
		return rows;
	}

	/**
	 * Opens a database connection and populates the table with the seed data.
	 */
	public static void execute() {
		initializeConnection();

		List<Object[]> seedData = new ArrayList<Object[]>();
		seedData.add(new Object[] { 2000, "St. Louis Rams", "Tennessee Titans" });
		seedData.add(new Object[] { 2001, "Baltimore Ravens", "New York Giants" });
		seedData.add(new Object[] { 2002, "New England Patriots", "St. Louis Rams" });
		seedData.add(new Object[] { 2003, "Tampa Bay Buccaneers", "Oakland Raiders" });
		seedData.add(new Object[] { 2004, "New England Patriots", "Carolina Panthers" });
		seedData.add(new Object[] { 2005, "New England Patriots", "Philadelphia Eagles" });
		seedData.add(new Object[] { 2006, "Pittsburgh Steelers", "Seattle Seahawks" });
		seedData.add(new Object[] { 2007, "Indianapolis Colts", "Chicago Bears" });
		seedData.add(new Object[] { 2008, "New York Giants", "New England Patriots" });
		seedData.add(new Object[] { 2009, "Pittsburgh Steelers", "Arizona Cardinals" });
		seedData.add(new Object[] { 2010, "New Orleans Saints", "Indianapolis Colts" });

		for (Object[] o : seedData) {
			System.out.println(insert(o) + " row inserted");
		}

		closeConnection();
	}

	public static void main(String args[]) throws SQLException {

		InsertData.execute();
	}

}