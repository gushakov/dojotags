define([ "dojo/_base/declare", "dojo/_base/window", "dojo/dom-construct", "./_Container" ],
		function(declare, win, domConstruct, _Container) {
			return declare("dojotags.Rows", [ _Container ], {

				spacerHeight : null,

				initialize : function(args) {
					this.spacerHeight = args.spacerHeight || "1em";
				},

				placeChildWidgetDomNode : function(node) {
					// place child dijits as blocks of div elements inside the
					// container's dom node
					var div = domConstruct.create("div", null, this.domNode);
					domConstruct.place(node, div, "last");
					// add spacer div
					domConstruct.create("div", {
						style : {
							height : this.spacerHeight
						}
					}, this.domNode);
				}

			});
		});