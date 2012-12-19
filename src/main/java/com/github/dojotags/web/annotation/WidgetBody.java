package com.github.dojotags.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for requests with {@code Bind-Class} header specifying the class
 * for the Java object which should be deserialized from the Json in the request
 * body.
 * 
 * @author gushakov
 * 
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WidgetBody {
}
