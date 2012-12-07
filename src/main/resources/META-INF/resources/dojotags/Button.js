define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/on", "dijit/form/Button", "dojox/mvc/at",
		"./Widget", ], function(declare, lang, on, Button, at, Widget) {
	return declare("dojotags.Button", [ Widget ], {

		onClick : null,

		initialize : function(args) {
			this.model.set("label", args.label || "");
			this.onClick = args.onClick || "default";
		},

		createDijit : function(node) {
			var dijit = this.dijit = new Button({
				label : at(this.model, "label")
			}, node);
			console.debug("Created Button dijit ", dijit);

			if (this.onClick) {
				if (this.onClick == "default") {
					on(dijit, "click", lang.hitch(this, function() {
						console.debug(this.id, " clicked");
						this.processEvent(this, "click");
					}));

				}
			}
		}
	});
});