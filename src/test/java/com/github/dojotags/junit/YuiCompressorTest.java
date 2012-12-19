package com.github.dojotags.junit;

import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

public class YuiCompressorTest {
	private static final Logger logger = LoggerFactory
			.getLogger(YuiCompressorTest.class);

	@Test
	public void testCompress() throws Exception {
		String js = "require([ \"dojo/ready\", \"dojotags/Page\" , \"dojotags/Flow\", \"dojotags/Label\", \"dojotags/Rows\" ], function(ready, Page , Flow, Label, Rows) { var page_1 = new Page({ 		id : \"page_1\", 		styleClass : \"\" 	});  		ready(function() { 		console.debug(\"DOM is ready\"); 		page_1.startup(); 	});		 });";

		StringReader reader = new StringReader(js);
		JavaScriptCompressor compressor = new JavaScriptCompressor(reader,
				new ErrorReporter() {

					@Override
					public void warning(String message, String sourceName,
							int line, String lineSource, int lineOffset) {
						logger.warn("YUI compressor warning: {}, at line: {}",
								message, line);

					}

					@Override
					public EvaluatorException runtimeError(String message,
							String sourceName, int line, String lineSource,
							int lineOffset) {
						error(message, sourceName, line, lineSource, lineOffset);
						return new EvaluatorException(message);
					}

					@Override
					public void error(String message, String sourceName,
							int line, String lineSource, int lineOffset) {
						logger.warn("YUI compressor error {}, at line: {}",
								message, line);
					}
				});

		StringWriter writer = new StringWriter();

		compressor.compress(writer, -1, true, false, true, false);

		System.out.println(writer);
	}

}
