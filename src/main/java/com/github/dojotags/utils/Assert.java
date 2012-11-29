package com.github.dojotags.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Assert {
	
	public static boolean assertValidCssUnitOfMeasure(String text){
		Pattern p = Pattern.compile("\\d+\\s*px|\\d+\\s*em|\\d+\\s*%");
		Matcher m = p.matcher(text);
		return m.matches();
	}
}
