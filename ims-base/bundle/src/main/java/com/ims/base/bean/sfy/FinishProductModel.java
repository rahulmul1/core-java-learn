/**
 * 
 */
package com.ims.base.bean.sfy;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author ragga7
 *
 */

@XmlRootElement(name="finishProductModel")
@XmlAccessorType(XmlAccessType.FIELD)
public class FinishProductModel {
	@XmlElement(name = "stonesAvailableCharacteristic")
	private List<StonesAvailableCharacteristic> stonesAvailableCharacteristic = null;

	@XmlAttribute(name="model")
	private String model;
	
	/**
	 * @return the stonesAvailableCharacteristic
	 */
	public List<StonesAvailableCharacteristic> getStonesAvailableCharacteristic() {
		return stonesAvailableCharacteristic;
	}

	/**
	 * @param stonesAvailableCharacteristic the stonesAvailableCharacteristic to set
	 */
	public void setStonesAvailableCharacteristic(
			List<StonesAvailableCharacteristic> stonesAvailableCharacteristic) {
		this.stonesAvailableCharacteristic = stonesAvailableCharacteristic;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	
}
