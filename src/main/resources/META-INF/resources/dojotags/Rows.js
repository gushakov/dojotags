define([ "dojo/_base/declare", "dojo/_base/window", "dojo/dom-construct", "./Container" ],
		function(declare, win, domConstruct, Container) {
			return declare("dojotags.Rows", [ Container ], {
				
				spacerHeight: null,
				
				constructor: function(args){
					this.spacerHeight = args.spacerHeight || "1em";
				},
				
				placeChildWidgetDomNode : function(node) {
					// place child dijits as blocks of div elements
					var div = domConstruct.create("div", null, this.domNode);
					var spacer = domConstruct.create("div", {style: "height: " + this.spacerHeight +  ";"});
					domConstruct.place(node, div, "last");
					domConstruct.place(spacer, div, "last");
				}

			});
		});