define(
		[ "dojo/_base/declare", "dojo/_base/lang", "dojo/on", "dojo/keys",
				"dijit/form/ValidationTextBox", "dojox/mvc/at", "./_FormElement",
				"./_BindableWidgetMixin" ], function(declare, lang, on, keys, ValidationTextBox,
				at, _FormElement, _BindableWidgetMixin) {
			return declare("dojotags.Input", [ _FormElement, _BindableWidgetMixin ], {

				onEnter : null,

				initialize : function(args) {
					this.inherited(arguments);
					this.onEnter = args.onEnter || "ignore";
				},

				createDijit : function(node) {

					var dijit = this.dijit = new ValidationTextBox({
						value : at(this.model, "value")
					}, node);

					on(dijit, "keypress", lang.hitch(this, function(evt) {
						if (this.onEnter && evt.charOrCode === keys.ENTER) {
							if (this.onEnter === "default") {
								this.processEvent("enter");
							} else if (this.onEnter === "submit") {
								this.form.processEvent("submit");
							}
						}
					}));
				}
			});
		});