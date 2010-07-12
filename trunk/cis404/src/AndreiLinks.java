import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Andrei Tolnai
 * @date 27 June 2010
 * @assignment 3.2
 * @description Create a servlet that displays three Internet links to some of
 *              your favorite Internet sites. Restrict your Internet sites to
 *              clean and professional sites appropriate for an academic
 *              environment. Your servlet should display an HTML table with
 *              three rows. Each row will display the Internet site links in the
 *              left column and a brief description in the right. The name of
 *              your application directory is to be "Week_03" and your servlet
 *              name is to be "yourNameLinks"
 * @company Bellevue University
 * @fileName AndreiLinks.java
 * 
 */
public class AndreiLinks extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		out.println("<html>");

		out.println("<head>");
		out.println("<title>Links</title>");
		out.println("</head>");

		out.println("<body>");

		out.println("<table border='1' width='500'>");

		out.println("<tr>");
		out.println("<td>");
		out.println("<a href='http://opera.com'>http://opera.com</a>");
		out.println("</td>");
		out.println("<td>");
		out.println("The fastest browser on Earth");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>");
		out.println("<a href='http://developer.android.com'>http://developer.android.com</a>");
		out.println("</td>");
		out.println("<td>");
		out.println("Android - for developers");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>");
		out.println("<a href='http://thebookofeli.warnerbros.com'>http://thebookofeli.warnerbros.com</a>");
		out.println("</td>");
		out.println("<td>");
		out.println("The Book of Eli - A post-apocalyptic tale, in which a lone man fights his way across America in order to protect a sacred book that holds the secrets to saving humankind.");
		out.println("</td>");
		out.println("</tr>");

		out.println("</body>");

		out.println("</html>");
	}
}