/**
 * 
 */
package com.ims.base.components.sfy;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jcr.AccessDeniedException;
import javax.jcr.Binary;
import javax.jcr.InvalidItemStateException;
import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.ReferentialIntegrityException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFactory;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ims.base.bean.sfy.Country;
import com.ims.base.bean.sfy.FinishProductModel;
import com.ims.base.bean.sfy.StonesAvailableCharacteristic;


/**
 * @author ragga7
 *
 */
@Component(name = "com.richemont.cms.car.core.components.setforyou.CreateNodeForSFYService", metatype = true, label = "SFY Node creation Service")
@Service(value = CreateNodeForSFYService.class)
public class CreateNodeForSFYServiceImpl implements CreateNodeForSFYService{
	
	/** The Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(CreateNodeForSFYServiceImpl.class);
	
	/** The JCR Session. */
	private Session jcrSession;
	
	/** The SFY Node. */
	private Node sfyNode;
	
	/** The Country. */
	private String countryName = StringUtils.EMPTY;
	
	/** The design. */
	private String design = StringUtils.EMPTY;

	/** The resolver factory. */
	@Reference
	private ResourceResolverFactory resourceResolverFactory;
	
	/** The resource resolver. */
	private ResourceResolver resourceResolver;
	
	@Reference
	private ParseXMLtoBeanService parseXMLtoBeanService;
	
	public String createNodesForSFY(String XMLPath) {
		String jsonNodePath = StringUtils.EMPTY;
		try {
			Country country = parseXMLtoBeanService.unMarshalingFromFile(XMLPath);
			
			resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
			jcrSession = resourceResolver.adaptTo(Session.class);
			Resource sfyResource = resourceResolver.getResource(SetForYouConstants.SFYNODEPATH.getValue());
			sfyNode = sfyResource.adaptTo(Node.class);
			countryName = country.getCode();
			design = country.getDesign().getCode();
			Node countryNode = createSubNode(sfyNode , countryName);
			Node designNode = createSubNode(countryNode, design );
			String json = createJsonFromBean(country);
			jsonNodePath = writeJsonToFile(json , designNode);
			
		} catch (JAXBException e) {
			LOG.error("JAXBException occured : " + e.getMessage());
		} catch (LoginException e) {
			LOG.error("LoginException occured : " + e.getMessage());
		} catch (RepositoryException e) {
			LOG.error("RepositoryException occured : " + e.getMessage());
		} catch (JSONException e) {
			LOG.error("JSONException occured : " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error("IOException occured : " + e.getMessage());
		}
		return jsonNodePath;
	}
	
	
	private String createJsonFromBean(Country country) throws JSONException{
		
		List<Double> caratList = new ArrayList<Double>();
		List<JSONObject> jsonObjectList = new ArrayList<JSONObject>();
		List<FinishProductModel> finishProductModelList = country.getDesign().getFinishProductModel();
		
		for (FinishProductModel finishProductModel : finishProductModelList) {
			String model = finishProductModel.getModel();
			List<StonesAvailableCharacteristic> stonesAvailableCharacteristicList = finishProductModel.getStonesAvailableCharacteristic();
			for (StonesAvailableCharacteristic stonesAvailableCharacteristic : stonesAvailableCharacteristicList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put(SetForYouConstants.REF.getValue(), model);
				jsonObject.put(SetForYouConstants.PRICE.getValue(), stonesAvailableCharacteristic.getRetailPrice());
				jsonObject.put(SetForYouConstants.CARAT.getValue(), stonesAvailableCharacteristic.getCarat());
				jsonObject.put(SetForYouConstants.COLOR.getValue(), stonesAvailableCharacteristic.getColor());
				jsonObject.put(SetForYouConstants.CLARITY.getValue(), stonesAvailableCharacteristic.getClarity());
				jsonObjectList.add(jsonObject);
				caratList.add(stonesAvailableCharacteristic.getCarat());
			}
		}
		
		Collections.sort(jsonObjectList, new SortSetForYouComparator());
		JSONArray jsonMainArray = new JSONArray(jsonObjectList);
		
		JSONObject jsonMinMaxObject = new JSONObject();
		jsonMinMaxObject.put(SetForYouConstants.MAXCARAT.getValue(), Collections.max(caratList));
		jsonMinMaxObject.put(SetForYouConstants.MINCARAT.getValue(), Collections.min(caratList));
		JSONArray jsonFinalArray = new JSONArray();
		
		jsonFinalArray.put(jsonMainArray);
		jsonFinalArray.put(jsonMinMaxObject);
		
		return jsonFinalArray.toString();
	}
	
	private String writeJsonToFile(String json, Node designNode) throws RepositoryException{
		
			InputStream inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
			ValueFactory valueFactory = jcrSession.getValueFactory();
			Binary contentValue = valueFactory.createBinary(inputStream);
			Node resNode = getFileNode(designNode, contentValue);

			return resNode.getPath();
		
	}


	private Node getFileNode(Node designNode, Binary contentValue)	throws ItemExistsException, PathNotFoundException,NoSuchNodeTypeException, LockException, VersionException,
			ConstraintViolationException, RepositoryException, ValueFormatException, AccessDeniedException,	ReferentialIntegrityException, InvalidItemStateException {
			Node fileNode = null;
			Node resNode = null;
			if(designNode.hasNode(SetForYouConstants.STONECHARACTERISTIC.getValue())){
				String subNodePath = designNode.getPath() + SetForYouConstants.BACKSLASH.getValue() + SetForYouConstants.STONECHARACTERISTIC.getValue(); 
				fileNode = resourceResolver.getResource(subNodePath).adaptTo(Node.class);
			}else{
				fileNode = designNode.addNode(SetForYouConstants.STONECHARACTERISTIC.getValue(), SetForYouConstants.NT_FILE.getValue());
				fileNode.addMixin(SetForYouConstants.MIX_REFERENCEABLE.getValue());
				//jcrSession.save();
			}
			if(fileNode.hasNode(SetForYouConstants.JCR_CONTENT.getValue())){
				String subNodePath = fileNode.getPath() + SetForYouConstants.BACKSLASH.getValue() + SetForYouConstants.JCR_CONTENT.getValue(); 
				resNode =  resourceResolver.getResource(subNodePath).adaptTo(Node.class);
			}else{
				resNode = fileNode.addNode(SetForYouConstants.JCR_CONTENT.getValue(), SetForYouConstants.NT_RESOURCE.getValue());
				resNode.setProperty(SetForYouConstants.JCR_MIMETYPE.getValue(), SetForYouConstants.APPLICATION_JSON.getValue());
				resNode.setProperty(SetForYouConstants.JCR_DATA.getValue(), contentValue);
				Calendar lastModified = Calendar.getInstance();
				lastModified.setTimeInMillis(lastModified.getTimeInMillis());
				resNode.setProperty(SetForYouConstants.JCR_LASTMODIFIED.getValue(), lastModified);
				jcrSession.save();
			}
			return resNode;
	}
	
	
	private Node createSubNode(Node currentNode, String subNodeRelativePath) throws RepositoryException {
		Calendar currentDate = new GregorianCalendar();
		Node subNode = null;
		if(currentNode.hasNode(subNodeRelativePath)){
			String subNodePath = currentNode.getPath() + SetForYouConstants.BACKSLASH.getValue() + subNodeRelativePath; 
			subNode = resourceResolver.getResource(subNodePath).adaptTo(Node.class);
		}else{
			subNode = currentNode.addNode(subNodeRelativePath);
			subNode.setPrimaryType(SetForYouConstants.NT_UNSTRUCTURED.getValue());
			subNode.setProperty(SetForYouConstants.JCR_CREATED.getValue(),currentDate);
			subNode.setProperty(SetForYouConstants.JCR_CREATEDBY.getValue(), SetForYouConstants.ADMIN.getValue());
			jcrSession.save();
		}
		return subNode;
	}
	
}
