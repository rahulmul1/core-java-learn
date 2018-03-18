package com.ims.base.context;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

/**
 * The Interface Context.
 * 
 * @author tsharm
 */
public interface Context {

    /**
     * Get request object.
     * 
     * @return Request object
     */
    SlingHttpServletRequest getRequest();

    /**
     * Get response object.
     * 
     * @return Response object
     */
    SlingHttpServletResponse getResponse();

    /**
     * Get resource.
     * 
     * @return Resource
     */
    Resource getResource();

    /**
     * Get resource resolver.
     * 
     * @return Resource resolver
     */
    ResourceResolver getResourceResolver();

}
