define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/html", "./FormElement", ], function(
		declare, lang, html, FormElement) {
	return declare("dojotags.Error", [ FormElement ], {

		initialize : function(args) {
			this.inherited(arguments);
			this.errorClass = args.errorClass;
			this.model.watch("value", lang.hitch(this, function(name, oldVal, newVal) {
				html.set(this.domNode, newVal);
			}));
		},

		createDijit : function(node) {
			html.set(node, this.model.get("value"));
			console.debug("Created Error with path ", this.path, " and value ", this.model
					.get("value"));
		}
	});
});