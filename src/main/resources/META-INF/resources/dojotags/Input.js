define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/on", "dojo/keys",
		"dijit/form/ValidationTextBox", "dojox/mvc/at", "./Widget", ], function(declare, lang, on,
		keys, ValidationTextBox, at, Widget) {
	return declare("dojotags.Input", [ Widget ], {

		onEnter : null,

		initialize : function(args) {
			this.model.set("value", args.value || "");
			this.model.set("path", args.path || "");
			this.onEnter = args.onEnter || "default";
		},

		createDijit : function(node) {

			var dijit = this.dijit = new ValidationTextBox({
				value : at(this.model, "value")
			}, node);

			on(dijit, "keypress", lang.hitch(this, function(evt) {
				if (this.onEnter) {
					if (this.onEnter == "default" && evt.charOrCode == keys.ENTER) {
						console.debug(this.id, " enter");
						this.processEvent(this, "enter");
					}
				}
			}));
		}
	});
});