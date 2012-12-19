package com.github.dojotags.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.PageContext;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.support.WebApplicationContextUtils;

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

	public static String toJson(Object bean, PageContext pageContext,
			String objectMapperBeanName) throws JsonGenerationException,
			JsonMappingException, IOException {
		ObjectMapper jacksonMapper = (ObjectMapper) WebApplicationContextUtils
				.getRequiredWebApplicationContext(
						pageContext.getServletContext()).getBean(
						objectMapperBeanName);
		return jacksonMapper.writeValueAsString(bean);
	}

}
