package com.ims.base.components.sfy;


/**
 * @author ragga7
 *
 */
public enum SetForYouConstants {
	
	NT_UNSTRUCTURED("nt:unstructured"),
	BACKSLASH("/"),
	JCR_CREATEDBY("jcr:createdBy"),
	JCR_CREATED("jcr:created"),
	ADMIN("admin"),
	SFYNODEPATH("/content/dam/ims_sfy"),
	REF("ref"),
	PRICE("price"),
	CARAT("carat"),
	COLOR("color"),
	CLARITY("clarity"),
	MINCARAT("mincarat"),
	MAXCARAT("maxcarat"),
	APPLICATION_JSON("application/json"),
	JCR_MIMETYPE("jcr:mimeType"),
	JCR_DATA("jcr:data"),
	JCR_LASTMODIFIED("jcr:lastModified"),
	NT_RESOURCE("nt:resource"),
	JCR_CONTENT("jcr:content"),
	MIX_REFERENCEABLE("mix:referenceable"),
	NT_FILE("nt:file"),
	STONECHARACTERISTIC("StonesCharacteristic.json"),
	DEFAULTCOUNTRYCODE("defaultCountryCode"),
	GET("GET"),
	ACCEPT("Accept"),
	APPLICATION_XML("application/xml"),
	
	;
	
	
	private final String value;
	
	/**
     * Custom Constructor.
     * 
     * @param value
     *            the value
     */
	private SetForYouConstants(String value){
		this.value = value;
	}
	
	
	/**
     * Gets the value.
     * 
     * @return the value.
     */
	public String getValue(){
		return this.value;
	}

}
