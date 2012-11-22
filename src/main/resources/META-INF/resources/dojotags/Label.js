define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/html", "./Widget", ], function(declare, lang, html, Widget) {
	return declare("dojotags.Label", [ Widget ], {

		text : null,

		initialize : function(args) {
			this.text = args.text || "";
		},

		createDijit : function(node) {
			
			html.set(node, this.text);
			
			console.debug("Created Label node with text ", this.text);

		}
	});
});