define([ "dojo/_base/declare" ], function(declare) {
	return declare("dojotags._BindableWidgetMixin", [], {

		/**
		 * Class name of the Java widget corresponding to this widget on the
		 * server side.
		 */
		bind : null,

		initialize : function(args) {
			this.inherited(arguments);
			this.bind = args.bind;
		},

		getRequestHeaders : function() {
			return {
				"Bind-Class" : this.bind
			};
		}

	});
});