define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/on",
		"dijit/form/Button", "dojox/mvc/at", "./Widget", ], function(declare,
		lang, on, Button, at, Widget) {
	return declare("dojotags.Button", [ Widget ], {

		initialize : function(args) {
			this.model.label = args.label || "";
		},

		createDijit : function(node) {
			var dijit = this.dijit = new Button({
				label : at(this.model, "label")
			}, node);
			console.debug("Created Button dijit ", dijit);

			on(dijit, "click", lang.hitch(this, function() {
				console.debug(this.id, " clicked");
				this.processEvent(this, "click");
			}));
		},

		///////
		// Model setters
		///////

		getLabel : function() {
			this.model.get("label");
		},
		
		setLabel : function(label) {
			this.model.set("label", label);
		}
	});
});