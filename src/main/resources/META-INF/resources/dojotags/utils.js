define([ "dojo/request", "dojo/_base/kernel", "dojo/Deferred" ],
		function(request, kernel, Deferred) {
			return {

				/**
				 * Sends an Ajax POST request of type "application/json" to the
				 * server. Returns a Deferred with server response.
				 * 
				 * @param {Object}
				 *            args Ajax call properties <code>path</code> Full
				 *            path on the server <code>data</code> Data in
				 *            serialized (Json) form <code>sync</code> Whether
				 *            the request should be executed synchronously
				 * 
				 * @returns Deferred resolved with server response
				 */
				ajaxRequest : function(args) {
					var sync, headers, name, def;
					sync = args.sync || false;
					headers = {
						"Content-Type" : "application/json"
					};
					if (args.headers) {
						name = null;
						for (name in args.headers) {
							if (args.headers.hasOwnProperty(name)) {
								headers[name] = args.headers[name];
							}
						}
					}
					def = new Deferred();
					request.post(args.path, {
						headers : headers,
						data : args.data,
						handleAs : "json",
						sync : sync
					}).then(function(response) {
						def.resolve(response);
					}, function(error) {
						def.reject(error);
					});
					return def;
				},

				/**
				 * Finds a widget with the given name in the global scope.
				 * 
				 * @param name
				 *            name of the widget
				 * @returns {_Widget} widget with the given name
				 */
				findWidgetByName : function(name) {
					var widget = null, attr = null, object = null, global;
					global = kernel.global;
					for (attr in global) {
						console.debug("=============>", 22222);
						if (kernel.global.hasOwnProperty(attr)) {
							object = kernel.global[attr];
							if (object.isInstanceOf && object.isInstanceOf(dojotags._Widget)
									&& object.name === name) {
								widget = object;
								break;
							}
						}
					}
					return widget;
				}
			};
		});