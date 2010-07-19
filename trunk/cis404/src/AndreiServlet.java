import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @fileName AndreiServlet.java
 * 
 */
public class AndreiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Flag to indicate if the servlet was accessed for the first time.
	 */
	private boolean firstAccess = true;

	/**
	 * Initializes the database on the first time this servlet is being
	 * accessed. This method has synchronized access.
	 */
	private synchronized void initializeDatabase() {

		if (firstAccess) {

			// Drop and recreate the table.
			Week6CreateTable.execute();

			// Populate the table with seed data.
			Week6InsertData.execute();

			// Change the flag value.
			firstAccess = false;
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		initializeDatabase();

		PrintWriter out = response.getWriter();

		printHeader(out);

		printForm(out);

		printFooter(out);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		printHeader(out);

		printForm(out);

		try {

			// Create the database connection
			Class.forName(Week6Constants.DB_DRIVER_CLASS);
			Connection con = DriverManager.getConnection(Week6Constants.DB_URL, Week6Constants.DB_USER, Week6Constants.DB_PASSWORD);
			System.out.println("Database connection created.");

			// Read the team name
			String selectedTeam = request.getParameter("Team");

			// Read the place where we will search:
			// 0-winning and losing teams, 1-winning teams, 2-losing teams
			String selectedPlace = request.getParameter("Place");

			StringBuilder query = new StringBuilder("SELECT year, winning_city, winning_team, losing_city, losing_team FROM AndreiSuperBowl ");

			// Will tell us if there are one or two parameters for the query
			boolean twoParameters = false;

			if (selectedPlace.equals("0")) {
				query.append(" WHERE winning_team = ? OR losing_team = ?");
				twoParameters = true;

			} else if (selectedPlace.equals("1")) {
				query.append(" WHERE winning_team = ?");
				twoParameters = false;

			} else if (selectedPlace.equals("2")) {
				query.append(" WHERE losing_team = ?");
				twoParameters = false;
			}

			// Create the prepared statement object.
			// Prepared statement is preferred in order to avoid SQL injection.
			PreparedStatement prepStmt = con.prepareStatement(query.toString());

			// Set the parameters for the prepared statement.
			prepStmt.setObject(1, selectedTeam);
			if (twoParameters) {
				prepStmt.setObject(2, selectedTeam);
			}

			ResultSet resultSet = prepStmt.executeQuery();

			out.println("<table border='1'>");

			boolean evenRow = false;
			boolean firstRow = true;

			while (resultSet.next()) {

				if (firstRow) {

					// Build the table header
					out.println("<tr bgcolor='#306EFF'>");
					out.println("<th><center>Year</center></th>");
					out.println("<th><center>Winning City</center></th>");
					out.println("<th><center>Winning Team</center></th>");
					out.println("<th><center>Losing City</center></th>");
					out.println("<th><center>Losing Team</center></th>");
					out.println("</tr>");

					firstRow = false;
				}

				if (evenRow) {

					out.println("<tr bgcolor='#56A5EC'>");
					evenRow = false;
				} else {

					out.println("<tr bgcolor='#659EC7'>");
					evenRow = true;
				}

				for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {

					out.println("<td>");
					out.print((resultSet.getString(i)).trim() + " ");
					out.println("</td>");
				}

				out.println("</tr>");
			}

			if (firstRow) {

				// If we are here, means no rows were obtained from the database
				out.println("<tr><td>No results!</td></tr>");
			}

			out.println("</table>");

			prepStmt.close();
			con.close();
			System.out.println("Database connections closed.");

		} catch (SQLException e) {

			out.println("<font color='red'>Database error. See the log.</font>");
			System.err.println("Database error.");
			System.err.println(e);

		} catch (Exception e) {

			out.println("<font color='red'>Exception. See the log.</font>");
			System.err.println(e);
		}

		printFooter(out);
	}

	/**
	 * Prints the upper part of the page
	 * 
	 * @param out
	 */
	private void printHeader(PrintWriter out) {
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Week 6 - Super Bowl teams</title>");
		out.println("</head>");
		out.println("<body bgcolor='#E6EDF4'>");
		out.println("<div>");
	}

	/**
	 * Prints the main form from the page
	 * 
	 * @param out
	 */
	private void printForm(PrintWriter out) {

		out.println("<form method='post' action='/AndreiWeek_6/AndreiServlet'>");

		out.println("<label>Select a Super Bowl team:</label>");

		// Build the drop-down with the team names
		out.println("<select name='Team'>");
		for (String teamName : obtainUniqueTeamNames()) {
			out.println("<option>" + teamName + "</option>");
		}
		out.println("</select>");

		out.println("<br /><br />");

		out.println("<label>Select where to look:</label>");

		// Build the drop-down with the places where to search
		out.println("<select name='Place'>");
		out.println("<option value='0'>Winning and losing teams</option>");
		out.println("<option value='1'>Winning teams</option>");
		out.println("<option value='2'>Losing teams</option>");
		out.println("</select>");

		out.println("<br /><br />");

		out.println("<input type='submit' value='Search'/>");

		out.println("</form>");
	}

	/**
	 * The method will return the unique winning and losing team names.
	 * 
	 * @return a list with team names
	 */
	private List<String> obtainUniqueTeamNames() {

		List<String> listToReturn = new ArrayList<String>();

		try {

			// Connect to the database
			Class.forName(Week6Constants.DB_DRIVER_CLASS);
			Connection con = DriverManager.getConnection(Week6Constants.DB_URL, Week6Constants.DB_USER, Week6Constants.DB_PASSWORD);
			Statement stmt = con.createStatement();
			System.out.println("Database connection created.");

			// Obtain the result set
			ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT winning_team AS team FROM andreisuperbowl "
					+ "UNION SELECT DISTINCT losing_team AS team FROM andreisuperbowl " + "ORDER BY team ASC");
			System.out.println("Team names obtained.");

			// Add the results to the list
			while (resultSet.next()) {
				listToReturn.add(resultSet.getString(1));
			}

			// Close the database connection
			stmt.close();
			con.close();
			System.out.println("Database connections closed.");

		} catch (Exception e) {

			System.err.println("Database error.");
			System.err.println(e);
		}

		return listToReturn;
	}

	/**
	 * Prints the bottom part of the page
	 * 
	 * @param out
	 */
	private void printFooter(PrintWriter out) {

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}