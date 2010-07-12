import java.sql.ResultSet;
import java.sql.SQLException;

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
 * @fileName ResultSetNavigator
 * 
 */
public class ResultSetNavigator {

	/**
	 * The result set which will be used for navigation
	 */
	private ResultSet resultSet;

	/**
	 * Constructs a result set navigator. It cat return the first, the next, the
	 * previous and the last record from the result set supplied.
	 * 
	 * @param resultSet
	 *            the result set which will be used for navigation.
	 */
	public ResultSetNavigator(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	/**
	 * 
	 * @return the first row from the result set as a <code>Record</code>
	 *         object.
	 */
	public Record getFirst() {
		Record myRecord = new Record();

		try {
			resultSet.first();
			myRecord = mapResultSetToObject(resultSet);
		} catch (Exception e) {
			System.err.println(e);
		}

		return myRecord;
	}

	/**
	 * 
	 * @return the next row from the result set as a <code>Record</code> object.
	 */
	public Record getNext() {
		Record myRecord = new Record();

		try {
			resultSet.next();
			myRecord = mapResultSetToObject(resultSet);
		} catch (SQLException e) {
			return this.getFirst();
		} catch (Exception e) {
			System.err.println(e);
		}

		return myRecord;
	}

	/**
	 * 
	 * @return the previous row from the result set as a <code>Record</code>
	 *         object.
	 */
	public Record getPrevious() {
		Record myRecord = new Record();

		try {
			resultSet.previous();
			myRecord = mapResultSetToObject(resultSet);
		} catch (SQLException e) {
			return this.getLast();
		} catch (Exception e) {
			System.err.println(e);
		}

		return myRecord;
	}

	/**
	 * 
	 * @return the last row from the result set as a <code>Record</code> object.
	 */
	public Record getLast() {
		Record myRecord = new Record();

		try {
			resultSet.last();
			myRecord = mapResultSetToObject(resultSet);
		} catch (Exception e) {
			System.err.println(e);
		}

		return myRecord;
	}

	/**
	 * Maps the columns from the result set to a <code>Record</code> type
	 * object.
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private Record mapResultSetToObject(ResultSet resultSet) throws SQLException {
		Record record = new Record();
		record.setYear(resultSet.getInt(resultSet.findColumn("year")));
		record.setWinningTeam(resultSet.getString(resultSet.findColumn("winning_team")));
		record.setLosingTeam(resultSet.getString(resultSet.findColumn("losing_team")));

		return record;
	}

}