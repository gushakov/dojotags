define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/html", "./_FormElement",
		"./_BindableWidgetMixin" ], function(declare, lang, html, _FormElement,
		_BindableWidgetMixin) {
	return declare("dojotags.FormError", [ _FormElement, _BindableWidgetMixin ], {

		initialize : function(args) {
			this.inherited(arguments);
			this.errorClass = args.errorClass;
			this.model.watch("value", lang.hitch(this, function(name, oldVal, newVal) {
				html.set(this.domNode, newVal);
			}));
		},

		createDijit : function(node) {
			html.set(node, this.model.get("value"));
		},

		clear : function() {
			this.model.set("value", "");
		}
	});
});