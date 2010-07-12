import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Andrei Tolnai
 * @date 05 July 2010
 * @assignment 4.2
 * @description Create a servlet survey on your favorite topic. Your survey
 *              needs to contain a minimum of three inputs. The survey input
 *              information will be saved to a file after each post. The
 *              response must then return the complete contents of your file
 *              including the latest post. Display your response in a tabled
 *              format and place each saved response in a separate table row.
 *              Name your data file "<yourName>data.dat" and place it in the
 *              "C:\Temp" directory. The name of your application directory is
 *              to be "Week_04<yourName>" and your servlet name is to be
 *              "Survey<yourName>".
 * @company Bellevue University
 * @fileName SurveyAndrei.java
 * 
 */
public class SurveyAndrei extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String FILE_PATH = "C:/Temp/AndreiData.dat";

	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * On doGet we display the survey form only.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		printHeader(out);
		printForm(out);
		printFooter(out);
	}

	/**
	 * On doPost (when the submit button was pressed) we display the survey
	 * results to the user.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		printHeader(out);

		out.println("<h2>Thank you for completing the survey!</h2>");

		printSurveyResults(request, out);

		printFooter(out);
	}

	/**
	 * Prints the header of the page.
	 * 
	 * @param out
	 */
	private void printHeader(PrintWriter out) {
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Survey");
		out.println("</title>");
		out.println("</head>");
		out.println("<body bgcolor='#DDD8E6'>");
		out.println("<div>");
	}

	/**
	 * Prints the form of the survey.
	 * 
	 * @param out
	 */
	private void printForm(PrintWriter out) {
		out.println("<form method='post' action='/Week_04Andrei/SurveyAndrei'>");
		out.println("<label>Complete the following survey:</label> </br>");
		out.println("Name: <input type='text' name='name' size='40' maxlength='40'/> </br>");
		out.println("Age: <input type='text' name='age' size='4' maxlength='5'/> </br>");
		out.println("Weight: <input type='text' name='weight' size='4' maxlength='5'/> </br>");
		out.println("Hair color: <input type='text' name='hairColor' size='30' maxlength='30'/> </br>");
		out.println("Favorite season: <input type='text' name='favoriteSeason' size='40' maxlength='40'/> </br>");
		out.println("<input type='submit' />");
		out.println("</form>");
	}

	/**
	 * Prints the table with the survey results.
	 * 
	 * @param request
	 * @param out
	 * @throws IOException
	 */
	private void printSurveyResults(HttpServletRequest request, PrintWriter out) throws IOException {

		// Reader the needed parameters from the request
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String weight = request.getParameter("weight");
		String hairColor = request.getParameter("hairColor");
		String favoriteSeason = request.getParameter("favoriteSeason");

		// Create a file
		File file = new File(FILE_PATH);

		// Create a file writer that will append to the file
		FileWriter fileWriter = new FileWriter(file, true);

		// Create the buffered writer
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		// Write a record to the file
		bufferedWriter.write(simpleDateFormat.format(new Date()) + "\t" + name + "\t" + age + "\t" + weight + "\t" + hairColor + "\t" + favoriteSeason + "\n");

		// Close the buffered writer stream
		bufferedWriter.close();

		// Close the file writer
		fileWriter.close();

		// Create a file reader that to read the file
		FileReader fileReader = new FileReader(file);

		// Create the buffered reader stream
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		// Print the table header for of the survey results
		out.println("<label> Here are the results of the survey up to now: </label>");
		out.println("<table border='1'>");
		out.println("<tr><th><b>Date/time</b></th>");
		out.println("<th><b>Name</b></th>");
		out.println("<th><b>Age</b></th>");
		out.println("<th><b>Weight</b></th>");
		out.println("<th><b>Hair color</b></th>");
		out.println("<th><b>Favorite season</b></th></tr>");

		// Print each record
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String[] values = line.split("\t");
			out.println("<tr>");
			for (String value : values) {
				out.println("<td>" + value + "</td");
			}
			out.println("</tr>");
		}

		// Close the buffered reader stream
		bufferedWriter.close();

		// Close the file reader
		fileReader.close();

		out.println("</table>");
	}

	/**
	 * Prints the footer of the page.
	 * 
	 * @param out
	 */
	private void printFooter(PrintWriter out) {
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}