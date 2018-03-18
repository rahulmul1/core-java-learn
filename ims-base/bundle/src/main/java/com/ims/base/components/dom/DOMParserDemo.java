package com.ims.base.components.dom;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParserDemo {

	public static void main(String[] args) throws Exception {
		// Get the DOM Builder Factory
		String xmlPath = "H:/IMS PROJECT/Employee.xml";
		
		System.out.println("Read XML ->");
		List<Employee> empList = readXML(xmlPath);
		// Printing the Employee list populated.
		for (Employee emp : empList) {
			System.out.println(emp);
		}
		
		System.out.println("Modify XML ->");
		modifyXML(xmlPath);
		
	}

	
	
	private static void modifyXML(String fileURL) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		Document document = getDocument(fileURL);
		Element documentElement = document.getDocumentElement();
	
		NodeList empNodes = documentElement.getChildNodes();
		for (int i = 0; i < empNodes.getLength(); i++) {
			Node empNode = empNodes.item(i);
			if(empNode instanceof Element){
				Boolean ageExist = false;
				String id = ((Element) empNode).getAttribute("id");
				((Element) empNode).setAttribute("id", String.valueOf((Integer.parseInt(id)+1)));
				
				NodeList charNodes = empNode.getChildNodes();
				for (int j = 0; j < charNodes.getLength(); j++) {
					Node charNode = charNodes.item(j);
					if(charNode instanceof Element){
						
						if("age".equals(charNode.getNodeName())){
							ageExist = true;
							((Element) charNode).setTextContent(String.valueOf((Integer.parseInt(charNode.getTextContent())+1)));
						}
						
						if("lastName".equals(charNode.getNodeName())){
							empNode.removeChild(charNode);
						}
					}
				}
				
				if(!ageExist){
					createNewElement(document, empNode, "age",
							"0");
				}
				
			}
		}
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String filePath = "H:/IMS PROJECT/Created Xmls/" + "XML" + timeStamp;
		createXML(document , filePath);
		
	}



	/**
	 * @param document
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 */
	private static void createXML(Document document , String filePath)
			throws TransformerFactoryConfigurationError,
			TransformerConfigurationException, TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(filePath));
		transformer.transform(source, result);
 
		System.out.println("File saved!");
	}



	/**
	 * @param document
	 * @param empNode
	 * @param elementName
	 * @param elementValue
	 * @throws DOMException
	 */
	private static void createNewElement(Document document, Node empNode,
			String elementName, String elementValue) throws DOMException {
		Element age = document.createElement(elementName);
		age.appendChild(document.createTextNode(elementValue));
		empNode.appendChild(age);
	}
	
	/**
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws DOMException
	 */
	private static List<Employee> readXML(String fileURL)
			throws ParserConfigurationException, SAXException, IOException,
			DOMException {
		Document document = getDocument(fileURL);

		List<Employee> empList = new ArrayList<Employee>();

		Element documentElement = document.getDocumentElement();
		System.out.println(documentElement.getNodeName());
		System.out.println(documentElement.getTextContent());

		// Iterating through the nodes and extracting the data.
		NodeList nodeList = documentElement.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node instanceof Element) {

				Employee emp = new Employee();
				emp.id = node.getAttributes().getNamedItem("id").getNodeValue();

				NodeList childNodes = node.getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
					Node cNode = childNodes.item(j);
					if (cNode instanceof Element) {

						String content = cNode.getTextContent().trim();
						switch (cNode.getNodeName()) {
						case "firstName":
							emp.firstName = content;
							break;
						case "lastName":
							emp.lastName = content;
							break;
						case "location":
							emp.location = content;
							break;
						}
					}
				}
				empList.add(emp);
			}

		}
		return empList;
	}



	/**
	 * @param fileURL
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static Document getDocument(String fileURL)
			throws ParserConfigurationException, SAXException, IOException {
		File fileurl = new File(fileURL);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// Get the DOM Builder
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Load and Parse the XML document
		// document contains the complete XML as a Tree.
		Document document = builder.parse(fileurl);
		return document;
	}
}

class Employee {
	String id;
	String firstName;
	String lastName;
	String location;

	@Override
	public String toString() {
		return firstName + " " + lastName + "(" + id + ")" + location;
	}
}