define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/html", "dojo/dom-style", "./_Widget" ],
		function(declare, lang, html, domStyle, _Widget) {
			return declare("dojotags.Label", [ _Widget ], {
				initialize : function(args) {
					this.inherited(arguments);
					this.model.set("text", args.text || "");
					// when model's text changes, update the content of the dom
					// node
					// for this label
					this.model.watch("text", lang.hitch(this, function(name, oldVal, newVal) {
						html.set(this.domNode, newVal);
					}));
				},

				createDijit : function(node) {
					html.set(node, this.model.get("text"));
				},
				
				doUpdate : function(event, data) {
					this.model.set("text", data);
				}
			});
		});