define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/on", "dijit/form/Button", "dojox/mvc/at",
		"./FormElement", ], function(declare, lang, on, Button, at, FormElement) {
	return declare("dojotags.Button", [ FormElement ], {

		onClick : null,

		initialize : function(args) {
			this.inherited(arguments);
			this.model.set("label", args.label || "");
			this.onClick = args.onClick || "ignore";
		},

		createDijit : function(node) {
			var dijit = this.dijit = new Button({
				label : at(this.model, "label")
			}, node);
			console.debug("Created Button dijit ", dijit);

			if (this.onClick) {
				on(dijit, "click", lang.hitch(this, function() {
					if (this.onClick == "default") {
						console.debug(this.id, " clicked (default)");
						this.processEvent("click");
					} else if (this.onClick == "submit") {
						console.debug(this.id, " clicked (submit)");
						this.form.processEvent("submit");
					}
				}));
			}
		}
	});
});