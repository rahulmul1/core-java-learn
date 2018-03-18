package com.ims.base.taghandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

import com.day.cq.wcm.api.Page;

/**
 * Tag extra info of the {@link DefineObjectsTag}.
 * 
 * @author tsharm
 */
public class DefineObjectsTEI extends TagExtraInfo {

    /**
     * Get variable infos.
     * 
     * @param data
     *            Tag data
     * @return Variable infos
     */
    @Override
    public final VariableInfo[] getVariableInfo(final TagData data) {

        final List<VariableInfo> varInfos = new ArrayList<VariableInfo>();

        this.addVar(varInfos, DefineObjectsTag.ATTR_LANGUAGE, String.class.getName());
        this.addVar(varInfos, DefineObjectsTag.ATTR_LOCALE, Locale.class.getName());
        this.addVar(varInfos, DefineObjectsTag.ATTR_I18N, ResourceBundle.class.getName());
        this.addVar(varInfos, DefineObjectsTag.ATTR_SELECTOR, String.class.getName());
        this.addVar(varInfos, DefineObjectsTag.ATTR_SUFFIX, String.class.getName());
        this.addVar(varInfos, DefineObjectsTag.ATTR_DESIGNPATH, String.class.getName());
        this.addVar(varInfos, DefineObjectsTag.ATTR_CURRENTPAGEPATH, String.class.getName());
        this.addVar(varInfos, DefineObjectsTag.ATTR_SELF, String.class.getName());
        this.addVar(varInfos, DefineObjectsTag.ATTR_LANGUAGEPAGE, Page.class.getName());
        this.addVar(varInfos, DefineObjectsTag.ATTR_BRANCHPAGE, Page.class.getName());
        this.addVar(varInfos, DefineObjectsTag.ATTR_ISEDITMODE, Boolean.class.getName());
        this.addVar(varInfos, DefineObjectsTag.ATTR_ISPREVIEWMODE, Boolean.class.getName());
        this.addVar(varInfos, DefineObjectsTag.ATTR_ISPUBLISHMODE, Boolean.class.getName());

        return varInfos.toArray(new VariableInfo[varInfos.size()]);
    }

    /**
     * Add variable.
     * 
     * @param varInfos
     *            Variable infos object
     * @param varName
     *            Variable name
     * @param varClass
     *            Variable class
     */
    private void addVar(final List<VariableInfo> varInfos, final String varName, final String varClass) {
        varInfos.add(new VariableInfo(varName, varClass, true, VariableInfo.AT_END));
    }
}
