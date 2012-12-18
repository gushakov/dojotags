define([ "dojo/_base/declare", "dojo/_base/lang", "dojo/Deferred", "dojo/store/Memory",
		"dojo/store/util/QueryResults", "./utils" ], function(declare, lang, Deferred, Memory,
		QueryResults, utils) {
	return declare("dojotags._Store", [ Memory ], {
		
		query : function(args) {

			var data = this.inherited(arguments);
			
			
			

//			console.debug("My store ", args);
//
//			var def = new Deferred();
//
//			var timeout = setTimeout(lang.hitch(this, function() {
//
//				console.debug("After time out ");
//
//				this.put({
//					id : 6,
//					name : "six"
//				});
//
//				def.resolve(data);
//
//				clearInterval(timeout);
//
//			}), 2000);

			return data;
		}

	});
});