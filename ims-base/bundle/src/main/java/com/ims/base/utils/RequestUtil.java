/*
* ===========================================================================
*
* RequestUtil.java
*
* Created on 16-Aug
*
* Copyright 2013, BT Financial;  All Rights Reserved.
*
* This software is the confidential and proprietary information of
* BT Financial, ("Confidential Information"). You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with BT Financial.
* ===========================================================================
*/

package com.ims.base.utils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMMode;
import com.day.cq.wcm.foundation.forms.FormsHelper;
import com.day.text.Text;

/**
 * @author tsharm
 *
 */
public final class RequestUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);

    private static final String PROTOCOL_SUFFIX = "://";
    private static final String PORT_SEPARATOR = ":";
    private static final String VALUES_DELIMITER = ", ";
    private static final String HEADER_DISPATCHER = "server-agent";
    private static final String IS_DISPATCHER = "Communique-Dispatcher";
    private static final String ENCODING_FORMAT_UTF8 = "UTF-8";
    private static final String SLASH = "/";

    /**
     * Hidden constructor
     */
    private RequestUtil() {
        // NO CONSTRUCTOR
    }

    /**
     * Checks whether we are in edit mode (= authoring functions enabled)
     * @param request The <code>SlingHttpServletRequest</code> upon which to determine the author mode.
     * @return true, if we are in author mode / false, otherwise
     */
    public static boolean isEditMode(SlingHttpServletRequest request) {
        return (WCMMode.fromRequest(request) == WCMMode.EDIT || WCMMode.fromRequest(request) == WCMMode.DESIGN);
    }

    /**
     * Checks whether we are in preview mode (= authoring functions disabled, but switch to author mode possible)
     * @param request The <code>SlingHttpServletRequest</code> upon which to determine the CMS mode.
     * @return true, if we are in preview mode / false, otherwise
     */
    public static boolean isPreviewMode(SlingHttpServletRequest request) {
        return (WCMMode.fromRequest(request) == WCMMode.PREVIEW);
    }

    /**
     * Checks whether we are in publish mode (= authoring functions completely disabled)
     * @param request The <code>SlingHttpServletRequest</code> upon which to determine the CMS mode.
     * @return true, if we are in publish mode / false, otherwise
     */
    public static boolean isPublishMode(SlingHttpServletRequest request) {
        return (WCMMode.fromRequest(request) == WCMMode.DISABLED);
    }

    /**
     * Check if we are going through dispatcher
     * @param request Request object
     * @return True, if going through dispatcher
     */
    public static boolean isDispatcher(SlingHttpServletRequest request) {
        final String dispatcher = request.getHeader(HEADER_DISPATCHER);
        return StringUtils.isNotBlank(dispatcher) && IS_DISPATCHER.equals(dispatcher);
    }

    /**
     * Get a single selector
     * @param request Request object
     * @param selectorIndex Index of selector
     * @param defaultValue Default value
     * @return Selector string or default value
     */
    public static String getSelector(SlingHttpServletRequest request, int selectorIndex,
                                      String defaultValue) {
        String selector = defaultValue;
        final String[] selectors = request.getRequestPathInfo().getSelectors();
        if (selectors.length > selectorIndex) {
            selector = selectors[selectorIndex];
        }
        return selector;
    }

    /**
     * Check if selector set
     * @param request Request object
     * @param selector Selector string
     * @return True, if set
     */
    public static Boolean hasSelector(SlingHttpServletRequest request, String selector) {
        List<String> selectors = Arrays.asList(request.getRequestPathInfo().getSelectors());
        return selectors.contains(selector);
    }

    /**
     * Safely get parameter values as a List of strings
     * @param request Request object
     * @param parameterName Parameter name
     * @return List of parameter values
     */
    public static List<String> getParameterValues(SlingHttpServletRequest request, String parameterName) {
        List<String> values = new ArrayList<String>();
        String[] valuesArr = request.getParameterValues(parameterName);
        if (valuesArr != null) {
            values = Arrays.asList(valuesArr);
        }
        return values;
    }

    public static List<String> getFormParameterNames(SlingHttpServletRequest request) {
        final Iterator<String> names = FormsHelper.getContentRequestParameterNames(request);
        List<String> namesList = new LinkedList<String>();
        CollectionUtils.addAll(namesList, names);

        return namesList;
    }

    public static String getFormParameterNamesAsJsonString(SlingHttpServletRequest request) {

        List<String> formParameterNames = getFormParameterNames(request);
        final Map<String, String> parameterMap = getParameterMap(request, formParameterNames);

        return new JSONObject(parameterMap).toString();
    }

    /**
     * Safely get parameter value as string
     * @param request Request object
     * @param parameterName Parameter name
     * @return value of the parameter
     */
    public static String getParameterValue(SlingHttpServletRequest request, String parameterName) {
        return getParameterValue(request, parameterName, StringUtils.EMPTY);
    }

    /**
     * Safely get parameter value as string
     * @param request Request object
     * @param parameterName Parameter name
     * @param defaultValue Default value
     * @return Value
     */
    public static String getParameterValue(SlingHttpServletRequest request, String parameterName,
                                           String defaultValue) {
        String value = request.getParameter(parameterName);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    /**
     * Safely get parameter value as integer
     * @param request Request object
     * @param parameterName Parameter name
     * @param defaultValue Default value
     * @return Int value
     */
    public static int getParameterIntValue(final SlingHttpServletRequest request, final String parameterName,
                                           final int defaultValue) {
        try {
            final String stringValue = request.getParameter(parameterName);
            if (stringValue != null) {
                return Integer.parseInt(stringValue);
            }
        }
        catch (NumberFormatException e) {
            LOGGER.debug("Could not parse parameter '{}' as integer: {}", parameterName, e.getMessage());
        }
        return defaultValue;
    }

    /**
     * Safely get parameter value as boolean
     * @param request Request object
     * @param parameterName Parameter name
     * @param defaultValue Default value
     * @return Boolean value
     */
    public static boolean getParameterBooleanValue(final SlingHttpServletRequest request,
                                                   final String parameterName, final boolean defaultValue) {
        final String stringValue = request.getParameter(parameterName);
        if (stringValue != null) {
            return "true".equals(stringValue);
        }
        return defaultValue;
    }

    /**
     * Get parameter map (from list of names, and exclude file uploads)
     * @param request Request object
     * @param paramNames Parameter names
     * @return Parameter map
     */
    public static Map<String, String> getParameterMap(SlingHttpServletRequest request, List<String> paramNames) {
        Map<String, String> map = new HashMap<String, String>();
        for (final String name : paramNames) {
            final RequestParameter rp = request.getRequestParameter(name);
            if (rp.isFormField()) {
                final String[] values = request.getParameterValues(name);
                String displayValue = Text.implode(values, VALUES_DELIMITER);
                map.put(name, displayValue);
            }
        }
        return map;
    }

    /**
     * Check if resource actually exists (disregarding permissions)
     * @param request Request object
     * @param sling Sling script helper
     * @return True, if resource exists
     */
    public static boolean resourceExists(SlingHttpServletRequest request, SlingScriptHelper sling) {
        ResourceResolver adminResourceResolver = null;
        try {
            ResourceResolverFactory resourceResolverFactory = sling.getService(ResourceResolverFactory.class);
            adminResourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
            Resource adminResource = adminResourceResolver.resolve(request,
                request.getRequestPathInfo().getResourcePath());
            return !ResourceUtil.isNonExistingResource(adminResource);
        }
        catch (LoginException e) {
            LOGGER.debug("Could not login administrative: ", e.getMessage());
        }
        finally {
            if (adminResourceResolver != null) {
                adminResourceResolver.close();
            }
        }
        return false;
    }

    /**
     * Check if an existing resource is requested (respecting permissions). If not, send Internal Server Error.
     * @param request Request object
     * @param response Response object
     * @return True, if existing resource requested
     */
    public static boolean forceExistingResourceRequest(final SlingHttpServletRequest request,
                                                       final SlingHttpServletResponse response) {
        if (ResourceUtil.isNonExistingResource(request.getResource())) {
            LOGGER.warn("Non-existing resource requested.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return false;
        }
        return true;
    }

    /**
     * Get base URL without path
     * @param request Sling request object
     * @param includeContext Include contex path?
     * @return Base URL string
     */
    public static String getBaseURL(SlingHttpServletRequest request, Boolean includeContext) {
        String baseURL = StringUtils.EMPTY;
        String requestURL = request.getRequestURL().toString();
        try {
            URL url = new URL(requestURL);
            baseURL = url.getProtocol() + PROTOCOL_SUFFIX + url.getHost();
            if (url.getPort() > 0 && url.getPort() != url.getDefaultPort()
                && !url.getHost().contains(PORT_SEPARATOR)) {
            	//sonar issue resolved
                baseURL = baseURL.concat(PORT_SEPARATOR).concat(Integer.toString(url.getPort()));
            }
            if (includeContext && StringUtils.isNotBlank(request.getContextPath())) {
                baseURL += request.getContextPath();
            }
        }
        catch (MalformedURLException e) {
            LOGGER.error("Could not get base URL from '{}': {}", requestURL, e.getMessage());
        }
        return baseURL;
    }

    /**
     * Get secure url from url
     * @param url URL
     * @return Secure URL
     */
    public static String getSecureUrl(final String url) {
        return StringUtils.replace(url, "http://", "https://");
    }

    /**
     * Get base URL without path (but incl. context path)
     * @param request Request object
     * @return Base URL string
     */
    public static String getBaseURL(SlingHttpServletRequest request) {
        return getBaseURL(request, true);
    }

    /**
     * Gets the plain suffix, strips the leading '/'
     * @param request the request
     * @return the plain suffix
     */
    public static String getPlainSuffix(SlingHttpServletRequest request) {
        String suffix = request.getRequestPathInfo().getSuffix();
        if (StringUtils.startsWith(suffix, SLASH)) {
            suffix = StringUtils.substringAfter(suffix, SLASH);
        }
        return suffix;
    }

    /**
     * Get extension from request. Also retrieve the extension from node paths which technically don't have an extension
     * (as in DAM).
     * @param request Sling request object
     * @return Extension string
     */
    public static String getExtension(SlingHttpServletRequest request) {
		if (request.getRequestPathInfo().getExtension() != null) {
            return StringUtils.lowerCase(request.getRequestPathInfo().getExtension());
        }
        else {
            String path = request.getRequestPathInfo().getResourcePath();
            return StringUtils.substringAfterLast(path, ".");
        }
    }

    /**
     * Retrieve the page containing the component from a request is coming.
     * @param request Request object
     * @return Page Page containing component.
     */
    public static Page getCurrentPage(SlingHttpServletRequest request) {
        final PageManager pageManager = request.getResourceResolver().adaptTo(PageManager.class);
        final Resource resource = request.getResource();
        return pageManager.getContainingPage(resource);
    }

    /**
     * Generate a list of query parameters from a key-value map
     * @param queryParameterMap the key-value map
     * @param prependQuestionMark if true the query string will contain a leading quesstion mark
     * @return the URL encoded query string
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     */
    public static String createQueryString(Map<String, Object> queryParameterMap, boolean prependQuestionMark)
        throws UnsupportedEncodingException {
        final String queryParamFormat = "%s=%s";
        StringBuilder sb = new StringBuilder();

        if (queryParameterMap != null && queryParameterMap.size() > 0) {
            int index = 0;
            for (Map.Entry<String, Object> parameter : queryParameterMap.entrySet()) {
                String parameterStringValue = parameter.getValue().toString();
                String queryParameter = String.format(queryParamFormat, parameter.getKey(),
                    URLEncoder.encode(parameterStringValue, ENCODING_FORMAT_UTF8));

                if (index == 0 && prependQuestionMark) {
                    sb.append("?");
                }
                else {
                    sb.append("&");
                }
                sb.append(queryParameter);
                index++;
            }
        }
        return sb.toString();
    }

    /**
     * Get the IP Address from the request
     * @param request SlingHttpServletRequest
     * @return The IP as String
     */
    public static String getIP(SlingHttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null) {
            String[] forwardIps = xForwardedFor.split(",");

            for (final String forwardIp : forwardIps) {
                try {
                    InetAddress clientIp = InetAddress.getByName(forwardIp.trim());
                    return clientIp.toString();
                }
                catch (UnknownHostException uhe) {
                    LOGGER.info("received X-Forwarded-For header containing unparseable value {}, ignoring it",
                        forwardIp.trim());
                }
            }
        }
        return request.getRemoteAddr();
    }
	
}
