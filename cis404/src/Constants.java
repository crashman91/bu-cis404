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
 * @fileName Constants
 * 
 */
public class Constants {

	public static final String DB_DRIVER_CLASS = "oracle.jdbc.OracleDriver";
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String DB_USER = "student";
	public static final String DB_PASSWORD = "password";

}