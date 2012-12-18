package com.github.dojotags.tags;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * Tag handler for {@code Page} widget container. Maintains a map of all nested
 * widget tags used when creating Dojo {@code require} directive.
 * 
 * @author gushakov
 * 
 */
public class PageTag extends AbstractJspBodyWidgetTag {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(PageTag.class);

	public static final String WIDGET_NAME = "page";
	public static final String WIDGET_MODULE_NAME = "Page";

	private boolean compress;

	public void setCompress(boolean compress) {
		this.compress = compress;
	}

	protected TreeMap<String, AbstractWidgetTag> nestedTags;

	public PageTag() {
		setCompress(false);
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("page-begin");
		setTagEndTemplate("page-end");
		nestedTags = new TreeMap<String, AbstractWidgetTag>();
	}

	public void registerNestedTag(AbstractWidgetTag tag) {
		// register once per widget tag name
		if (!this.nestedTags.containsKey(tag.widgetName)) {
			this.nestedTags.put(tag.widgetName, tag);
		}
	}

	/**
	 * Overrides default to substitute placeholders in Dojo require statement
	 * (modules and arguments) with values constructed from the names of the
	 * widget tags in {@code nestedTags} map.
	 */
	@Override
	protected String modifyBodyContentBeforeOutput() {
		String requireModules = "";
		String requireArgs = "";
		String script = "<script>";
		Iterator<AbstractWidgetTag> tagIter = nestedTags.values().iterator();
		while (tagIter.hasNext()) {
			AbstractWidgetTag tag = tagIter.next();
			requireModules += ", \"dojotags/" + tag.widgetModuleName + "\"";
			requireArgs += ", " + tag.widgetModuleName;
		}
		logger.debug("Adding require modules {} and arguments {}",
				requireModules, requireArgs);
		Map<String, Object> requireAttrs = new HashMap<String, Object>();
		requireAttrs.put("requireModules", requireModules);
		requireAttrs.put("requireArgs", requireArgs);
		String content = TagTemplates.replace(getBodyContent().getString(),
				'#', requireAttrs);
		if (compress) {
			script += compress(content);
		} else {
			script += content;
		}
		script += "</script>";
		return script;
	}

	@Override
	protected void resetWidgetAttributes() {
		super.resetWidgetAttributes();
		compress = false;
	}
	
	private String compress(String js) {
		StringReader reader = new StringReader(js);
		StringWriter writer = new StringWriter();
		String result = js;
		try {
			JavaScriptCompressor compressor = new JavaScriptCompressor(reader,
					new ErrorReporter() {

						@Override
						public void warning(String message, String sourceName,
								int line, String lineSource, int lineOffset) {
							logger.warn(
									"YUI compressor warning: {}, at line: {}",
									message, line);

						}

						@Override
						public EvaluatorException runtimeError(String message,
								String sourceName, int line, String lineSource,
								int lineOffset) {
							error(message, sourceName, line, lineSource,
									lineOffset);
							return new EvaluatorException(message);
						}

						@Override
						public void error(String message, String sourceName,
								int line, String lineSource, int lineOffset) {
							logger.warn("YUI compressor error {}, at line: {}",
									message, line);
						}
					});

			compressor.compress(writer, -1, true, false, true, false);
			result = writer.toString();
		} catch (EvaluatorException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
}
