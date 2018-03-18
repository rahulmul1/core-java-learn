package com.ims.base.context;

import static com.day.cq.wcm.tags.DefineObjectsTag.DEFAULT_COMPONENT_CONTEXT_NAME;
import static com.day.cq.wcm.tags.DefineObjectsTag.DEFAULT_COMPONENT_NAME;
import static com.day.cq.wcm.tags.DefineObjectsTag.DEFAULT_CURRENT_DESIGN_NAME;
import static com.day.cq.wcm.tags.DefineObjectsTag.DEFAULT_CURRENT_PAGE_NAME;
import static com.day.cq.wcm.tags.DefineObjectsTag.DEFAULT_CURRENT_STYLE_NAME;
import static com.day.cq.wcm.tags.DefineObjectsTag.DEFAULT_DESIGNER_NAME;
import static com.day.cq.wcm.tags.DefineObjectsTag.DEFAULT_EDIT_CONTEXT_NAME;
import static com.day.cq.wcm.tags.DefineObjectsTag.DEFAULT_PAGE_PROPERTIES_NAME;
import static com.day.cq.wcm.tags.DefineObjectsTag.DEFAULT_PROPERTIES_NAME;
import static org.apache.sling.scripting.jsp.taglib.DefineObjectsTag.DEFAULT_SLING_NAME;

import java.util.ArrayList;

import javax.servlet.jsp.PageContext;

import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.scripting.jsp.util.TagUtil;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.api.components.ComponentContext;
import com.day.cq.wcm.api.components.EditContext;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Designer;
import com.day.cq.wcm.api.designer.Style;
import com.ims.base.utils.RequestCache;


/**
 * @author tsharm
 *
 */
public class JSPContext extends AbstractContext {

    private final PageContext pageContext;
    private final RequestCache rc;

    /**
     * Creates the JSP context using the provided PageContext
     * @param pageContext Page context
     */
    public JSPContext(PageContext pageContext) {
        super(TagUtil.getRequest(pageContext), TagUtil.getResponse(pageContext));
        this.pageContext = pageContext;
        this.rc = RequestCache.getInstance(pageContext);
    }

    public Component getComponent() {
        return (Component) pageContext.getAttribute(DEFAULT_COMPONENT_NAME);
    }

    public ComponentContext getComponentContext() {
        return (ComponentContext) pageContext.getAttribute(DEFAULT_COMPONENT_CONTEXT_NAME);
    }

    public Design getCurrentDesign() {
        return (Design) pageContext.getAttribute(DEFAULT_CURRENT_DESIGN_NAME);
    }

    @Override
    public Page getCurrentPage() {
        return (Page) pageContext.getAttribute(DEFAULT_CURRENT_PAGE_NAME);
    }

    public String getCurrentPagePath() {
        return rc.getCurrentPagePath();
    }

    public Style getCurrentStyle() {
        return (Style) pageContext.getAttribute(DEFAULT_CURRENT_STYLE_NAME);
    }

    public Designer getDesigner() {
        return (Designer) pageContext.getAttribute(DEFAULT_DESIGNER_NAME);
    }

    public String getDesignPath() {
        return rc.getDesignPath();
    }

    public EditContext getEditContext() {
        return (EditContext) pageContext.getAttribute(DEFAULT_EDIT_CONTEXT_NAME);
    }

    public PageContext getPageContext() {
        return pageContext;
    }

    public ValueMap getPageProperties() {
        return (ValueMap) pageContext.getAttribute(DEFAULT_PAGE_PROPERTIES_NAME);
    }

    public ValueMap getProperties() {
        return (ValueMap) pageContext.getAttribute(DEFAULT_PROPERTIES_NAME);
    }

    public String getSelector() {
        return rc.getFirstSelector();
    }

    public ArrayList<String> getSelectors() {
        return rc.getSelectors();
    }

    public String getSelf() {
        return rc.getSelf();
    }

    public SlingScriptHelper getSling() {
        return (SlingScriptHelper) pageContext.getAttribute(DEFAULT_SLING_NAME);
    }

    public String getSuffix() {
        return rc.getSuffix();
    }
	
}
