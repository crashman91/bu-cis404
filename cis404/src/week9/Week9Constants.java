package week9;

import java.util.ArrayList;
import java.util.List;

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
 * @fileName Week9Constants.java
 * 
 */
public class Week9Constants {

	public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/email_web_client";
	public static final String DB_USER = "student";
	public static final String DB_PASSWORD = "password";

	public static final List<Object[]> seedData = new ArrayList<Object[]>();

	static {
		// The seed data is in the following format:
		// 0-year, 1-winning city, 2-winning team, 3-losing city, 4-losing team.
		seedData.add(new Object[] { 1967, "Green Bay", "Packers", "Kansas City", "Chiefs" });
		seedData.add(new Object[] { 1968, "Green Bay", "Packers", "Oakland", "Raiders" });
		seedData.add(new Object[] { 1969, "New York", "Jets", "Baltimore", "Colts" });
		seedData.add(new Object[] { 1970, "Kansas City", "Chiefs", "Minnesota", "Vikings" });
		seedData.add(new Object[] { 1971, "Baltimore", "Colts", "Dallas", "Cowboys" });
		seedData.add(new Object[] { 1972, "Dallas", "Cowboys", "Miami", "Dolphins" });
		seedData.add(new Object[] { 1973, "Miami", "Dolphins", "Washington", "Redskins" });
		seedData.add(new Object[] { 1974, "Miami", "Dolphins", "Minnesota", "Vikings" });
		seedData.add(new Object[] { 1975, "Pittsburgh", "Steelers", "Minnesota", "Vikings" });
		seedData.add(new Object[] { 1976, "Pittsburgh", "Steelers", "Dallas", "Cowboys" });
		seedData.add(new Object[] { 1977, "Oakland", "Raiders", "Minnesota", "Vikings" });
		seedData.add(new Object[] { 1978, "Dallas", "Cowboys", "Denver", "Broncos" });
		seedData.add(new Object[] { 1979, "Pittsburgh", "Steelers", "Dallas", "Cowboys" });
		seedData.add(new Object[] { 1980, "Pittsburgh", "Steelers", "Los Angeles", "Rams" });
		seedData.add(new Object[] { 1981, "Oakland", "Raiders", "Philadelphia", "Eagles" });
		seedData.add(new Object[] { 1982, "San Francisco", "49ers", "Cincinnati", "Bengals" });
		seedData.add(new Object[] { 1983, "Washington", "Redskins", "Miami", "Dolphins" });
		seedData.add(new Object[] { 1984, "Los Angeles", "Raiders", "Washington", "Redskins" });
		seedData.add(new Object[] { 1985, "San Francisco", "49ers", "Miami", "Dolphins" });
		seedData.add(new Object[] { 1986, "Chicago", "Bears", "New England", "Patriots" });
		seedData.add(new Object[] { 1987, "New York", "Giants", "Denver", "Broncos" });
		seedData.add(new Object[] { 1988, "Washington", "Redskins", "Denver", "Broncos" });
		seedData.add(new Object[] { 1989, "San Francisco", "49ers", "Cincinnati", "Bengals" });
		seedData.add(new Object[] { 1990, "San Francisco", "49ers", "Denver", "Broncos" });
		seedData.add(new Object[] { 1991, "New York", "Giants", "Buffalo", "Bills" });
		seedData.add(new Object[] { 1992, "Washington", "Redskins", "Buffalo", "Bills" });
		seedData.add(new Object[] { 1993, "Dallas", "Cowboys", "Buffalo", "Bills" });
		seedData.add(new Object[] { 1994, "Dallas", "Cowboys", "Buffalo", "Bills" });
		seedData.add(new Object[] { 1995, "San Francisco", "49ers", "San Diego", "Chargers" });
		seedData.add(new Object[] { 1996, "Dallas", "Cowboys", "Pittsburgh", "Steelers" });
		seedData.add(new Object[] { 1997, "Green Bay Packers", "New England", "Patriots" });
		seedData.add(new Object[] { 1998, "Denver", "Broncos", "Green Bay", "Packers" });
		seedData.add(new Object[] { 1999, "Denver", "Broncos", "Atlanta", "Falcons" });
		seedData.add(new Object[] { 2000, "St. Louis", "Rams", "Tennessee", "Titans" });
		seedData.add(new Object[] { 2001, "Baltimore", "Ravens", "New York", "Giants" });
		seedData.add(new Object[] { 2002, "New England", "Patriots", "St. Louis", "Rams" });
		seedData.add(new Object[] { 2003, "Tampa Bay", "Buccaneers", "Oakland", "Raiders" });
		seedData.add(new Object[] { 2004, "New England", "Patriots", "Carolina", "Panthers" });
		seedData.add(new Object[] { 2005, "New England", "Patriots", "Philadelphia", "Eagles" });
		seedData.add(new Object[] { 2006, "Pittsburgh", "Steelers", "Seattle", "Seahawks" });
		seedData.add(new Object[] { 2007, "Indianapolis", "Colts", "Chicago", "Bears" });
		seedData.add(new Object[] { 2008, "New York", "Giants", "New England", "Patriots" });
		seedData.add(new Object[] { 2009, "Pittsburgh", "Steelers", "Arizona", "Cardinals" });
		seedData.add(new Object[] { 2010, "New Orleans", "Saints", "Indianapolis", "Colts" });
	}

}