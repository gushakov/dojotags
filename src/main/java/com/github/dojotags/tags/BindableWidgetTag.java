package com.github.dojotags.tags;



/**
 * Specifies a full class name for a Java bean which will be instantiated and
 * populated from the Json sent with the widget event.
 * 
 * @author gushakov
 * @see com.github.dojotags.web.config.WidgetBodyHandlerMethodArgumentResolver
 */
public interface BindableWidgetTag {
	public String getBindClassName();
}
