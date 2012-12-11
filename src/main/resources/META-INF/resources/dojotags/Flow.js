define([ "dojo/_base/declare", "dojo/_base/window", "dojo/dom-construct", "dojo/dom-style",
		"./Container" ], function(declare, win, domConstruct, domStyle, Container) {
	return declare("dojotags.Flow", [ Container ], {

		spacerWidth : null,

		initialize : function(args) {
			this.spacerWidth = args.spacerWidth || "1em";
		},

		createDijit : function(node) {
			domStyle.set(node, {
				overflow : "auto",
				width : "100%",
			});
		},

		placeChildWidgetDomNode : function(node) {
			// place child dijits one after another inside the container's dom
			// node, separated by a spacer (except if this is the last widget)
			domStyle.set(node, {
				display : "block",
				float : "left"
			});
			domConstruct.place(node, this.domNode, "last");
			domConstruct.create("div", {
				style : {
					display : "block",
					float : "left",
					width : this.spacerWidth
				},
				innerHTML : "&nbsp;"
			}, this.domNode);

		}

	});
});