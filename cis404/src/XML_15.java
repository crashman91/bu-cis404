import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Andrei Tolnai
 * @date 26 July 2010
 * @assignment 7.2
 * @description Modify the provided code to: <li>
 *              Add a Student_ID attribute node to each Student node <li>
 *              Comment in your own words this source code where indicated <li>
 *              Add two additional student nodes
 * @company Bellevue University
 * @fileName XML_15.java
 * 
 */
public class XML_15 {

	final static String indentSpace = "   ";

	public static void main(String[] args) {

		/*
		 * Create a "DocumentBuilderFactory" type variable that will allow us to
		 * create a new instance of a "DocumentBuilder" object.
		 */
		DocumentBuilderFactory documentBuilderFactory = null;

		/*
		 * Create a "DocumentBuilder" type variable that will allow to create a
		 * new instance of a "Document" type object.
		 */
		DocumentBuilder documentBuilder = null;

		/*
		 * Create a "Document" type variable that will provide access to the
		 * document data.
		 */
		Document document = null;

		/*
		 * Create an "Element" type variable that will represent the root of the
		 * XML document that will be built bellow.
		 */
		Element rootElement = null;

		/* Obtain a new instance of a "DocumentBuilderFactory" class */
		documentBuilderFactory = DocumentBuilderFactory.newInstance();

		try {

			/* Obtain a new instance of a "DocumentBuilder". */
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (javax.xml.parsers.ParserConfigurationException e) {

			System.out.println("ParserConfigurationException thrown");
		}

		/*
		 * Initialize the "Document" type variable which is used to build the
		 * XML document with students.
		 */
		document = documentBuilder.newDocument();

		/*
		 * Create the root element of the XML document. The name of the node is
		 * the one specified.
		 */
		rootElement = document.createElement("Students");

		/* Add the root node previously created to the XML document. */
		document.appendChild(rootElement);

		/*
		 * Add all the other student nodes created with the "createStudentNode"
		 * method to the root node of the XML document.
		 */
		rootElement.appendChild(createStudentNode(document, "1", "Larry", "Wilson"));
		rootElement.appendChild(createStudentNode(document, "2", "Sue", "Doe"));
		rootElement.appendChild(createStudentNode(document, "3", "Mary", "Johnson"));
		rootElement.appendChild(createStudentNode(document, "4", "Richard", "Alpert"));
		rootElement.appendChild(createStudentNode(document, "5", "Jack", "Shephard"));

		/*
		 * Call the "display" method to print out to System.out output stream
		 * the content of the XML document.
		 */
		display(document, "", new PrintWriter(System.out, true));

		System.out.println();
	}

	/**
	 * Creates a student node using the specified parameters.
	 * 
	 * @param document
	 * @param studentId
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	private static Node createStudentNode(Document document, String studentId, String firstName, String lastName) {

		Element studentElement = document.createElement("Student");

		studentElement.setAttribute("Student_ID", studentId);

		/*
		 * Create a new "Element" type object that will store the first name of
		 * the student.
		 */
		Element firstNameElement = document.createElement("FirstName");

		/*
		 * To the first name node a value is added that actually represents the
		 * first name of the student.
		 */
		firstNameElement.appendChild(document.createTextNode(firstName));

		Element lastNameElement = document.createElement("LastName");
		lastNameElement.appendChild(document.createTextNode(lastName));

		studentElement.appendChild(firstNameElement);
		studentElement.appendChild(lastNameElement);

		return studentElement;
	}

	/**
	 * Displays the node information to the provided PrintWriter type object.
	 * 
	 * @param node
	 * @param indent
	 * @param out
	 */
	private static void display(Node node, String indent, PrintWriter out) {

		if (node == null) {

			return;
		}

		switch (node.getNodeType()) {

		// Document Node
		case Node.DOCUMENT_NODE: {

			out.print(indent + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

			display(((Document) node).getDocumentElement(), indent, out);
			break;
		}
			// Element Node
		case Node.ELEMENT_NODE: {

			out.print("\n" + indent + "<" + node.getNodeName());

			// Process Attribute Nodes
			if (node.getAttributes() != null) {

				int nodeAttSize = node.getAttributes().getLength();

				for (int i = 0; i < nodeAttSize; ++i) {

					out.print(" " + node.getAttributes().item(i).getNodeName() + "=\"" + node.getAttributes().item(i).getNodeValue() + "\"");
				}
			}

			out.print(">");

			// Process Child Nodes
			NodeList childNodes = node.getChildNodes();

			if (childNodes != null) {

				for (int i = 0; i < childNodes.getLength(); ++i) {

					display(childNodes.item(i), (indent + indentSpace), out);
				}
			}

			out.print("\n" + indent + "</" + node.getNodeName() + ">");
			out.flush();
			break;
		}
			// Text node
		case Node.TEXT_NODE: {

			String nodeValue = node.getNodeValue();

			nodeValue = nodeValue.trim().replaceAll("\n", "");

			// Prevents printing of blank lines between nodes
			if ((nodeValue.indexOf("\n") < 0) && (nodeValue.length() > 0)) {

				out.print("\n" + indent + nodeValue);
			}
			break;
		}
		}
	}

}