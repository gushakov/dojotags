define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/_base/array", "dojo/dom-construct",
		"dojox/json/query", "./Widget" ], function(declare, lang, array, domConstruct, query,
		Widget) {
	return declare("dojotags.Container", [ Widget ], {

		/**
		 * List of children widgets of this container.
		 * 
		 * @type {Array}
		 */
		widgets : null,

		constructor : function(args) {
			this.widgets = [];
		},

		addWidget : function(widget) {
			if (!(widget instanceof dojotags.Widget)) {
				throw new Error("Not an instance of Widget.");
			}

			if (query("$[?id=$1]", this.widgets, widget.id).length !== 0) {
				throw new Error("Widget with ID " + widget.id
						+ " is already registered with this container " + this.id);
			}
			this.widgets.push(widget);
		},

		placeChildWidgetDomNode : function(node, isLast) {
			domConstruct.place(node, this.domNode, "last");
		},

		findDescendantsOfType : function(type) {
			var list = null;
			list = [];
			// check if the right type
			if (this.declaredClass === type) {
				list.push(this);
			}

			// process children widgets
			array.forEach(this.widgets, lang.hitch(this, function(widget) {
				if (widget.declaredClass === type) {
					list.push(widget);
				}
				// recurse if child widget is a Container
				if (widget.isInstanceOf(dojotags.Container)) {
					array.forEach(widget.findDescendantsOfType(type), function(w) {
						list.push(w);
					});
				}
			}));
			return list;
		},

		startup : function() {
			array.forEach(this.widgets, lang.hitch(this, function(widget) {
				if (widget.dijit) {
					// append the dijit's dom node to the container's dom node
					this.placeChildWidgetDomNode(widget.dijit.domNode);
				} else {
					// append the widget's dom node to the container's dom node
					this.placeChildWidgetDomNode(widget.domNode);
				}
				// start up the widget
				widget.startup();
			}));
			this.inherited(arguments);
		}

	});
});