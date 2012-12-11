package com.github.dojotags.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assert {

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
}
