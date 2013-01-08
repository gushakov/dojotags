package com.github.dojotags.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.dojotags.utils.Utils;

public class UtilsTest {

	@Test
	public void assertValidCssUnitOfMeasure() throws Exception {
		assertTrue(Utils.assertValidCssUnitOfMeasure("34px"));
		assertTrue(Utils.assertValidCssUnitOfMeasure("34 px"));
		assertTrue(Utils.assertValidCssUnitOfMeasure("34em"));
		assertTrue(Utils.assertValidCssUnitOfMeasure("34%"));
		assertFalse(Utils.assertValidCssUnitOfMeasure("34"));
		assertFalse(Utils.assertValidCssUnitOfMeasure("ten px"));
	}

}
