define([ "dojo/_base/declare", "dojo/_base/window", "dojo/dom-construct", "./_Container" ],
		function(declare, win, domConstruct, _Container) {
			return declare("dojotags.Page", [ _Container ], {

				startup : function() {
					// do layout, start all the children dijits
					this.inherited(arguments);

					// attach the domNode of this page to the body of the
					// document
					domConstruct.place(this.domNode, win.body(), "last");
				}

			});
		});