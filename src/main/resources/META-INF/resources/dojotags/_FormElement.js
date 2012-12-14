define([ "dojo/_base/declare", "./_Widget" ], function(declare, _Widget) {
	return declare("dojotags._FormElement", [ _Widget ], {

		/**
		 * Nearest ancestor Form widget.
		 * 
		 * @type Form
		 */
		form : null,

		/**
		 * Name of the model attribute of the ancestor Form widget to which this
		 * widget's "value" model attribute is bound.
		 * 
		 * @type String
		 */
		path : null,

		initialize : function(args) {
			this.inherited(arguments);
			this.model.set("value", args.value || "");
			// register a handle to the ancestor form widget
			var form = this.form = this.findAncestorOfType("dojotags.Form");
			if (form) {
				this.form = form;
				if (args.path) {
					this.path = args.path;
					console.debug("Initializing binding to ", this.path);
					if (this.declaredClass === "dojotags.FormError") {
						// bind path to the form's errors attribute
						form.bindErrorPath(this);
					} else {
						// bind path to the form's model attribute
						form.bindPath(this);
					}
				}
			}
		},

		/**
		 * Overrides to update the model's "value" attribute from the current
		 * value of the "value" property of the dijit. Needed for some events,
		 * such as "onkeypress" in ValidationTextBox dijit before the "onchange"
		 * is triggered.
		 */
		processEvent : function(event, sync) {
			if (this.dijit) {
				this.model.set("value", this.dijit.getValue());
			}
			this.inherited(arguments);
		}

	});
});