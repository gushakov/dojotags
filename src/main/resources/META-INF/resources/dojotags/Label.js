define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/html", "./Widget", ],
		function(declare, lang, html, Widget) {
			return declare("dojotags.Label", [ Widget ], {
				initialize : function(args) {
					this.model.text = args.text || "";

					// when model's text changes, update the content of the dom
					// node
					// for this label
					this.model.watch("text", lang.hitch(this, function(name,
							oldVal, newVal) {
						html.set(this.domNode, newVal);
					}));
				},

				createDijit : function(node) {
					html.set(node, this.model.text);
					console.debug("Created Label node with text ", this
							.getText());
				},

				// /////
				// Model getters/setters
				// /////

				getText : function() {
					return this.model.get("text");
				},

				setText : function(text) {
					this.model.set("text", text);
				}
			});
		});