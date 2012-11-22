define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/on",
		"dijit/form/Button", "./Widget", ], function(declare, lang, on, Button,
		Widget) {
	return declare("dojotags.Button", [ Widget ], {

		label : null,

		initialize : function(args) {
			this.label = args.label || "";
		},

		createDijit : function(node) {
			var dijit = this.dijit = new Button({
				label : this.label
			}, node);
			console.debug("Created Button dijit ", dijit);

			on(dijit, "click", lang.hitch(this, function() {
				console.debug("Btn1 clicked");
				this.processEvent("click");
			}));
		},

		processCallback : function(data) {
			console.debug("In Button widget processing callback data", data);
		}
	});
});