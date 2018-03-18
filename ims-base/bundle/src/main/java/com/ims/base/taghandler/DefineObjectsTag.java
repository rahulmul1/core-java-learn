package com.ims.base.taghandler;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * Defines page context attributes useful for scripting.
 * 
 * @author tsharm
 */
public class DefineObjectsTag extends TagSupport {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3792834926802852707L;

    /** Attribute Language. */
    public static final String ATTR_LANGUAGE = "lang";

    /** Attribute locale. */
    public static final String ATTR_LOCALE = "locale";

    /** Attribute i18n. */
    public static final String ATTR_I18N = "i18n";

    /** Attribute selector. */
    public static final String ATTR_SELECTOR = "selector";

    /** Attribute suffix. */
    public static final String ATTR_SUFFIX = "suffix";

    /** Attribute designPath. */
    public static final String ATTR_DESIGNPATH = "designPath";

    /** Attribute currentPagePath. */
    public static final String ATTR_CURRENTPAGEPATH = "currentPagePath";

    /** Attribute languagePage. */
    public static final String ATTR_LANGUAGEPAGE = "languagePage";

    /** Attribute branchPage. */
    public static final String ATTR_BRANCHPAGE = "branchPage";

    /** Attribute self. */
    public static final String ATTR_SELF = "self";

    /** Attribute isEditMode. */
    public static final String ATTR_ISEDITMODE = "isEditMode";

    /** Attribute isPreviewMode. */
    public static final String ATTR_ISPREVIEWMODE = "isPreviewMode";

    /** Attribute isPublishMode. */
    public static final String ATTR_ISPUBLISHMODE = "isPublishMode";

    /** The Constant DEFAULT_CURRENT_PAGE_NAME. */
    public static final String DEFAULT_CURRENT_PAGE_NAME = "currentPage";

    /** The Constant DEFAULT_CURRENT_DESIGN_NAME. */
    public static final String DEFAULT_CURRENT_DESIGN_NAME = "currentDesign";

    /**
     * Create Scripting variables for JSPs.
     * 
     * @return always EVAL_PAGE
     */
    @Override
    public final int doEndTag() {
        return EVAL_PAGE;
    }
}
