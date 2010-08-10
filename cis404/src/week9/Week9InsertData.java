package week9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author Andrei Tolnai
 * @date 09 August 2010
 * @assignment 9.2
 * @description Create a JSP that allows a user to select their favorite sports
 *              team and choose either winner or loser. You may use Super Bowl
 *              results or World Series results data. Based on the user
 *              selection return a tabled format showing all the years their
 *              favorite team won or lost, depending on selection. Name your JSP
 *              "-yourName-JSP" and your Web application "Week_9".
 * @company Bellevue University
 * @fileName Week9InsertData.java
 * 
 */
public class Week9InsertData {

	/**
	 * The query used in the prepared statement.
	 */
	private static final String QUERY = "INSERT INTO AndreiSuperBowl(year, winning_city, winning_team, losing_city, losing_team) VALUES(?, ?, ?, ?, ?)";

	private static Connection con;
	private static PreparedStatement prepStmt;

	private Week9InsertData() {
		// No constructor needed.
	}

	/**
	 * Opens a database connection.
	 */
	private static void initializeConnection() {
		try {
			Class.forName(Week9Constants.DB_DRIVER_CLASS);
			con = DriverManager.getConnection(Week9Constants.DB_URL, Week9Constants.DB_USER, Week9Constants.DB_PASSWORD);
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

		for (Object[] o : Week9Constants.seedData) {
			System.out.println(insert(o) + " row inserted");
		}

		closeConnection();
	}

	public static void main(String args[]) throws SQLException {

		Week9InsertData.execute();
	}

}