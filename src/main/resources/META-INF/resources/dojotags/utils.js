define([ "dojo/request", "dojo/Deferred" ], function (request, Deferred) {
    return {

        /**
         * Sends an Ajax POST request of type "application/json" to the server.
         * Returns a Deferred with server response.
         *
         * @param {Object} args Ajax call properties
         * <code>path</code> Full path on the server
         * <code>data</code> Data in serialized (Json) form
         * <code>sync</code> Whether the request should be executed
         * synchronously
         *
         * @returns Deferred resolved with server response
         */
        ajaxRequest: function (args) {
            var sync = args.sync || false;
            var headers = {
                "Content-Type": "application/json"
            };
            if (args.headers) {
                for (var name in args.headers) {
                    headers[name] = args.headers[name];
                }
            }
            var def = new Deferred();
            request.post(args.path, {
                headers: headers,
                data: args.data,
                handleAs: "json",
                sync: sync
            }).then(function (response) {
                    def.resolve(response);
                }, function (error) {
                    def.reject(error);
                });
            return def;
        }
    };
});