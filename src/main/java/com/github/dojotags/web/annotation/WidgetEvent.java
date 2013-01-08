package com.github.dojotags.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation marking a method as a listener for a widget event. Value attribute
 * should specify the name of the event to listen for. Can specify the names
 * widgets which should be updated after the completion of the handler using
 * "updates" attribute.
 * 
 * @author gushakov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WidgetEvent {
	/**
	 * Event name as specified by the widget tag attribute.
	 * 
	 * @return event name, defaults to ""
	 */
	String value() default "";

	/**
	 * List of widget names which need to be updated.
	 * 
	 * @return list of names
	 */
	String[] updates() default {};
}
