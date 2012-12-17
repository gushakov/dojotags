package com.github.dojotags.test.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, CharSequence> {

	@Override
	public void initialize(Name constraintAnnotation) {
	}

	@Override
	public boolean isValid(CharSequence value,
			ConstraintValidatorContext context) {
		
		boolean answer = false;
		
		if (value != null){
			Pattern pattern = Pattern.compile("\\p{Alpha}+");
			Matcher matcher = pattern.matcher(value);
			if (matcher.matches()){
				answer = true;
			}
		}
		
		return answer;
	}

}
