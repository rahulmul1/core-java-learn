/**
 * @class CQ.form.LinkFieldWidget
 * @extends CQ.form.CompositeField
 * This is a custom path field with link text and target
 * @param {Object} config the config object
 */
/**
 * @class Ejst.CustomWidget
 * @extends CQ.form.CompositeField This is a custom widget based on
 *          {@link CQ.form.CompositeField}.
 * @constructor Creates a new CustomWidget.
 * @param {Object}
 *            config The config object
 */
CQ.form.LinkFieldWidget = CQ.Ext.extend(CQ.form.CompositeField, {

    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    hiddenField : null,

    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    linkTitle : null,

    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    linkURL : null,

    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    linkText : null,

    /**
     * @private
     * @type CQ.Ext.form.FormPanel
     */
    formPanel : null,
    
    enableText : false,
    enableIcon : false,
    allowBlankTitle : false,
    
    rootPath : null,

    constructor : function(config) {
    	if (config.enableText) {
            this.enableText = true;
        }
        if (config.enableIcon) {
            this.enableIcon = true;
        }
        if (config.allowBlankTitle) {
            this.allowBlankTitle = true;
        }
        if (config.rootPath) {
            this.rootPath = config.rootPath;
        } 
        var defaults = {
            "border" : false,
            "labelWidth" : 75,
            "layout" : "form",
            "rootPath" : "/content"
        // "columns":6
        };
        config = CQ.Util.applyDefaults(config, defaults);
        CQ.form.LinkFieldWidget.superclass.constructor.call(this, config);
    },

    // overriding CQ.Ext.Component#initComponent
    initComponent : function() {
        CQ.form.LinkFieldWidget.superclass.initComponent.call(this);
        
        this.layout = "form"

        // Hidden field
        this.hiddenField = new CQ.Ext.form.Hidden({
            name : this.name
        });
        this.add(this.hiddenField);

        // Link title
        this.linkTitle = new CQ.Ext.form.TextField({
            fieldLabel : "Page Title",
            allowBlank : this.allowBlankTitle,
            anchor: "100%",
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
        });
        this.add(this.linkTitle);

        // Link URL
        this.linkURL = new CQ.form.PathField({
            fieldLabel : "Page Path",
            allowBlank : true,
            rootPath: this.rootPath,
            anchor: "100%",
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                },
                dialogclose : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
        });
       
        this.add(this.linkURL);
        
        // Link text
        this.linkText = new CQ.Ext.form.TextArea({
            fieldLabel : "Link Text",
            anchor : "100%",
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
        });
        if (this.enableText) {
            this.add(this.linkText);
        }

    },

    processInit : function(path, record) {
        this.linkTitle.processInit(path, record);
        this.linkURL.processInit(path, record);
        this.linkText.processInit(path, record);
    },

    setValue : function(value) {
        var link = JSON.parse(value);
        this.linkTitle.setValue(link.title);
        this.linkText.setValue(link.text);
        this.linkURL.setValue(link.url);
        this.hiddenField.setValue(value);
    },

    getValue : function() {
        return this.getRawValue();
    },

    getRawValue : function() {
        var link = {
            "title" : this.linkTitle.getValue(),
            "url" : this.linkURL.getValue(),
            "text": this.linkText.getValue(),
            "rendersize" : "true"
        };
        
        
        return JSON.stringify(link);
    },

    updateHidden : function() {
        this.hiddenField.setValue(this.getValue());
    }
});

CQ.Ext.reg('linkfield', CQ.form.LinkFieldWidget);