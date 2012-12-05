package com.github.dojotags.utils;

public class WidgetUtils {
	private static int offset = 0;

	public static String getWidgetGuid(String name) {
		return name + "_" + (System.currentTimeMillis() + offset++);
	}
}
