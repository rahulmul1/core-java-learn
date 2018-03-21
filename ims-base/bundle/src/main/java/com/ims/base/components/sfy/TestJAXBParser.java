/**
 * 
 */
package com.ims.base.components.sfy;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.ims.base.components.dom.TestSfyDomParser;

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
			ClassLoader classLoader = new TestSfyDomParser().getClass().getClassLoader();
			String fileName = classLoader.getResource("files/AU_DESIGN_1.xml").getFile();
			parseXMLtoBeanServiceImpl.unMarshalingFromFile(fileName);
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}

}
