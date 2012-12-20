define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/_base/array", "dojo/aspect",
		"dojo/store/Memory", "dijit/form/FilteringSelect", "dojox/mvc/at", "./_FormElement",
		"./_BindableWidgetMixin" ], function(declare, lang, array, aspect, Memory, FilteringSelect,
		at, _FormElement, _BindableWidgetMixin) {
	return declare("dojotags.Select", [ _FormElement, _BindableWidgetMixin ], {

		_store : null,

		onOpen : null,
		
		_openedOnce: null,

		initialize : function(args) {
			this.inherited(arguments);

			this.onOpen = args.onOpen || "ignore";

			this._openedOnce = false;
			
			this._store = new Memory({
				data : args.items
			});

			if (this.onOpen === "update") {
				aspect.after(this._store, "query", lang.hitch(this, function(args) {
					this.model.set("regExp", args.name.source);
					// process open event for manual open of the select's
					// drop-down list
					if (args.name.source === "^.*$" && this._openedOnce === false) {
						this.processEvent("open");
						this._openedOnce = true;
					}
				}), true);
			}

		},

		doUpdate : function(event, update) {
			if (event === "open") {
				// add new items to the store
				array.forEach(update.addItems, lang.hitch(this, function(item) {
					this._store.put(item);
				}));				
			}
		},

		createDijit : function(node) {
			this.dijit = new FilteringSelect({
				store : this._store,
				value : at(this.model, "value")
			}, node);

		}
	});
});