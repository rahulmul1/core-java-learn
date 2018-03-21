package com.ims.base.components.dom;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.ims.base.bean.sfy.Country;
import com.ims.base.bean.sfy.FinishProductModel;

public class TestSfyDomParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassLoader classLoader = new TestSfyDomParser().getClass().getClassLoader();
		String fileName = classLoader.getResource("files/AU_DESIGN_1.xml").getFile();
		
		File fileURL = new File(fileName);
		
		SfyDomParser sfyDomParser = new SfyDomParser();
		try {
			Country country = sfyDomParser.getBeanFromXML(fileURL);
			List<FinishProductModel> finishProductModelList = country.getDesign().getFinishProductModel();
			for (FinishProductModel finishProductModel : finishProductModelList) {
				System.out.println(finishProductModel.getModel());
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}
