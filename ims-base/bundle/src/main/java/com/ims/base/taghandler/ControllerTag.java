package com.ims.base.taghandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.commons.classloader.DynamicClassLoaderManager;

import com.ims.base.components.Controller;
import com.ims.base.context.JSPContext;



/**
 * The Class ControllerTag.
 * 
 * @author tsharm
 */
public class ControllerTag extends TagSupport {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1286294351451379953L;

    /** The pc. */
    private transient PageContext pc;

    /** The parent. */
    private transient Tag parent;

    /** The cls. */
    private String cls;

    /** The var. */
    private String var;

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
     */
    @Override
    public final int doStartTag() throws JspException {
        try {
            if (this.cls != null && this.var != null) {
                final JSPContext jspContext = new JSPContext(this.pc);
                final DynamicClassLoaderManager dclm = jspContext.getSling()
                        .getService(DynamicClassLoaderManager.class);
                final ClassLoader dynamicClassLoader = dclm.getDynamicClassLoader();
                final Class<?> cl = dynamicClassLoader.loadClass(this.cls);
                final Constructor<?> co = cl.getConstructor(JSPContext.class);
                final Controller ctrl = (Controller) co.newInstance(jspContext);
                this.injectOSGiServices(ctrl, dynamicClassLoader, jspContext.getSling());
                ctrl.init();
                this.pc.setAttribute(this.var, ctrl);
            }
        } catch (final ClassNotFoundException e) {
            throw new JspTagException("ClassNotFoundException while loading controller '" + this.cls, e);
        } catch (final NoSuchMethodException e) {
            throw new JspTagException("NoSuchMethodException while loading controller '" + this.cls, e);
        } catch (final InstantiationException e) {
            throw new JspTagException("InstantiationException while loading controller '" + this.cls, e);
        } catch (final IllegalAccessException e) {
            throw new JspTagException("IllegalAccessException while loading controller '" + this.cls, e);
        } catch (final InvocationTargetException e) {
            throw new JspTagException("InvocationTargetException while loading controller '" + this.cls, e);
        }
        return Tag.SKIP_BODY;
    }

    /**
     * Inject OSGi services into @Reference annotated fields<br/>
     * Emulates the Felix @Reference annotation.
     * 
     * @param obj
     *            Object to inject into
     * @param dynamicClassLoader
     *            Dynamic class loader
     * @param sling
     *            Sling script helper
     * @throws JspTagException
     *             the jsp tag exception
     */
    private void injectOSGiServices(final Object obj, final ClassLoader dynamicClassLoader,
            final SlingScriptHelper sling) throws JspTagException {

        final Field[] fields = obj.getClass().getDeclaredFields();
        for (final Field field : fields) {
            if (field.isAnnotationPresent(Reference.class)) {
                try {
                    final Class<?> aClass = dynamicClassLoader.loadClass(field.getType().getName());
                    field.set(obj, sling.getService(aClass));
                } catch (final IllegalAccessException e) {
                    throw new JspTagException("Could not inject OSGi reference for " + field.getType(), e);
                } catch (final ClassNotFoundException e) {
                    throw new JspTagException("Could not inject OSGi reference for " + field.getType(), e);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public final int doEndTag() throws JspException {
        return Tag.EVAL_PAGE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#setPageContext(javax.servlet.jsp. PageContext)
     */
    @Override
    public final void setPageContext(final PageContext p) {
        this.pc = p;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#setParent(javax.servlet.jsp.tagext .Tag)
     */
    @Override
    public final void setParent(final Tag t) {
        this.parent = t;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#getParent()
     */
    @Override
    public final Tag getParent() {
        return this.parent;
    }

    /**
     * Sets the cls.
     * 
     * @param s
     *            the new cls
     */
    public final void setCls(final String s) {
        this.cls = s;
    }

    /**
     * Gets the cls.
     * 
     * @return the cls
     */
    public final String getCls() {
        return this.cls;
    }

    /**
     * Gets the var.
     * 
     * @return the var
     */
    public final String getVar() {
        return this.var;
    }

    /**
     * Sets the var.
     * 
     * @param var
     *            the new var
     */
    public final void setVar(final String var) {
        this.var = var;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#release()
     */
    @Override
    public final void release() {
        this.pc = null;
        this.parent = null;
        this.cls = null;
        this.var = null;
    }
}
