/**
 * 
 */
package com.ims.base.bean.sfy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ragga7
 *
 */
@XmlRootElement(name="stonesAvailableCharacteristic")
@XmlAccessorType(XmlAccessType.FIELD)
public class StonesAvailableCharacteristic implements Comparable<StonesAvailableCharacteristic>{
	
	private double carat;
	private String color;
	private String clarity;
	private double retailPrice;
	
	/**
	 * @return the carat
	 */
	public double getCarat() {
		return carat;
	}
	/**
	 * @param carat the carat to set
	 */
	public void setCarat(double carat) {
		this.carat = carat;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the clarity
	 */
	public String getClarity() {
		return clarity;
	}
	/**
	 * @param clarity the clarity to set
	 */
	public void setClarity(String clarity) {
		this.clarity = clarity;
	}
	/**
	 * @return the retailPrice
	 */
	public double getRetailPrice() {
		return retailPrice;
	}
	/**
	 * @param retailPrice the retailPrice to set
	 */
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	/**
	 * @param o the comparable object
	 */
	public int compareTo(StonesAvailableCharacteristic o) {
		return this.retailPrice < o.retailPrice ? -1 
				: this.retailPrice > o.retailPrice ? 1 
						: 0 ;
	}
	
}
