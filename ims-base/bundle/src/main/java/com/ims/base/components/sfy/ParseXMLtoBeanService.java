package com.ims.base.components.sfy;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.ims.base.bean.sfy.Country;



/**
 * @author ragga7
 *
 */
public interface ParseXMLtoBeanService {
	

	Country unMarshaling(String fileUrl) throws JAXBException, IOException;
	Country unMarshalingFromFile(String fileUrl) throws JAXBException, IOException;
	void marshaling(Country country, String fileUrl) throws JAXBException;
}
