define([ "dojo/_base/declare", "dojo/_base/array", "dojo/json", "dojo/Stateful", "dojox/mvc/sync",
		"./Container" ], function(declare, array, json, Stateful, sync, Container) {
	return declare("dojotags.Form", [ Container ], {

		/**
		 * Map of validation errors for this form.
		 * 
		 * @type Stateful
		 */
		errors : null,

		initialize : function(args) {
			this.inherited(arguments);
			this.errors = new Stateful({});
		},

		/**
		 * Creates a new model attribute with name specified by the value of the
		 * "path" attribute of the target widget and value corresponding to the
		 * value of the target widget's "value" attribute. Also creates a
		 * two-way binding between these attributes.
		 * 
		 * @param {Widget}
		 *            widget Target widget
		 */
		bindPath : function(widget) {
			this.model.set(widget.path, widget.model.get("value"));
			sync(widget.model, "value", this.model, widget.path);
		},

		/**
		 * Creates a new error attribute with name specified by the value of the
		 * "path" attribute of the target widget and value corresponding to the
		 * value of the target widget's "value" attribute. Also creates a
		 * two-way binding between these attributes.
		 * 
		 * @param {Widget}
		 *            widget Target widget (Error)
		 * @throws Error
		 *             If there is no attribute in the model under the same name
		 */
		bindErrorPath : function(widget) {
			if (this.model[widget.path] === undefined) {
				throw new Error("Cannot find corresponding model attribute for error path "
						+ widget.path);
			}
			this.errors.set(widget.path, widget.model.get("value"));
			sync(widget.model, "value", this.errors, widget.path);
		},

		processCallback : function(event, response) {
			this.inherited(arguments);
			var old, errs, attr = null;
			// clear the previous errors
			old = this.findDescendantsOfType(dojotags.FormError.prototype.declaredClass);
			array.forEach(old, function(err) {
				err.clear();
			});
			// set new errors
			errs = response.errors;
			console.debug("Processing form errors ", errs);
			for ( attr in errs) {
				if (errs.hasOwnProperty(attr)) {
					this.errors.set(attr, errs[attr]);
				}
			}
		},

		/**
		 * Override to serialize without the widget id, since it is not expected
		 * to be present as an attribute of the form backing object.
		 */
		serializeModel : function() {
			return json.stringify(this.model);
		}
	});
});