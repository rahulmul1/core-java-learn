package com.ims.base.components.sax;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class TextSaxParserDemo {

	public static void main(String[] args) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			SaxParserDemo saxParserDemo = new SaxParserDemo();
			
			ClassLoader classLoader = new TextSaxParserDemo().getClass().getClassLoader();
			String fileName = classLoader.getResource("files/Employee_SAX.xml").getFile();
				
			saxParser.parse(new File(fileName),
					saxParserDemo);
			// Get Employees list
			List<Employee> empList = saxParserDemo.getEmpList();
			// print employee information
			for (Employee emp : empList)
				System.out.println(emp);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}