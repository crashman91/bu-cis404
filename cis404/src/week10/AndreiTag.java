package week10;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * @author Andrei Tolnai
 * @date 16 August 2010
 * @assignment 10.2
 * @description Create a custom tag that displays your name. Your tag class can
 *              include a body or not include one (see pgs. 385 and 381
 *              respectively). Place your custom tag in a JSP file named
 *              -yourname-CustomTag and place your JSP in an application named
 *              "Week_10".
 * @company Bellevue University
 * @fileName AndreiTag.java
 * 
 */
public class AndreiTag extends TagSupport {

	private static final long serialVersionUID = 8387114601125622407L;

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.print("Andrei Tolnai");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
}