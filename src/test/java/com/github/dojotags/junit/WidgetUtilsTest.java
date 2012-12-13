package com.github.dojotags.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.dojotags.utils.WidgetUtils;

public class WidgetUtilsTest {
	@Test
	public void assertValidCssUnitOfMeasure() throws Exception {
		assertTrue(WidgetUtils.assertValidCssUnitOfMeasure("34px"));
		assertTrue(WidgetUtils.assertValidCssUnitOfMeasure("34 px"));
		assertTrue(WidgetUtils.assertValidCssUnitOfMeasure("34em"));
		assertTrue(WidgetUtils.assertValidCssUnitOfMeasure("34%"));
		assertFalse(WidgetUtils.assertValidCssUnitOfMeasure("34"));
		assertFalse(WidgetUtils.assertValidCssUnitOfMeasure("ten px"));
	}
}
