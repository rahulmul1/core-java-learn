package com.ims.base.taghandler;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * Tag extra info for controller tag.
 * 
 * @author tsharm
 */
public class ControllerTEI extends TagExtraInfo {

    /** The Constant ATTRIBUTE_CLS. */
    private static final String ATTRIBUTE_CLS = "cls";

    /** The Constant ATTRIBUTE_VAR. */
    private static final String ATTRIBUTE_VAR = "var";

    /** The Constant DEFAULT_TYPE. */
    private static final String DEFAULT_TYPE = "java.lang.Object";

    /**
     * Get variable info for controller tag.
     * 
     * @param data
     *            Tag data
     * @return Variable infos
     */
    @Override
    public VariableInfo[] getVariableInfo(final TagData data) {

        String type = data.getAttributeString(ATTRIBUTE_CLS);
        if (type == null) {
            type = DEFAULT_TYPE;
        }
        return new VariableInfo[] {
                new VariableInfo(data.getAttributeString(ATTRIBUTE_VAR), type, true,
                VariableInfo.AT_BEGIN)
                };
    }

}
