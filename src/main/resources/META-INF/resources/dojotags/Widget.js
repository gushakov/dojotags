define([ "dojo/_base/declare", "dojo/_base/kernel", "dojo/_base/lang",
		"dojo/_base/array", "dojo/dom-construct", "dojo/json", "dojo/when",
		"dojo/Stateful", "./utils" ], function(declare, kernel, lang, array,
		domConstruct, json, when, Stateful, utils) {
	return declare("dojotags.Widget", [], {
		/**
		 * Id of the widget, should be unique in the global scope.
		 * 
		 * @type String
		 */
		id : null,

		/**
		 * Model map for this widget. Will be serialized upon Ajax request
		 * processing. Contains domain objects to be represented by this widget.
		 * Model is a Stateful object allowing automatic updates of this
		 * widget's dijit's properties when model's objects change.
		 * 
		 * @type Stateful
		 */
		model : null,

		/**
		 * DOM node to witch this widget's dijit will be attached.
		 */
		domNode : null,

		/**
		 * Dojo dijit for this widget.
		 */
		dijit : null,

		/**
		 * Parent widget (container) or null.
		 * 
		 * @type Widget
		 */
		parent : null,

		/**
		 * Initializes this widget and registers it in the global scope.
		 * 
		 * @param {String}
		 *            args.id Id of the widget
		 * @param {Widget}
		 *            args.parent Parent widget of this widget
		 */
		constructor : function(args) {
			if (args.id === undefined) {
				throw new Error("Widget id cannot be null.");
			}

			if (kernel.global[args.id] !== undefined) {
				throw new Error("Widget with id " + args.id
						+ " exists already.");
			}

			this.id = args.id;
			this.model = new Stateful({});

			// if the parent widget was specified check that it is
			// really a container, then add this widget to the container
			if (args.parent !== undefined) {
				if (!(args.parent instanceof dojotags.Container)) {
					throw new Error(
							"Parent widget should be of type Container.");
				}
				this.parent = args.parent;
				this.parent.addWidget(this);
			}

			// let subclasses to perform custom initialization before
			// the creation of this widget's dijit
			this.initialize(args);

			// create a dom node and a dijit for this widget
			var node = this.domNode = domConstruct.create("span", null);
			this.createDijit(node);

			// register this widget in the global scope
			kernel.global[this.id] = this;
		},

		/**
		 * Subclasses should implement to perform the custom initialization
		 * before the creation of the dijit for this widget.
		 * 
		 * @param args
		 *            Constructor arguments
		 */
		initialize : function(args) {
			// not implemented
		},

		/**
		 * Subclasses should implement to create a Dojo dijit representing this
		 * widget.
		 * 
		 * @param {DomNode}
		 *            node DOM node for the dijit
		 */
		createDijit : function(node) {
			// not implemented
		},

		/**
		 * Sends an Ajax request to the server with the URI template:
		 * <code>/dojotags/widget/{this.id}/event/{event}</code> and
		 * <code>Widget-Class</code> header set to the widget's class. Calls
		 * the callback method with the data returned from the server once the
		 * response has been received.
		 * 
		 * @param {Widget}
		 *            widget Widget which initiated the event
		 * @param {String}
		 *            event Name of the event
		 * @param {Boolean}
		 *            sync Set to true for a synchronous request
		 */
		processEvent : function(widget, event, sync) {
			var uri = "/dojotags/widget/" + this.id + "/event/" + event;
			when(utils.ajaxRequest({
				path : uri,
				data : this.serializeModel(),
				headers : {
					"Widget-Class" : widget.declaredClass
				}
			}), lang.hitch(this, function(response) {
				this.processCallback(widget, event, response);
				return data;
			}));
		},

		/**
		 * Subclasses should implement to update the model of this widget with
		 * the data returned from the server after processing the specified
		 * event.
		 * 
		 * @param {Widget}
		 *            sourceWidget Widget which initiated the event
		 * @param {String}
		 *            event Event processed on the server
		 * @param {Object}
		 *            response Data object returned from the server
		 */
		processCallback : function(sourceWidget, event, response) {
			console.debug("Processing callback for widget ", sourceWidget, event, response);
			// process updates
			array.forEach(response.updates, function(update){
				console.debug("Processing update: ", update);
				
				// get target widget from the global scope
				var targetWidget = kernel.global[update.id];
				if (targetWidget === undefined){
					throw new Error("Cannot find a widget with id " + update.id + ".");
				}
				
				// update target widget model attributes
				for(var attr in update){
					// skip id attribute 
					if (attr != "id"){
						targetWidget.model.set(attr, update[attr]);						
					}
				}
			});
		},

		/**
		 * Return's this widget's model as a string.
		 * 
		 * @return String Widget's model serialized to a Json string
		 */
		serializeModel : function() {
			this.model["id"] = this.id;
			return json.stringify(this.model);
		},

		/**
		 * Starts up this widget's dijit. Subclasses should override to lay out
		 * children widgets or to attach the dijit to the document DOM before
		 * calling this method.
		 */
		startup : function() {
			// start up the dijit
			if (this.dijit) {
				this.dijit.startup();
			}
		}
	});
});