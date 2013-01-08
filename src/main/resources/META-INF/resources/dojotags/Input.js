define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/on", "dojo/keys",
		"dijit/form/ValidationTextBox", "dojox/mvc/at", "./_Widget" ], function(declare, lang, on,
		keys, ValidationTextBox, at, _Widget) {
	return declare("dojotags.Input", [ _Widget ], {
		
		onEnter: null,
		
		_cancelOnChangeOnce: null,
		
		initialize : function(args) {
			this.inherited(arguments);
			this._cancelOnChangeOnce = false;
			this.onEnter = args.onEnter || "enter";
		},
		
		/**
		 * Creates <code>dijit.form.ValidationTextBox</code> dijit to support
		 * this widget. Registers widget event handlers for
		 * <code>keypress</code> event for the enter key.
		 */
		createDijit : function(node) {

			var dijit = this.dijit = new ValidationTextBox({
				value : at(this.model, "value")
			}, node);
			
			on(dijit, "change", lang.hitch(this, function(evt) {
				if(! this._cancelOnChangeOnce){
					this.processEvent("change");					
				}
				else {
					this._cancelOnChangeOnce = false;
				}
			}));
			
			on(dijit, "keypress", lang.hitch(this, function(evt) {
				if (evt.charOrCode === keys.ENTER) {
					this.model.set("value", this.dijit.getValue());
					this.processEvent(this.onEnter);
					this._cancelOnChangeOnce = true;
				}
			}));
		},

		getRequestHeaders : function() {
			var headers = this.inherited(arguments);
			headers["Widget-Type"] = "dojotags.Input";
			return headers;
		}
	});
});