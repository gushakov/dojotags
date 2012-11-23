package com.github.dojotags.junit;

import static junit.framework.Assert.*;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.stringtemplate.v4.ST;

import com.github.dojotags.tags.Constants;

public class StringTemplateTest {
	
	@Test
	public void testSubstitute() throws Exception {
		String template = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("com/github/dojotags/tags/templates/page-begin.html"));
		assertNotNull(template);
		ST st = new ST(template, '$', '$');
		st.add("theme", Constants.THEME_DEFAULT);
		st.add("contextPath", "");
		String out = st.render();
		assertFalse("Did not replace $theme$", out.contains("$theme$"));
		assertFalse("Did not replace $contextPath$", out.contains("$contextPath$"));
		
	}
}
