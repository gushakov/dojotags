package com.github.dojotags.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.PageContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.dojotags.web.registry.WidgetsRegistry;

/**
 * Collection of static utility methods.
 * 
 * @author gushakov
 * 
 */
public class WidgetUtils {

	/**
	 * Checks if {@code text} contains a dimension with a valid CSS unit of
	 * measure: px, em, or %.
	 * 
	 * @param text
	 *            text to check
	 * @return true if text is a dimension with a valid CSS unit of measure
	 */
	public static boolean assertValidCssUnitOfMeasure(String text) {
		boolean answer = false;
		if (text != null) {
			Pattern p = Pattern.compile("\\d+\\s*px|\\d+\\s*em|\\d+\\s*%");
			Matcher m = p.matcher(text);
			if (m.matches()) {
				answer = true;
			}
		}
		return answer;
	}

	/**
	 * Returns an automatically generated unique widget id. Uses widget name and
	 * a value of the counter in {@code pageContext} to generate a unique id for
	 * the current page.
	 * 
	 * @param name
	 *            widget name
	 * @param pageContext
	 *            current page context
	 * @return widget id unique in the page scope
	 */
	public static String getWidgetGuid(String name, PageContext pageContext) {
		Integer offset = (Integer) pageContext
				.getAttribute("dojotags.guid.offset");
		if (offset == null) {
			offset = new Integer(1);
		}
		String guid = name + "_" + offset;
		pageContext.setAttribute("dojotags.guid.offset", offset + 1);
		return guid;
	}

	/**
	 * Returns session-scoped instance of {@linkplain WidgetsRegistry} declared
	 * in the root web application context.
	 * 
	 * @return widget registry
	 * 
	 * @see org.springframework.web.context.support.WebApplicationContextUtils
	 * @see com.github.dojotags.app.config.WidgetsRegistryConfiguration
	 */
	public static WidgetsRegistry getWidgetsRegistry(PageContext pageContext) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(
				pageContext.getServletContext()).getBean(WidgetsRegistry.class);
	}

}
