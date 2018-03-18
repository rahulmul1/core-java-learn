package com.ims.base.components.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ims.base.bean.sfy.Country;
import com.ims.base.bean.sfy.Design;
import com.ims.base.bean.sfy.FinishProductModel;
import com.ims.base.bean.sfy.StonesAvailableCharacteristic;

public class SfySaxParser extends DefaultHandler{

	private Country country;
	private Design design;
	private FinishProductModel finishProductModel;
	private List<FinishProductModel> finishProductModelList;
	private StonesAvailableCharacteristic availableCharacteristic ;
	private List<StonesAvailableCharacteristic> availableCharacteristicsList ;
	
	public Country getCountry() {
		return country;
	}
	
	Boolean bcarat = false;
	Boolean bcolor = false;
	Boolean bclarity = false;
	Boolean bretailPrice = false;
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("country")) {
			country = new Country();
			country.setCode(attributes.getValue("code"));
			
		} else if (qName.equalsIgnoreCase("design")) {
			design = new Design();
			design.setCode(attributes.getValue("code"));
			finishProductModelList = new ArrayList<>();
			
		} else if (qName.equalsIgnoreCase("finishProductModel")) {
			finishProductModel = new FinishProductModel();
			finishProductModel.setModel(attributes.getValue("model"));
			availableCharacteristicsList = new ArrayList<>();
			
		} else if (qName.equalsIgnoreCase("stonesAvailableCharacteristic")) {
			availableCharacteristic = new StonesAvailableCharacteristic();
			
		} else if (qName.equalsIgnoreCase("carat")) {
			bcarat = true;
			
		} else if (qName.equalsIgnoreCase("color")) {
			bcolor = true;
			
		} else if (qName.equalsIgnoreCase("clarity")) {
			bclarity = true;
			
		} else if(qName.equalsIgnoreCase("retailPrice")) {
			bretailPrice = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (qName.equalsIgnoreCase("stonesAvailableCharacteristic")) {
			availableCharacteristicsList.add(availableCharacteristic);
			
		}else if(qName.equalsIgnoreCase("finishProductModel")){
			finishProductModel.setStonesAvailableCharacteristic(availableCharacteristicsList);
			finishProductModelList.add(finishProductModel);
			
		}else if (qName.equalsIgnoreCase("design")) {
			design.setFinishProductModel(finishProductModelList);
			
		}else if(qName.equalsIgnoreCase("country")){
			country.setDesign(design);
		}
	}

	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (bcarat) {
			availableCharacteristic.setCarat(Double.parseDouble(new String(ch, start, length)));
			bcarat = false;
			
		} else if (bcolor) {
			availableCharacteristic.setColor(new String(ch, start, length));
			bcolor = false;
			
		} else if (bclarity) {
			availableCharacteristic.setClarity(new String(ch, start, length));
			bclarity = false;
			
		} else if (bretailPrice) {
			availableCharacteristic.setRetailPrice(Double.parseDouble(new String(ch, start, length)));
			bretailPrice = false;
		}
	}

}
