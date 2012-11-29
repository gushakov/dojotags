package com.github.dojotags.junit;

import org.junit.Test;

import static junit.framework.Assert.*;

import com.github.dojotags.utils.Assert;

public class AssertTest {
	@Test
	public void assertValidCssUnitOfMeasure() throws Exception {
		assertTrue(Assert.assertValidCssUnitOfMeasure("34px"));
		assertTrue(Assert.assertValidCssUnitOfMeasure("34 px"));
		assertTrue(Assert.assertValidCssUnitOfMeasure("34em"));
		assertTrue(Assert.assertValidCssUnitOfMeasure("34%"));
		assertFalse(Assert.assertValidCssUnitOfMeasure("34"));
		assertFalse(Assert.assertValidCssUnitOfMeasure("ten px"));
	}
}
