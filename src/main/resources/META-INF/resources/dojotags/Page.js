define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/_base/array", "dojo/_base/window",
		"dojo/dom-construct", "./_Container", "./utils" ], function(declare, lang, array, win,
		domConstruct, _Container, utils) {
	return declare("dojotags.Page", [ _Container ], {

		viewClass : null,

		initialize : function(args) {
			this.inherited(arguments);
			this.viewClass = args.viewClass;
		},

		/**
		 * Processes the response from the server by looking at
		 * <code>response.updates</code> list and performing
		 * <code>doUpdate(event, update)</code> logic for all widgets which
		 * need to be updated.
		 * 
		 * @param {String}
		 *            event Name of the event processed on the server
		 * @param {Object}
		 *            response Data object returned from the server
		 */
		processCallback : function(event, response) {
			// process all updates
			array.forEach(response.updates, lang.hitch(this, function(update) {
				// find widget by name
				//var widget = null, name = null;
				for (var name in update) {
					if (update[name] !== undefined){
						var widget = utils.findWidgetByName(name);
						if (widget) {
							widget.doUpdate(event, update[name]);
						}
						break;
					}
				}
			}));
		},

		startup : function() {
			// do layout, start all the children dijits
			this.inherited(arguments);

			// attach the domNode of this page to the body of the
			// document
			domConstruct.place(this.domNode, win.body(), "last");
		}

	});
});