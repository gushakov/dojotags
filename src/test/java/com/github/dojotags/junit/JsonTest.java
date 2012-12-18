package com.github.dojotags.junit;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dojotags.utils.Items;
import com.github.dojotags.widgets.Select;
import com.google.common.collect.ImmutableMap;

public class JsonTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(JsonTest.class);
	
	@Test
	public void testWriteItems() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ImmutableMap<?, ?> map = ImmutableMap.of(1,
				"one", 2, "two"); 
		
		logger.debug(mapper.writeValueAsString(new Items(map)));
				
		Select select = new Select();
		select.setAddItems(new Items(map));
				
		logger.debug(mapper.writeValueAsString(select));
	}

}
