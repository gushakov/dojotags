define([ "dojo/_base/declare", "dojo/_base/window", "dojo/dom-construct", "./Container" ],
		function(declare, win, domConstruct, Container) {
			return declare("dojotags.Flow", [ Container ], {

				spacerWidth : null,

				constructor : function(args) {
					this.spacerWidth = args.spacerWidth || "1em";
				},

				placeChildWidgetDomNode : function(node) {
					// place child dijits one after another
					// inside the container's dom node
					domConstruct.place(node, this.domNode, "last");
					// separate by a spacer span
					domConstruct.create("span", {
						style : "display: inline-block; width: " + this.spacerWidth + ";"
					}, this.domNode);
				}

			});
		});