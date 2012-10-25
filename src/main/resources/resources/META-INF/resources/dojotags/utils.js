define([ "dojo/request", "dojo/Deferred" ], function(request, Deferred) {
	return {

		/**
		 * Sends an Ajax POST request of type "application/json" to the server.
		 * Returns a Deferred with server response.
		 * 
		 * @param path
		 *            full path on the server
		 * @param data
		 *            data in object form
		 * @param sync
		 *            whether the request should be executed synchronously
		 * @returns Deferred resolved with server response
		 */
		ajaxRequest : function(path, data, sync) {
			var def = new Deferred();
			request.post(path, {
				headers : {
					"Content-Type" : "application/json"
				},
				data : data,
				handleAs : "json",
				sync : sync
			}).then(function(data) {
				def.resolve(data);
			}, function(error) {
				def.reject(error);
			});
			return def;
		},

	};
});