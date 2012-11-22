define([ "./utils", "dojo/_base/declare", "dojo/_base/lang", "dojo/when", "dojo/Deferred", "dojo/json",
		"dojo/Stateful", "dojox/mvc/ModelRefController" ], function(utils,
		declare, lang, when, Deferred, json, Stateful,
		ModelRefController) {
	return declare("dojotags.Form", [], {

		/** Form model, visible to the page script */
		model : null,

		/** Form controller */
		_controller : null,

		/** Form's action path */
		_actionPath : null,

		/**
		 * Initializes a dojo.Stateful model object and corresponding
		 * dojox.mvc.ModelRefController controller object for a dojox.mvc.Group
		 * element representing a form component.
		 * 
		 * @param {Object}
		 *            args.modelData model initialization data, empty map by
		 *            default
		 * @param {String}
		 *            args.actionPath path for the submit of the form, epmty
		 *            string by default
		 */
		constructor : function(args) {
			// set up model and controller
			var model = this.model = new Stateful(args.modelData || {});
			this._controller = new ModelRefController({
				model : model
			});
			this._actionPath = args.actionPath || "";
		},

		/**
		 * Converts the form's model to Json and executes a POST request to the
		 * server using action path. Automatically updates the application model
		 * after receiving the response from the server. Returns a Deferred
		 * which will be resolved with the data returned from the server.
		 * 
		 * @param {String}
		 *            action optional, action to be executed on the server
		 * @param {Boolean}
		 *            sync optional, whether the request should be executed
		 *            synchronously, false by default
		 * @returns Deferred resolved with the response from the server
		 */
		submit : function(action, sync) {
			var def = new Deferred();
			var path = this._actionPath;
			if (action) {
				path = path + "/" + action;
			}
			when(utils.ajaxRequest(path, json.stringify(this.model),
					sync || false), lang.hitch(this, function(data) {
				this._controller.set(new Stateful(data));
				def.resolve(data);
			}), lang.hitch(this, function(error) {
				// set the error in the model and reject
				this.model.set("error", error);
				def.reject(error);
			}));
			return def;
		}
	});
});