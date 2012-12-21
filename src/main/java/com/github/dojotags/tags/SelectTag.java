package com.github.dojotags.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.github.dojotags.utils.WidgetUtils;
import com.github.dojotags.web.registry.WidgetsRegistry;
import com.github.dojotags.widgets.Select;

/**
 * Tag handler for {@code Select} widget.
 * 
 * @author gushakov
 * 
 */
public class SelectTag extends AbstractFormElementWidgetTag implements
		BindableWidgetTag {

	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "select";
	public static final String WIDGET_MODULE_NAME = "Select";

	private ObjectMapper jacksonMapper;

	private String onopen;

	public void setOnopen(String onopen) {
		this.onopen = onopen;
	}

	public SelectTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("select");
		setAssertHasParentTag(true);
		jacksonMapper = new ObjectMapper();
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();
		WidgetsRegistry widgetsRegistry = WidgetUtils
				.getWidgetsRegistry(pageContext);
		Select select = widgetsRegistry.get(id, Select.class);
		try {
			if (select != null) {
				templateAttrs.put("items",
						jacksonMapper.writeValueAsString(select.getItems()));
			} else {
				select = new Select();
				select.setId(id);
				widgetsRegistry.put(select);
			}
			templateAttrs.put("onopen", onopen);
		} catch (JsonGenerationException e) {
			throw new JspException(e);
		} catch (JsonMappingException e) {
			throw new JspException(e);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return result;
	}

	@Override
	public String getBindClassName() {
		return Select.class.getName();
	}

}
