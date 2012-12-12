define([ "dojo/_base/declare", "./Widget"], function(declare, Widget) {
	return declare("dojotags.FormElement", [ Widget ], {

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
			// register a handle to the ancestor form widget
			var form = this.form = this.findAncestorOfType("dojotags.Form");
			if (form) {
				this.form = form;
				if (args.path) {
					this.path = args.path;
					console.debug("Initializing binding to ", this.path);
					if (this.declaredClass == dojotags.Error.prototype.declaredClass) {
						// bind path to the form's errors attribute
						form.bindErrorPath(this);
					} else {
						// bind path to the form's model attribute
						form.bindPath(this);
					}
				}
			}
			this.model.set("value", args.value || "");
		}

	});
});