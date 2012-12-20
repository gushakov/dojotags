package com.github.dojotags.web.registry;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dojotags.widgets.Widget;

public class WidgetsRegistry {

	private static final Logger logger = LoggerFactory
			.getLogger(WidgetsRegistry.class);

	private final Map<String, Widget> registry = new HashMap<String, Widget>();

	public void put(Widget widget) {
		registry.put(widget.getId(), widget);
	}

	public Widget get(String id) {
		return registry.get(id);
	}

	public <T extends Widget> T get(String id, Class<T> type) {
		T widget = type.cast(registry.get(id));
		return widget;
	}

}
