package com.ims.base.components;

import java.util.List;

import javax.jcr.Node;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.designer.Style;
import com.ims.base.context.JSPContext;
import com.ims.base.utils.LoggerUtil;

/**
 * The Class AbstractController.
 */
public abstract class AbstractController implements Controller {

	//Path till data node of Page
	/** The Constant PAGE_DATA_NODE_PATH. */
	public static final String PAGE_DATA_NODE_PATH = "jcr:content/data";


	/** The jsp context. */
	private final JSPContext jspContext;

	/**
	 * Controller constructor.
	 * 
	 * @param context
	 *            the context
	 */
	public AbstractController(JSPContext context) {

		this.jspContext = context;
	}

	/**
	 * Instantiates a new abstract controller.
	 */
	public AbstractController() {

		this.jspContext = null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sapient.osgi.component.controller.Controller#init()
	 */
	public void init() {
		LoggerUtil.debugLog(this.getClass(), "AbstractController initialized");
	}

	/**
	 * Get request attribute typed.
	 * 
	 * @param <T>
	 *            Type
	 * @param name
	 *            Attribute name
	 * @param cls
	 *            Class of value
	 * @param clear
	 *            Clear attribute afterwards?
	 * @return Value
	 */
	@SuppressWarnings({ "unchecked" })
	public <T> T getRequestAttribute(final String name, final Class<T> cls, final boolean clear) {

		final Object reqAttrValue = this.getSlingRequest().getAttribute(name);
		final Object reqParmeterAttrValue = this.getSlingRequest().getParameter(name);

		if (reqAttrValue != null) {
			try {
				if (clear) {
					this.getSlingRequest().removeAttribute(name);
				}
				return (T) reqAttrValue;
			} catch (ClassCastException e) {
				LoggerUtil.debugLog(this.getClass(), "Unable to cast for {} to {}.",
						reqAttrValue.getClass(), cls);
			}
		} else if (reqParmeterAttrValue != null) {
			try {
				if (clear) {
					this.getSlingRequest().removeAttribute(name);
				}
				return (T) reqParmeterAttrValue;
			} catch (ClassCastException e) {
				LoggerUtil.debugLog(this.getClass(), "Could not cast {} to {}.",
						reqParmeterAttrValue.getClass(), cls);
			}
		}


		return null;
	}

	/**
	 * Get request attribute typed.
	 * 
	 * @param <T>
	 *            Type
	 * @param name
	 *            Attribute name
	 * @param cls
	 *            Class of value
	 * @param clear
	 *            Clear attribute afterwards?
	 * @param defaultValue
	 *            return value
	 * @return Value
	 */
	public <T> T getRequestAttribute(final String name, final Class<T> cls, final boolean clear,
			T defaultValue) {

		T result = this.getRequestAttribute(name, cls, clear);

		if (result == null) {
			result = defaultValue;
		}

		return result;
	}

	/**
	 * Gets the context.
	 * 
	 * @return the context
	 */
	protected JSPContext getContext() {

		return this.jspContext;
	}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public ValueMap getProperties() {

		return this.jspContext.getProperties();
	}

	/**
	 * Gets the page manager.
	 * 
	 * @return the page manager
	 */
	public PageManager getPageManager() {

		return this.jspContext.getPageManager();
	}

	/**
	 * Gets the page properties.
	 * 
	 * @return the page properties
	 */
	public ValueMap getPageProperties() {

		return this.jspContext.getPageProperties();
	}

	/**
	 * Gets the resource.
	 * 
	 * @return the resource
	 */
	public Resource getResource() {

		return this.jspContext.getResource();
	}

	/**
	 * Gets the resource resolver.
	 * 
	 * @return the resource resolver
	 */
	public ResourceResolver getResourceResolver() {

		return this.jspContext.getResourceResolver();
	}

	/**
	 * Gets the selector.
	 * 
	 * @return the selector
	 */
	public String getSelector() {

		return this.jspContext.getSelector();
	}

	/**
	 * Gets the selectors.
	 * 
	 * @return the selectors
	 */
	public List<String> getSelectors() {

		return this.jspContext.getSelectors();
	}

	/**
	 * Gets the selectors.
	 * 
	 * @return the selectors
	 */
	public String getSelectorString() {

		return this.jspContext.getRequest().getRequestPathInfo().getSelectorString();
	}

	/**
	 * Gets the selectors.
	 * 
	 * @return the selectors
	 */
	public Boolean hasSelector() {

		return this.getSelectors().size() > 0;
	}

	/**
	 * Gets the sling.
	 * 
	 * @return the sling
	 */
	public SlingScriptHelper getSling() {

		return this.jspContext.getSling();
	}

	/**
	 * Gets the suffix.
	 * 
	 * @return the suffix
	 */
	public String getSuffix() {

		return this.jspContext.getSuffix();
	}

	/**
	 * Gets the sling request.
	 * 
	 * @return the sling request
	 */
	public SlingHttpServletRequest getSlingRequest() {

		return this.jspContext.getRequest();
	}

	/**
	 * Gets the sling response.
	 * 
	 * @return the sling response
	 */
	public SlingHttpServletResponse getSlingResponse() {

		return this.jspContext.getResponse();
	}

	/**
	 * Gets the current node.
	 * 
	 * @return the current node
	 */
	public Node getCurrentNode() {

		return this.jspContext.getCurrentNode();
	}

	/**
	 * Gets the current page path.
	 * 
	 * @return the current page path
	 */
	public String getCurrentPagePath() {

		return this.jspContext.getCurrentPagePath();
	}

	/**
	 * Gets the current style.
	 * 
	 * @return the current style
	 */
	public Style getCurrentStyle() {

		return this.jspContext.getCurrentStyle();
	}

	/**
	 * Gets the current page.
	 * 
	 * @return the current page
	 */
	public Page getCurrentPage() {

		return this.jspContext.getCurrentPage();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object getServiceReference(Class interfaceClass){
		Object obj = null;
		if(jspContext!= null){
			SlingBindings bindings = (SlingBindings) jspContext.getRequest().getAttribute("org.apache.sling.api.scripting.SlingBindings");
			obj = bindings.getSling().getService(interfaceClass);	
		}
		if(obj == null){
			LoggerUtil.debugLog(this.getClass(),
					"getServiceReference() Object is null from service");
		}
		return obj;
	}    

}