package com.github.dojotags.tags;

import java.util.Map;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageTag extends AbstractScriptlessBodyWidgetTag {

	private static final Logger logger = LoggerFactory.getLogger(PageTag.class);

	private String theme;

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public PageTag() {
		setTagBeginTemplate("page-begin");
		setTagEndTemplate("page-end");
	}
	
	@Override
	protected void addTemplateAttributes(Map<String, Object> attrs)
			throws JspException {
		// add theme
		String themeStr = null;
		if (theme == null || theme.matches("\\s*")) {
			themeStr = Constants.THEME_DEFAULT;
			logger.debug("Using default Dojo theme {}", themeStr);
		} else {
			try {
				themeStr = Enum.valueOf(Constants.Theme.class,
						theme.trim().toLowerCase()).name();
			} catch (IllegalArgumentException e) {
				themeStr = Constants.THEME_DEFAULT;
				logger.error(
						"Cannot configure Dojo theme {}, will use default {}",
						theme, themeStr);
			}
		}
		attrs.put("theme", themeStr);
	}

}
