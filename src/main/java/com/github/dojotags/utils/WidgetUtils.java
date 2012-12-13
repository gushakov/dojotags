package com.github.dojotags.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.PageContext;

public class WidgetUtils {
	
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
}
