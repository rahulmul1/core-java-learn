package com.ims.base.utils;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.jsp.PageContext;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.scripting.jsp.util.TagUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.tags.DefineObjectsTag;
//import com.day.cq.wcm.api.designer.Design;

public class RequestCache {
	
	public RequestCache() {

	}

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestCache.class);

    private SlingHttpServletRequest slingRequest = null;
    private PageContext pageContext = null;

	private String designPath = null;
    private String currentPagePath = null;
    private String firstSelector = null;
    private ArrayList<String> selectors = null;
    private String suffix = null;
    private String self = null;

    /**
     * Private constructor (singleton)
     * @param slingRequest Sling request
     */
    private RequestCache(SlingHttpServletRequest slingRequest) {
        this.slingRequest = slingRequest;
        initCached();
    }

    /**
     * Private constructor (singleton)
     * @param pageContext Page context
     */
    private RequestCache(PageContext pageContext) {
        this.pageContext = pageContext;
        this.slingRequest = TagUtil.getRequest(pageContext);
        initCached();
    }

    /**
     * Get singleton instance (cached in request)
     * @param slingRequest Sling request
     * @param pageContext Page context
     * @return Cached RequestData object
     */
    private static RequestCache getInstance(SlingHttpServletRequest slingRequest, PageContext pageContext) {
        LOGGER.trace("Retrieving RequestCache instance...");
        RequestCache instance = (RequestCache) slingRequest.getAttribute(RequestCache.class.getName());
        if (instance == null) {
            LOGGER.trace("Doesn't exist yet. Creating new...");
            if (pageContext != null) {
                instance = new RequestCache(pageContext);
            }
            else {
                instance = new RequestCache(slingRequest);
            }
            slingRequest.setAttribute(RequestCache.class.getName(), instance);
        }
        return instance;
    }

    /**
     * Get singleton instance (cached in request)
     * @param slingRequest Sling request
     * @return Cached RequestData object
     */
    public static RequestCache getInstance(SlingHttpServletRequest slingRequest) {
        return getInstance(slingRequest, null);
    }

    /**
     * Get singleton instance (cached in request)
     * @param pageContext Page context
     * @return Cached RequestData object
     */
    public static RequestCache getInstance(PageContext pageContext) {
        return getInstance(TagUtil.getRequest(pageContext), pageContext);
    }

    /**
     * Initialize objects (cached)
     */
    private void initCached() {

        // get objects from pageContext if exists
        if (pageContext != null) {
            Page currentPage = (Page) pageContext.getAttribute(DefineObjectsTag.DEFAULT_CURRENT_PAGE_NAME);
          //  Design currentDesign = (Design) pageContext.getAttribute(DefineObjectsTag.DEFAULT_CURRENT_DESIGN_NAME);

            /*// design path
            if (currentDesign != null) {
//                designPath = navigationService.getLink(pageContext, currentDesign.getPath(), 
//                		null, null, null);
            }*/

            // current page path
            if (currentPage != null) {
                currentPagePath = currentPage.getPath();
            }
        }

        // first selector
        firstSelector = RequestUtil.getSelector(slingRequest, 0, null);

        // all selectors
        selectors = new ArrayList<String>(Arrays.asList(slingRequest.getRequestPathInfo().getSelectors()));

        // suffix
        suffix = slingRequest.getRequestPathInfo().getSuffix();
    }

    // GETTERS BELOW

    public String getFirstSelector() {
        return firstSelector;
    }

    public ArrayList<String> getSelectors() {
        return selectors;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getDesignPath() {
        return designPath;
    }

    public String getCurrentPagePath() {
        return currentPagePath;
    }

    public String getSelf() {
        return self;
    }
	
}
