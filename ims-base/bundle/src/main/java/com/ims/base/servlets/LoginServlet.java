/**
 * 
 */
package com.ims.base.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import com.ims.base.constants.ApplicationConstants;

/**
 * @author NK
 *
 */
@Component(label = "Login Servlet", description = "Servlet for login", metatype = false, immediate = true)
@SlingServlet(
	        resourceTypes = "/apps/ims/base/components/forms/formstart",
	        generateComponent = false,
	        selectors = "login",
	        extensions = "html",
	        methods = { "GET,POST" }
        	)
public class LoginServlet extends SlingAllMethodsServlet{

	/**
	 *Serial iD 
	 */
	private static final long serialVersionUID = -6693335817107720551L;
	
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		String userId = StringUtils.EMPTY;
		String pwd =  StringUtils.EMPTY;
		
		Enumeration<String> parameterNames = request.getParameterNames();
		
		while (parameterNames.hasMoreElements()) {
			String paramName = (String) parameterNames.nextElement();
			if(paramName.equals(ApplicationConstants.USER_ID.getValue()))
				userId = request.getParameter(paramName);
			if (paramName.equals(ApplicationConstants.PWD.getValue())) {
				pwd = request.getParameter(paramName);
			}
			
		}
	}
}
