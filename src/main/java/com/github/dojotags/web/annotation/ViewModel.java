package com.github.dojotags.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * Shorthand annotation for session-scoped view-model beans to be declared in
 * the application configuration.
 * 
 * @author gushakov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Bean(initMethod = "init")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Documented
public @interface ViewModel {
}
