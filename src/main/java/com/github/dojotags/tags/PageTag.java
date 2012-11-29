package com.github.dojotags.tags;

import java.util.Map;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageTag extends AbstractScriptlessBodyWidgetTag {
	private static final Logger logger = LoggerFactory.getLogger(PageTag.class);

	public static final String WIDGET_NAME = "page";

	private String theme;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public PageTag() {
		setWidgetName(WIDGET_NAME);
		setTagBeginTemplate("page-begin");
		setTagEndTemplate("page-end");
	}

	@Override
	protected void addTemplateAttributes(Map<String, Object> attrs)
			throws JspException {
		// add theme attribute
		if (theme == null || theme.matches("\\s*")) {
			theme = Constants.THEME_DEFAULT;
			logger.debug("Using default Dojo theme {}", theme);
		} else {
			try {
				theme = Enum.valueOf(Constants.Theme.class,
						theme.trim().toLowerCase()).name();
			} catch (IllegalArgumentException e) {
				theme = Constants.THEME_DEFAULT;
				logger.error(
						"Cannot configure Dojo theme {}, will use default {}",
						theme, theme);
			}
		}
		attrs.put("theme", theme);
	}

}
