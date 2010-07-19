import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

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
 * @fileName Week6InsertData.java
 * 
 */
public class Week6InsertData {

	/**
	 * The query used in the prepared statement.
	 */
	private static final String QUERY = "INSERT INTO AndreiSuperBowl(year, winning_city, winning_team, losing_city, losing_team) VALUES(?, ?, ?, ?, ?)";

	private static Connection con;
	private static PreparedStatement prepStmt;

	private Week6InsertData() {
		// No constructor needed.
	}

	/**
	 * Opens a database connection.
	 */
	private static void initializeConnection() {
		try {
			Class.forName(Week6Constants.DB_DRIVER_CLASS);
			con = DriverManager.getConnection(Week6Constants.DB_URL, Week6Constants.DB_USER, Week6Constants.DB_PASSWORD);
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

		for (Object[] o : Week6Constants.seedData) {
			System.out.println(insert(o) + " row inserted");
		}

		closeConnection();
	}

	public static void main(String args[]) throws SQLException {

		Week6InsertData.execute();
	}

}