CQ.form.CustomMultiField = CQ.Ext.extend(CQ.form.CompositeField, {

hiddenField: null,

linkText: null,

linkURL: null,

alt: null,

imagePath: null,

    linkType: null,

    miniCartIcon: null,

    formPanel: null,

    constructor: function (config) {
    config = config || {};
    var defaults = {
    "border": true,
    "labelWidth": 75,
    "layout": "form"
    };
config = CQ.Util.applyDefaults(config, defaults);
CQ.form.CustomMultiField.superclass.constructor.call(this, config);
},

initComponent: function () {

    CQ.form.CustomMultiField.superclass.initComponent.call(this);

    // Hidden field
    this.hiddenField = new CQ.Ext.form.Hidden({
         name: this.name
    });
    this.add(this.hiddenField);

    // Link text
    this.add(new CQ.Ext.form.Label({
    cls: "customwidget-label",

    }));

	 // Add Link Type
    this.linkType = new CQ.form.Selection({
        cls:"customwid-5",
        fieldLabel:"Link Type",
        fieldDescription:"Select the link type.",
        hideLabel:false,
        width:300,
        columns:1,
        name : "linktype",
        xtype:"selection",
        type:"combobox",
        options: [{
                    text: 'Text', 
                    value: "text"
                },{
                    text: 'Image', 
                    value: "image"
                },{
                    text: 'Call', 
                    value: "call"
                },{
                    text: 'Social', 
                    value: "Social"
                }
                 ],
        listeners: {
            afterrender:{
                scope:this,
                 fn:this.updateHidden,
                fn:this.updateDialog

            },
            selectionchanged: {
                scope:this,
                 fn:this.updateHidden,
                 fn:this.updateDialogChange

            }
        }
    });
    this.add(this.linkType);



    this.linkText = new CQ.Ext.form.TextField({
        cls: "customwid-1",
        maxLength: 100,
        fieldLabel: "Title",
        maxLengthText: "A maximum of 100 characters is allowed for the Link Text.",
        width: 300,
        hidden:true,
        name : "item",
        listeners: {
        change: {
        scope: this,
        fn: this.updateHidden
    }
    }
    });
    this.add(this.linkText);


    this.linkURL = new CQ.form.PathField({
        cls: "customwid-2",
        allowBlank: false,
        fieldLabel: "URL",
        width: 300,
        hidden:true,
        name:"linkurl",
        listeners: {
    change: {
    scope: this,
    fn: this.updateHidden
    },
    dialogclose: {
    scope: this,
    fn: this.updateHidden
    }
    }
    });
    this.add(this.linkURL);


  // Link imagePath
   this.imagePath = new CQ.form.PathField({
        cls: "customwid-3",
        allowBlank: false,
       fieldLabel: "Image Path",
        width: 300,
       hidden:true,
       name:"imagepath",
        listeners: {
    change: {
    scope: this,
    fn: this.updateHidden
    },
    dialogclose: {
    scope: this,
    fn: this.updateHidden
    }
    }
    });
    this.add(this.imagePath);

    this.miniCartIcon = new CQ.form.Selection({
       	cls: "customwid-6",
        fieldLabel:"Icon Type",
        fieldDescription:"Select the Icon type.",
        hideLabel:false,
        width:300,
        columns:1,
        xtype:"selection",
        type:"select",
        name:"minicarticon",
        value:"Other",
        hidden:true,
        options: [{
                    text: 'Phone', 
                    value: "Phone"
                },{
                    text: 'Boutique', 
                    value: "Boutique"
                },{
                    text: 'Shopping bag', 
                    value: "Shopping bag"
                },{
                    text: 'Go to desktop site', 
                    value: "Go to desktop site"
                },{
                    text: 'Contact US', 
                    value: "Contact US"
                },{
                    text: 'Other', 
                    value: "Other"
                }],
        listeners: {
            afterrender:{
                scope:this,
                fn:this.updateHidden

            },
            selectionchanged: {
                scope:this,
                fn:this.updateHidden

            }
        }
    });
     this.add(this.miniCartIcon);



// Link alt
this.alt = new CQ.Ext.form.TextField({
        cls: "customwid-4",
       maxLength: 100,
        fieldLabel: "Alt(In case of social sharing)",
        maxLengthText: "A maximum of 100 characters is allowed for the Link Text.",
        width: 300,
        allowBlank: true,
        name : "itemss",
    	hidden:true,
        listeners: {
        change: {
        scope: this,
        fn: this.updateHidden
        },
        check: {
        scope: this,
        fn: this.updateHidden
    }
    }
    });
    this.add(this.alt);
},

    // overriding CQ.form.CompositeField#processPath
    processPath: function(path) {
        this.linkType.processPath(path);
    },

    // overriding CQ.form.CompositeField#processRecord
    processRecord: function(record, path) {
        this.linkType.processRecord(record, path);
    },

processInit: function (path, record) {
    this.linkType.processInit(path,record);
    this.linkText.processInit(path, record);
    this.linkURL.processInit(path, record);
    this.imagePath.processInit(path, record);
	this.miniCartIcon.processInit(path,record);
    this.alt.processInit(path, record);

},

setValue: function (value) {
    var link = JSON.parse(value);
    this.linkText.setValue(link.text);
    this.linkURL.setValue(link.url);
    this.imagePath.setValue(link.imagePath);
	this.miniCartIcon.setValue(link.miniIcon);
    this.alt.setValue(link.alt);
    this.linkType.setValue(link.linktype);
    this.hiddenField.setValue(value);
    this.updateDialog();

},
getValue: function () {
return this.getRawValue();
},

getRawValue: function () {
    var link = {
    "linktype":this.linkType.getValue(),
    "url": this.linkURL.getValue(),
    "text": this.linkText.getValue(),
    "alt": this.alt.getValue() ,
    "imagePath": this.imagePath.getValue(),
	"miniIcon": this.miniCartIcon.getValue()
    };
    return JSON.stringify(link);
},


    updateDialog: function(){

        var linkTypeValue = this.linkType.getValue();

        if(linkTypeValue == 'Text'){
            this.linkText.show();
            this.linkURL.show();
			this.linkURL.allowBlank = false;
            this.imagePath.allowBlank = true;
            this.imagePath.hide();
			this.miniCartIcon.show();
            this.alt.hide();
        }
       if(linkTypeValue == 'Image'){
            this.linkText.show();
            this.linkURL.allowBlank = false;
            this.imagePath.allowBlank = false;
            this.linkURL.show();
            this.imagePath.show();
			this.miniCartIcon.show();
            this.alt.show();

       }
       if(linkTypeValue == 'Call'){
            this.linkText.show();
           this.linkURL.allowBlank = true;
            this.imagePath.allowBlank = true;
            this.linkURL.hide();
            this.imagePath.hide();
			this.miniCartIcon.hide();
            this.alt.hide();

       }
         if(linkTypeValue == 'Social'){
            this.linkText.show();
           this.linkURL.allowBlank = true;
            this.imagePath.allowBlank = true;
            this.linkURL.hide();
            this.imagePath.hide();
			this.miniCartIcon.hide();
            this.alt.hide();

       }
  },

     updateDialogChange: function(){

        var linkTypeValue = this.linkType.getValue();
        if(linkTypeValue == 'Text'){
            this.linkText.show();
            this.linkURL.show();
			this.linkURL.allowBlank = false;
            this.imagePath.allowBlank = true;
            this.imagePath.hide();
			this.miniCartIcon.show();
            this.alt.hide();
             this.linkText.setValue('');
            this.linkURL.setValue('');
			this.linkURL.setValue('');
            this.imagePath.setValue('');
            this.imagePath.setValue('');
			this.miniCartIcon.setValue('');
            this.alt.setValue('');
        }
       if(linkTypeValue == 'Image'){
            this.linkText.show();
            this.linkURL.allowBlank = false;
            this.imagePath.allowBlank = false;
            this.linkURL.show();
            this.imagePath.show();
			this.miniCartIcon.show();
            this.alt.show();
            this.linkText.setValue('');
            this.linkURL.setValue('');
			this.linkURL.setValue('');
            this.imagePath.setValue('');
            this.imagePath.setValue('');
			this.miniCartIcon.setValue('');
            this.alt.setValue('');

       }
       if(linkTypeValue == 'Call'){
            this.linkText.show();
           this.linkURL.allowBlank = true;
            this.imagePath.allowBlank = true;
            this.linkURL.hide();
            this.imagePath.hide();
			this.miniCartIcon.hide();
            this.alt.hide();
            this.linkText.setValue('');
            this.linkURL.setValue('');
			this.linkURL.setValue('');
            this.imagePath.setValue('');
            this.imagePath.setValue('');
			this.miniCartIcon.setValue('');
            this.alt.setValue('');

       }
          if(linkTypeValue == 'Social'){
            this.linkText.show();
           this.linkURL.allowBlank = true;
            this.imagePath.allowBlank = true;
            this.linkURL.hide();
            this.imagePath.hide();
			this.miniCartIcon.hide();
            this.alt.hide();
            this.linkText.setValue('');
            this.linkURL.setValue('');
			this.linkURL.setValue('');
            this.imagePath.setValue('');
            this.imagePath.setValue('');
			this.miniCartIcon.setValue('');
            this.alt.setValue('');

       }
  },


updateHidden: function () {
this.hiddenField.setValue(this.getValue());
}
});



CQ.Ext.reg("CustomMultiField", CQ.form.CustomMultiField);
