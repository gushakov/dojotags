package com.github.dojotags.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotaion used to mark controller methods which handle a widget event.
 * 
 * @author gushakov
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface WidgetEventMapping {
	String widgetId();

	String event() default "click";
}
