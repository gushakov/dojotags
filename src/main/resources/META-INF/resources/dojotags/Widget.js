define([ "dojo/_base/declare", "dojo/_base/kernel", "dojo/_base/lang",
		"dojo/_base/array", "dojo/_base/window", "dojo/dom-construct",
		"dojo/json", "dojo/when", "./utils" ], function(declare, kernel, lang,
		array, win, domConstruct, json, when, utils) {
	return declare("dojotags.Widget", [], {
		/**
		 * Id of the widget, should be unique in the global scope.
		 */
		id : null,

		/**
		 * Model data for this widget. Will be serialized upon Ajax request
		 * processing.
		 * 
		 * @type Object
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
		 * Parent widget.
		 * 
		 * @type Widget
		 */
		parent : null,

		/**
		 * Children widgets of this widget.
		 * 
		 * @type Object
		 */
		widgets : null,

		/**
		 * Initializes this widget and registers it in the global scope.
		 * 
		 * @param {String}
		 *            args.id Id of the widget
		 * @param {Widget}
		 *            args.parent Parent widget of this widget
		 * @throws Error
		 *             if args.id is null or if a widget with the same id exists
		 *             in the map of parant's widgets
		 */
		constructor : function(args) {
			if (args.id === undefined) {
				throw new Error("Widget ID cannot be null.");
			}

			if (kernel.global[args.id] !== undefined) {
				throw new Error("Widget with ID " + args.id
						+ " exists already.");
			}

			this.id = args.id;
			this.model = {};
			this.widgets = {};

			if (args.parent !== undefined) {
				if (!(args.parent instanceof dojotags.Widget)) {
					throw new Error("Parent widget should be of type Widget.");
				}
				if (args.parent.widgets
						&& args.parent.widgets[this.id] !== undefined) {
					throw new Error("Widget with ID " + this.id
							+ " is already registered with parent "
							+ args.parent.id);
				}
				this.parent = args.parent;
				this.parent.addWidget(this);
			}

			kernel.global[this.id] = this;

			this.initialize(args);

			var node;
			if (this.parent) {
				// create new div and place it as a last element in the
				// parent's dom node
				node = domConstruct.create("div", null, this.parent.domNode);
			} else {
				// create new div and append it to the document body
				node = domConstruct.create("div", null, win.body());

			}
			this.domNode = node;
			this.createDijit(node);

			if (this.dijit) {
				this.dijit.startup();
			}
		},

		initialize : function(args) {
			// subclasses should override with custom processing
		},

		createDijit : function(node) {
			// subclasses should override with custom processing
		},

		addWidget : function(widget) {
			if (!(widget instanceof dojotags.Widget)) {
				throw new Error("Not an instance of Widget.");
			}

			this.widgets[widget.id] = widget;
		},

		processEvent : function(event, sync) {
			var uri = "/dojotags/widget/" + this.id + "/event/" + event;
			when(utils.ajaxRequest(uri, this._serializeModel(), sync || false),
					lang.hitch(this, function(data) {
						this.processCallback(data);
						return data;
					}));
		},

		processCallback : function(data) {
			// subclasses should override with custom processing
		},

		_serializeModel : function() {
			return json.stringify(this.model);
		}
	});
});