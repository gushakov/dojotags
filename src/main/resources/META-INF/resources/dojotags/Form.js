define([ "dojo/_base/declare", "dojo/_base/array", "dojo/json", "dojo/Stateful", "dojox/mvc/sync",
		"./_Container", "./_BindableWidgetMixin" ], function(declare, array, json, Stateful, sync,
		_Container, _BindableWidgetMixin) {
	return declare("dojotags.Form", [ _Container, _BindableWidgetMixin ], {

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
		 * Creates binding between this form's model attribute corresponding to
		 * the "path" property of the target widget and the target widget's
		 * "value" model attribute.
		 * 
		 * @param {Widget}
		 *            widget Target widget
		 */
		bindPath : function(widget) {
			// set the form's model attribute
			this.model.set(widget.path, widget.model.get("value"));

			// synchronize the form's model attribute and the target
			// widget "value"
			sync(widget.model, "value", this.model, widget.path);
		},

		/**
		 * Creates binding between this form's errors model attribute
		 * corresponding to the "path" property of the target widget and the
		 * target widget's "value" model attribute.
		 * 
		 * @param {FormError}
		 *            widget Target widget
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
			for (attr in errs) {
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