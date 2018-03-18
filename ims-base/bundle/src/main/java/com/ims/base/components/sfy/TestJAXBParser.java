/**
 * 
 */
package com.ims.base.components.sfy;

import java.io.IOException;

import javax.xml.bind.JAXBException;

/**
 * @author NK
 *
 */
public class TestJAXBParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParseXMLtoBeanServiceImpl parseXMLtoBeanServiceImpl = new ParseXMLtoBeanServiceImpl();
		try {
			parseXMLtoBeanServiceImpl.unMarshalingFromFile("H:/IMS PROJECT/AU_DESIGN_1.xml");
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}

}
