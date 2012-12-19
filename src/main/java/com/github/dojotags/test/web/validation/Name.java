package com.github.dojotags.test.web.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// see http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#table-custom-constraints
// for documentation about custom validation annotations

/**
 * Annotation for properties containing proper names: one word consisting of
 * only alphabetical characters. Used in the validation example.
 * 
 * @author gushakov
 * 
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
@Documented
public @interface Name {
	String message() default "{com.github.dojotags.test.web.validation.name}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
