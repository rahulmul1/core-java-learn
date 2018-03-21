package com.ims.base.components.sfy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;
import javax.servlet.ServletException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ragga7
 *
 */
@SlingServlet(paths = { "/bin/cartier/SfyStep2Servlet.json" }, metatype = true, methods = { "GET,POST" })
public class SfyStep2Servlet extends SlingAllMethodsServlet{

	/**
	 * serialVersionUID id
	 */
	private static final long serialVersionUID = -8424555133070301101L;
	
	
	@Reference
	private CreateNodeForSFYService createNodeForSFYService;
	
	/** The Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(SfyStep2Servlet.class);
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) 
			throws ServletException , IOException {
		
		String jsonString = StringUtils.EMPTY;
		Node stoneCharacteristicJsonNode = null;
		String[] selectors = request.getRequestPathInfo().getSelectors();
		String design = getDesignFromSelectors(selectors);
		String countryCode = getCountryCode(request);
		
		try {
			String stoneCharacteristicJsonPath = SetForYouConstants.SFYNODEPATH.getValue() + SetForYouConstants.BACKSLASH.getValue() + 
				countryCode + SetForYouConstants.BACKSLASH.getValue() +	design  + SetForYouConstants.BACKSLASH.getValue() +
				SetForYouConstants.STONECHARACTERISTIC.getValue() + SetForYouConstants.BACKSLASH.getValue() + SetForYouConstants.JCR_CONTENT.getValue();
			
			Resource jsonNodeResource = request.getResourceResolver().getResource(stoneCharacteristicJsonPath);
			if(jsonNodeResource != null){
				stoneCharacteristicJsonNode = jsonNodeResource.adaptTo(Node.class);
			}else{
				stoneCharacteristicJsonNode = getJsonNodeFromService(countryCode, design, request);
			}
			
			InputStream inputStream = stoneCharacteristicJsonNode.getProperty(SetForYouConstants.JCR_DATA.getValue()).getBinary().getStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(inputStream, writer, (StandardCharsets.UTF_8).toString());
			jsonString = writer.toString();
			generateJSON(response, jsonString);
		
		} catch (ValueFormatException e) {
			LOG.error("ValueFormatException occured : " + e.getMessage());
		} catch (PathNotFoundException e) {
			LOG.error("PathNotFoundException occured : " + e.getMessage());
		} catch (RepositoryException e) {
			LOG.error("RepositoryException occured : " + e.getMessage());
		}
	}
	
	private Node getJsonNodeFromService(String countryCode , String design , SlingHttpServletRequest request){
		/**this line will be used instead of the line below it*/
		//String sfyxMlFileLocation = setForYouService.getSFYXMlFileLocation(countryCode, design, request.getResource());
		ClassLoader classLoader = new SfyStep2Servlet().getClass().getClassLoader();
		String sfyxMlFileLocation = classLoader.getResource("files/AU_DESIGN_1.xml").getFile();
		String jsonNodePath = createNodeForSFYService.createNodesForSFY(sfyxMlFileLocation);
		return request.getResourceResolver().getResource(jsonNodePath).adaptTo(Node.class);
	}
	
	
	/**
	 * @param request
	 */
	private String getCountryCode(SlingHttpServletRequest request) {
		//PageManager pageManager = request.getResourceResolver().adaptTo(PageManager.class);
		//Page currentPage = pageManager.getContainingPage(request.getResource());
		String countryCode = StringUtils.EMPTY;
		
		//countryCode = ConfigurationUtils.getZoneConfigurationMap(currentPage).get(SetForYouConstants.DEFAULTCOUNTRYCODE.getValue()).toString();
		countryCode = "US";
		return countryCode;
	}
	
	
	private String getDesignFromSelectors(String[] selectors) {
		return "DESIGN_1";
	}

	protected final void generateJSON(final SlingHttpServletResponse response, final String jsonString)
            throws IOException {
        response.setCharacterEncoding((StandardCharsets.UTF_8).toString());
        response.setContentType(SetForYouConstants.APPLICATION_JSON.getValue());
        final PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonString);
    }
	
	

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		doPost(request, response);
	}
	
}
