package com.github.dojotags.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.PageContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Collection of static utility methods.
 * 
 * @author gushakov
 * 
 */
public class Utils {

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
	 * Returns annotation of the given {@code type} if any is present in the
	 * list of given annotations.
	 * 
	 * @param annotations
	 *            list of annotations to search
	 * @param type
	 *            class of the annotation to look for
	 * @return annotation of the given type or null if no such annotation can be
	 *         found
	 */
	public static <T extends Annotation> T findAnnotation(
			Annotation[] annotations, Class<T> type) {
		Annotation annot = null;
		for (Annotation annotation : annotations) {
			if (annotation.getClass().equals(type)) {
				annot = annotation;
				break;
			}
		}
		return type.cast(annot);
	}

	/**
	 * Constructs a getter method name given a field name.
	 * 
	 * @param fieldName
	 *            field name
	 * @return getter method name
	 */
	public static String getGetter(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}

	/**
	 * Constructs a setter method name given a field name.
	 * 
	 * @param fieldName
	 *            field name
	 * @return setter method name
	 */
	public static String getSetter(String fieldName) {
		return "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}

	/**
	 * Find a method by name.
	 * 
	 * @param name
	 *            method name
	 * @param methods
	 *            collection of methods
	 * @return first method with matching name, parameters are ignored
	 */
	public static Method findMehod(String name, Method[] methods) {
		Method method = null;
		for (Method nextMethod : methods) {
			if (nextMethod.getName().equals(name)) {
				method = nextMethod;
				break;
			}
		}
		return method;
	}

	/**
	 * Looks up a bean from current web application context.
	 * 
	 * @param pageContext
	 *            page context
	 * @param beanClass
	 *            class of the bean to look up
	 * @return a bean from the web application context
	 */
	public static <T> T lookupBean(PageContext pageContext, Class<T> beanClass) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(
				pageContext.getServletContext()).getBean(beanClass);
	}

}
