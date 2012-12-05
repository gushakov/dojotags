package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tag handler for {@code config} tag responsible outputting links to Dojo
 * stylesheets and the main Dojo configuration directive in the {@code head}
 * element of the page. Declares {@code theme} attribute used to specify Dijit
 * theme for the document.
 * 
 * @author George Ushakov
 * 
 */
public class ConfigTag extends AbstractTemplatedTag {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(ConfigTag.class);
	private String theme;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public ConfigTag() {
		setTagBeginTemplate("config");
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();

		// add theme attribute
		if (theme == null || theme.matches("\\s*")) {
			theme = Constants.THEME_DEFAULT;
			logger.debug("Using default Dojo theme {}", theme);
		} else {
			try {
				theme = Enum.valueOf(Constants.Theme.class,
						theme.trim().toLowerCase()).name();
			} catch (IllegalArgumentException e) {
				logger.error(
						"Cannot configure Dojo theme {}, will use default {}",
						theme, Constants.THEME_DEFAULT);
				theme = Constants.THEME_DEFAULT;
			}
		}
		templateAttrs.put("theme", theme);
		
		// set theme as a page context variable
		pageContext.setAttribute("dijitTheme", theme, PageContext.PAGE_SCOPE);
		
		return result;
	}

}
