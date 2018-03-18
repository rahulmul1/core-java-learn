/**
 * Author: Stephan Kleine
 */

/**
 * @class CQ.form.MultiFieldContainer
 * @extends CQ.form.CompositeField This is a custom widget based on
 *          {@link CQ.form.CompositeField}. Must be child of a multifield
 *          widget. Allows to have multiple widgets per multifield item (e.g.
 *          textfield and linkbrowse). Compare with https://groups.google.com/forum/?fromgroups=#!topic/day-communique/DOjtEz5ncZk
 * 
 * <links jcr:primaryType="cq:Widget" border="{Boolean}true" fieldLabel="linklist" name="./links" xtype="multifield"> 
 * 		<fieldContainer jcr:primaryType="nt:unstructured" xtype="multifieldcontainer">
 * 			<myItems jcr:primaryType="cq:WidgetCollection">
 * 				<firstfield jcr:primaryType="nt:unstructured" fieldLabel="Link" xtype="pathfield"/> 
 * 				<secondfield jcr:primaryType="nt:unstructured" fieldLabel="Linktext" xtype="textfield"/> 
 * 			</myItems> 
 * 		</fieldContainer> 
 * </links>
 * 
 * 
 * @constructor Creates a new MultiFieldContainer.
 * @param {Object}
 *            config The config object
 */
CQ.form.MultiFieldContainer = CQ.Ext.extend(CQ.form.CompositeField, {

	/**
	 * @private
	 * @type CQ.Ext.form.Hidden
	 */
	hiddenField : null,

	/**
	 * @private Array holding child items
	 */
	myObjects : null,

	/**
	 * This separator must be compatible with the reference search, i.e. if there are two links separated by this, CQ should be able to find and adapt those links.
	 * @see com.day.cq.wcm.commons.ReferenceSearch
	 * @private
	 * @type String
	 */
	fieldSeparator : "''''",

	constructor : function(config) {
		config = config || {};
		var defaults = {
			"border" : false,
			"layout" : "form",
			"columns" : 2
		};
		if (config.separator) {
			this.fieldSeparator = config.separator;
		}

		config = CQ.Util.applyDefaults(config, defaults);
		CQ.form.MultiFieldContainer.superclass.constructor.call(this,
				config);
	},

	// overriding CQ.Ext.Component#initComponent
	initComponent : function() {
		CQ.form.MultiFieldContainer.superclass.initComponent.call(this);
		this.hiddenField = new CQ.Ext.form.Hidden({
			name : this.name
		});
		this.add(this.hiddenField);

		this.myObjects = new Array(this.myItems.length);

		for ( var i = 0; i < this.myItems.length; i++) {
			this.myObjects[i] = CQ.Util.build(this.myItems[i]);
			this.add(this.myObjects[i]);
			this.myObjects[i].on("change", this.updateHidden, this);
			// needed for pathfields
			this.myObjects[i].on("dialogselect", this.updateHidden, this);
		}
	},

	// overriding CQ.form.CompositeField#setValue
	setValue : function(value) {
		if (value) {
			var parts = value.split(this.fieldSeparator);
			for ( var i = 0; i < this.myObjects.length; i++) {
				this.myObjects[i].setValue(parts[i + 1]);
			}
			this.updateHidden();
		}
	},

	// overriding CQ.form.CompositeField#getValue
	getValue : function() {
		return this.getRawValue();
	},

	// overriding CQ.form.CompositeField#getRawValue
	getRawValue : function() {
		if (!this.myObjects) {
			return null;
		}
		var value = "";

		for ( var i = 0; i < this.myObjects.length; i++) {
			value += this.myObjects[i].getValue();
			if (i < this.myObjects.length - 1) {
				value += this.fieldSeparator;
			}
		}
		return this.fieldSeparator + value + this.fieldSeparator;
	},

	// private
	updateHidden : function() {
		this.hiddenField.setValue(this.getValue());
	}

});

// register xtype
CQ.Ext.reg('multifieldcontainer', CQ.form.MultiFieldContainer);