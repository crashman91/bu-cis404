<%
	/**
	 * @author Andrei Tolnai
	 * @date 09 August 2010
	 * @assignment 9.2
	 * @description Create a JSP that allows a user to select their
	 *              favorite sports team and choose either winner or loser. You
	 *              may use Super Bowl results or World Series results data.
	 *              Based on the user selection return a tabled format showing
	 *              all the years their favorite team won or lost, depending on
	 *              selection. Name your JSP "<yourName>JSP" and your Web
	 *              application "Week_9".
	 * @company Bellevue University
	 * @fileName AndreiJSP.jsp
	 * 
	 */
%>

<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="week9.Week9Constants"%>
<%@ page import="week9.Week9CreateTable"%>
<%@ page import="week9.Week9InsertData"%>

<%!Connection con = null;%>
<%!PreparedStatement prepStmt = null;%>
<%!ResultSet resultSet = null;%>
<%!List<String> uniqueTeamNames = new ArrayList<String>();%>

<html>

<head>

<title>Select Team</title>

</head>

<body bgcolor='#E6EDF4'>
<%
	if (request.getParameter("init_db") == null || "true".equals(request.getParameter("init_db"))) {
		// Drop and recreate the table.
		Week9CreateTable.execute();

		// Populate the table with seed data.
		Week9InsertData.execute();
		
		
		try {

			// Connect to the database
			Class.forName(Week9Constants.DB_DRIVER_CLASS);
			Connection con = DriverManager.getConnection(Week9Constants.DB_URL, Week9Constants.DB_USER, Week9Constants.DB_PASSWORD);
			Statement stmt = con.createStatement();
			System.out.println("Database connection created.");

			// Obtain the result set
			ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT winning_team AS team FROM andreisuperbowl "
					+ "UNION SELECT DISTINCT losing_team AS team FROM andreisuperbowl " + "ORDER BY team ASC");
			System.out.println("Team names obtained.");

			// Add the results to the list
			while (resultSet.next()) {
				uniqueTeamNames.add(resultSet.getString(1));
			}

			// Close the database connection
			stmt.close();
			con.close();
			System.out.println("Database connections closed.");

		} catch (Exception e) {

			System.err.println("Database error.");
			System.err.println(e);
		}
	}
%>

<form method='post' action='/Week_9/AndreiJSP.jsp'>
	<label>Select a Super Bowl team:</label>
	<select name='Team'>
	<option selected='selected'><%=request.getParameter("Team") != null ? request.getParameter("Team") : ""%></option>
	<%
		for (String teamName : uniqueTeamNames) {
	%>
	<option><%=teamName%></option>
	<%
		}
	%>
</select> <br />

<br />

<label>Select where to look:</label>
	<select name='Place'>
	<option selected='selected'><%=request.getParameter("Place") != null ? request.getParameter("Place") : "Winner"%></option>
	<%
		if (request.getParameter("Place") != null && request.getParameter("Place").equals("Winner")) {
	%>
	<option>Loser</option>
	<%
		} else {
	%>
	<option>Winner</option>
	<%
		}
	%>
</select> <br />

<br />

<input type='hidden' name='init_db' value='false' />
<input type='submit' value='Search' />
<%
 	if (request.getMethod().equals("POST")) {

 		try {

 			// Create the database connection
 			Class.forName(week9.Week9Constants.DB_DRIVER_CLASS);
 			con = DriverManager.getConnection(week9.Week9Constants.DB_URL, week9.Week9Constants.DB_USER, week9.Week9Constants.DB_PASSWORD);
 			System.out.println("Database connection created.");

 		} catch (Exception e) {
 %>
<h1>Error connection to database.</h1>
<%
	return;
		}

		try {

			// Read the team name
			String selectedTeam = request.getParameter("Team");

			// Read the place where we will search: Winner or Loser
			String selectedPlace = request.getParameter("Place");

			StringBuilder query = new StringBuilder("SELECT year, winning_city, winning_team, losing_city, losing_team FROM AndreiSuperBowl ");

			boolean whereClauseAppened = false;

			if (selectedPlace.equals("Winner")) {
				query.append(" WHERE winning_team = ?");
				whereClauseAppened = true;
			} else if (selectedPlace.equals("Loser")) {
				query.append(" WHERE losing_team = ?");
				whereClauseAppened = true;
			}

			// Create the prepared statement object.
			// Prepared statement is preferred in order to avoid SQL injection.
			prepStmt = con.prepareStatement(query.toString());

			// Set the parameters for the prepared statement.
			if (whereClauseAppened) {
				prepStmt.setObject(1, selectedTeam);
			}

			resultSet = prepStmt.executeQuery();
		} catch (SQLException e) {
%>

<b>Failed looking for your favorite Team.</b>
<br />

<%
	}
%>

<table border='1'>
	<%
		boolean evenRow = false;
			boolean firstRow = true;

			while (resultSet.next()) {

				if (firstRow) {

					// Build the table header
	%>
	<tr bgcolor='#306EFF'>
		<th>
		<center>Year</center>
		</th>
		<th>
		<center>Winning City</center>
		</th>
		<th>
		<center>Winning Team</center>
		</th>
		<th>
		<center>Losing City</center>
		</th>
		<th>
		<center>Losing Team</center>
		</th>
	</tr>
	<%
		firstRow = false;
				}

				if (evenRow) {
	%>
	<tr bgcolor='#56A5EC'>
		<%
			evenRow = false;
					} else {
		%>
	
	<tr bgcolor='#659EC7'>
		<%
			evenRow = true;
					}

					for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
		%>
		<td><%=resultSet.getString(i).trim()%></td>
		<%
			}
		%>
	</tr>
	<%
		}

			if (firstRow) {

				// If we are here, means no rows were obtained from the database
	%>
	<tr>
		<td>No results!</td>
	</tr>
	<%
		}
	%>
</table>
<%
	prepStmt.close();
		con.close();
		System.out.println("Database connections closed.");
	}
%>

</body>
</html>