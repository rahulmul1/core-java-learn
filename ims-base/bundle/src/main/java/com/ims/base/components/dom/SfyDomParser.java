/**
 * 
 */
package com.ims.base.components.dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ims.base.bean.sfy.Country;
import com.ims.base.bean.sfy.Design;
import com.ims.base.bean.sfy.FinishProductModel;
import com.ims.base.bean.sfy.StonesAvailableCharacteristic;

/**
 * @author NK
 *
 */
public class SfyDomParser {

	public Country getBeanFromXML(File fileUrl) throws ParserConfigurationException, SAXException, IOException{
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder newDocumentBuilder = builderFactory.newDocumentBuilder();
		Document doc = newDocumentBuilder.parse(fileUrl);
		Element documentElement = doc.getDocumentElement();
		System.out.println(documentElement.getNodeName());
		System.out.println(documentElement.getTextContent());
		
		String countryCode = documentElement.getAttribute("code");
		Country country = new Country();
		country.setCode(countryCode);
		Design design = new Design();
		
		NodeList designNodeList = documentElement.getChildNodes();
		for (int i = 0; i < designNodeList.getLength(); i++) {
			Node designNode = designNodeList.item(i);
			if(designNode instanceof Element){
				
				List<FinishProductModel> finishProductModelsList = new ArrayList<>();
				String designCode = ((Element) designNode).getAttribute("code");
				design.setCode(designCode);
				
				NodeList finishProductModelList = designNode.getChildNodes();
				for (int j = 0; j < finishProductModelList.getLength(); j++) {
					Node finishNode = finishProductModelList.item(j);
					if(finishNode instanceof Element){
						
						FinishProductModel finishProductModel = new FinishProductModel();
						String model = ((Element) finishNode).getAttribute("model");
						finishProductModel.setModel(model);
						List<StonesAvailableCharacteristic> stonesAvailableCharacteristicsList = new ArrayList<>();
						
						NodeList stoneNodeList = finishNode.getChildNodes();
						for (int k = 0; k < stoneNodeList.getLength(); k++) {
							Node stoneNode = stoneNodeList.item(k);
							if(stoneNode instanceof Element){
								
								StonesAvailableCharacteristic availableCharacteristic = new StonesAvailableCharacteristic();
								
								NodeList childNodes = stoneNode.getChildNodes();
								for (int l = 0; l < childNodes.getLength(); l++) {
									Node charNode = childNodes.item(l);
									if(charNode instanceof Element){
										
										String textContent = charNode.getTextContent();
										switch (charNode.getNodeName()){
										case "carat":
											availableCharacteristic.setCarat(Double.parseDouble(textContent));
											break;
										case "color":
											availableCharacteristic.setColor(textContent);
											
											break;
										case "clarity":
											availableCharacteristic.setClarity(textContent);
											break;
										case "retailPrice":
											availableCharacteristic.setRetailPrice(Double.parseDouble(textContent));
											break;
										default:
											break;
										}
									}
								}
								stonesAvailableCharacteristicsList.add(availableCharacteristic);
							}
						}
						finishProductModel.setStonesAvailableCharacteristic(stonesAvailableCharacteristicsList);
						finishProductModelsList.add(finishProductModel);
					}
				}
				design.setFinishProductModel(finishProductModelsList);
			}
		}
		country.setDesign(design);
		return country;
	}

}
