package com.github.dojotags.tags;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tag handler for {@code Page} widget container. Maintains a map of all nested
 * widget tags used when creating Dojo {@code require} directive.
 * 
 * @author George Ushakov
 * 
 */
public class PageTag extends AbstractJspBodyWidgetTag {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(PageTag.class);

	public static final String WIDGET_NAME = "page";
	public static final String WIDGET_MODULE_NAME = "Page";

	protected TreeMap<String, AbstractWidgetTag> nestedTags;

	public PageTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("page-begin");
		setTagEndTemplate("page-end");
		nestedTags = new TreeMap<String, AbstractWidgetTag>();
	}

	public void registerNestedTag(AbstractWidgetTag tag) {
		this.nestedTags.put(tag.widgetId, tag);
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

		return content;
	}
}
