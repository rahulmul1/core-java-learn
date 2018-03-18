/**
 * 
 */
package com.ims.base.components.sfy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import com.ims.base.bean.sfy.Country;

/**
 * @author ragga7
 *
 */
@Component(name = "com.richemont.cms.car.core.components.setforyou.ParseXMLtoBeanService", metatype = true, label = "XML to bean Service")
@Service()
public class ParseXMLtoBeanServiceImpl implements ParseXMLtoBeanService {
	

	public Country unMarshaling(String fileUrl) throws JAXBException, IOException {
		URL url = new URL(fileUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(SetForYouConstants.GET.getValue());
		connection.setRequestProperty(SetForYouConstants.ACCEPT.getValue(), SetForYouConstants.APPLICATION_XML.getValue());
		InputStream xml = connection.getInputStream();
	    JAXBContext jaxbContext = JAXBContext.newInstance(Country.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    Country country = (Country) jaxbUnmarshaller.unmarshal(xml);
	    connection.disconnect();
	    return country;
	}
	
	
	public Country unMarshalingFromFile(String fileUrl) throws JAXBException, IOException {
		File xml = new File(fileUrl);
	    JAXBContext jaxbContext = JAXBContext.newInstance(Country.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    Country country = (Country) jaxbUnmarshaller.unmarshal(xml);
	    return country;
	}

	
	public void marshaling(Country country,String fileUrl) throws JAXBException {
		 JAXBContext jaxbContext = JAXBContext.newInstance(Country.class);
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    jaxbMarshaller.marshal(country, System.out);
		    jaxbMarshaller.marshal(country, new File(fileUrl));
		
	}
}
