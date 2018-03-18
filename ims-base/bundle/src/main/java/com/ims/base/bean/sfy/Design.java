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


@XmlRootElement(name="design")
@XmlAccessorType(XmlAccessType.FIELD)
public class Design {
	@XmlElement(name = "finishProductModel")
	private List<FinishProductModel> finishProductModel = null;
	
	@XmlAttribute(name="code")
	private String code;
	

	/**
	 * @return the finishProductModel
	 */
	public List<FinishProductModel> getFinishProductModel() {
		return finishProductModel;
	}

	/**
	 * @param finishProductModel the finishProductModel to set
	 */
	public void setFinishProductModel(List<FinishProductModel> finishProductModel) {
		this.finishProductModel = finishProductModel;
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
