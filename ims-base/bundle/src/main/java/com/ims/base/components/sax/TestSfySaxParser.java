package com.ims.base.components.sax;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.ims.base.bean.sfy.Country;
import com.ims.base.bean.sfy.FinishProductModel;

public class TestSfySaxParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			SfySaxParser sfySaxParser = new SfySaxParser();
			
			ClassLoader classLoader = new TestSfySaxParser().getClass().getClassLoader();
			String fileName = classLoader.getResource("files/AU_DESIGN_1.xml").getFile();
			
			File fileURL = new File(fileName);
			
			saxParser.parse(fileURL,sfySaxParser);

			Country country = sfySaxParser.getCountry();
			
			List<FinishProductModel> finishProductModelList = country.getDesign().getFinishProductModel();
			for (FinishProductModel finishProductModel : finishProductModelList) {
				System.out.println(finishProductModel.getModel());
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}
