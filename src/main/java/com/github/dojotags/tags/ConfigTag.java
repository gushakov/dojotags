package com.github.dojotags.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigTag extends SimpleTagSupport {
	public static final Logger logger = LoggerFactory
			.getLogger(ConfigTag.class);

	private String theme;

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Override
	public void doTag() throws JspException, IOException {
		logger.debug("Parsing config tag with theme {}.", theme);
		
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();
		String contextPath = ((HttpServletRequest) pageContext.getRequest())
				.getContextPath();

		// get theme

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

		try {
			
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("contextPath", contextPath);
			attrs.put("theme", themeStr);
			out.println(TagTemplates.substitute("config", attrs));

		} catch (Exception e) {
			throw new JspException(e);
		}

	}
}
