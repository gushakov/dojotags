define([ "dojo/_base/declare", "dojox/mvc/sync", "./Container" ],
		function(declare, sync, Container) {
			return declare("dojotags.Form", [ Container ], {

				/**
				 * Creates a new model attribute with name specified by the
				 * value of the "path" attribute of the target widget and value
				 * corresponding to the value of the target widget's "value"
				 * attribute. Also creates a two-way binding between these
				 * attributes.
				 * 
				 * @param {Widget}
				 *            widget Target widget
				 */
				bindPath : function(widget) {
					// set new model attribute
					this.model.set(widget.model.get("path"), widget.model.get("value"));
					// bind new model attribute
					sync(widget.model, "value", this.model, widget.model.get("path"));
				}
			});
		});