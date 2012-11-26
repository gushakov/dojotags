define([ "dojo/_base/declare", "dojo/_base/window", "dojo/dom-construct", "./Container" ],
		function(declare, win, domConstruct, Container) {
			return declare("dojotags.Rows", [ Container ], {

				placeChildWidgetDomNode : function(node) {
					// place child dijits as blocks of div elements
					var div = domConstruct.create("div", null, this.domNode);
					domConstruct.place(node, div, "last");
				}

			});
		});