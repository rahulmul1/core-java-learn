/**
 * 
 */
package com.ims.base.bean.sfy;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author ragga7
 *
 */

@XmlRootElement(name="country")
@XmlAccessorType(XmlAccessType.FIELD)
public class Country {
	@XmlElement(name = "design")
	private Design design = null;
	
	@XmlAttribute(name="code")
	private String code;
	

	/**
	 * @return the design
	 */
	public Design getDesign() {
		return design;
	}

	/**
	 * @param design the design to set
	 */
	public void setDesign(Design design) {
		this.design = design;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
}
