package com.github.dojotags.tags;

import java.io.IOException;

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

		String t = null;
		if (theme == null || theme.matches("\\s*")) {
			t = Constants.THEME_DEFAULT;
			logger.debug("Using default Dojo theme {}", t);
		} else {
			try {
				t = Enum.valueOf(Constants.Theme.class,
						theme.trim().toLowerCase()).name();
			} catch (IllegalArgumentException e) {
				t = Constants.THEME_DEFAULT;
				logger.error(
						"Cannot configure Dojo theme {}, will use default {}",
						theme, t);
			}
		}

		try {

			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""
					+ contextPath + "/resources/dijit/themes/" + t + "/" + t
					+ ".css\">");

			out.println("<script src=\""
					+ contextPath
					+ "/resources/dojo/dojo.js\" data-dojo-config=\"async: true, packages: [{name: '"
					+ Constants.THEME_DEFAULT + "', location: '" + contextPath
					+ "/resources/" + Constants.THEME_DEFAULT
					+ "'}]\"></script>");

		} catch (Exception e) {
			throw new JspException(e);
		}

	}
}
