define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/on", "dijit/form/Button", "dojox/mvc/at",
		"./_Widget", "./_BindableWidgetMixin" ], function(declare, lang, on, Button, at, _Widget, _BindableWidgetMixin) {
	return declare("dojotags.Button", [ _Widget, _BindableWidgetMixin ], {

		onClick : null,

		initialize : function(args) {
			this.inherited(arguments);
			this.model.set("label", args.label || "");
			this.onClick = args.onClick || "ignore";
		},

		createDijit : function(node) {
			var dijit = this.dijit = new Button({
				label : at(this.model, "label")
			}, node);

			if (this.onClick) {
				on(dijit, "click", lang.hitch(this, function() {
					if (this.onClick === "default") {
						this.processEvent("click");
					} else if (this.onClick === "submit") {
						this.findAncestorOfType("dojotags.Form").processEvent("submit");
					}
				}));
			}
		}
	});
});