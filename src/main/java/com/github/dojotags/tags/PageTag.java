package com.github.dojotags.tags;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageTag extends SimpleTagSupport {

	private static final Logger logger = LoggerFactory.getLogger(PageTag.class);

	private String widgetId;

	public void setWidgetId(String widgetId) {
		this.widgetId = widgetId;
	}

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

		JspFragment body = getJspBody();

		// get widget id
		String pageId = widgetId.trim();
		if (pageId.equals("")) {
			throw new JspException("Page widgetId attribute is empty");
		}

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
			attrs.put("theme", themeStr);
			attrs.put("contextPath", contextPath);
			attrs.put("pageId", pageId);
			StringWriter writer = new StringWriter();
			writer.append(TagTemplates.substitute("page-begin", attrs));
			body.invoke(writer);
			writer.append(TagTemplates.substitute("page-end", attrs));
			out.println(writer);

		} catch (Exception e) {
			throw new JspException(e);
		}

	}
}
