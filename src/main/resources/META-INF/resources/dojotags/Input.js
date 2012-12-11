define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/on", "dojo/keys",
		"dijit/form/ValidationTextBox", "dojox/mvc/at", "./FormElement", ], function(declare, lang, on,
		keys, ValidationTextBox, at, FormElement) {
	return declare("dojotags.Input", [ FormElement ], {

		onEnter : null,
		
		initialize : function(args) {
			this.inherited(arguments);
			this.onEnter = args.onEnter || "ignore";
		},

		createDijit : function(node) {

			var dijit = this.dijit = new ValidationTextBox({
				value : at(this.model, "value")
			}, node);

			on(dijit, "keypress", lang.hitch(this, function(evt) {
				if (this.onEnter && evt.charOrCode == keys.ENTER) {
					if (this.onEnter == "default") {
						console.debug(this.id, " enter (default)");
						this.processEvent("enter");
					} else if (this.onEnter == "submit") {
						console.debug(this.id, " enter (submit)");
						this.form.processEvent("submit");
					}
				}
			}));
		}
	});
});