package com.github.dojotags.tags;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stringtemplate.v4.ST;

/**
 * Provides templates support for tag handlers using {@code StringTempate}
 * library. Will read template files from
 * {@code com/github/dojotags/tags/templates} directory on the classpath and
 * cache them in a map. Subclasses can access a template and substitute actual
 * attributes by providing template name and a map of attributes.
 * 
 * @author George Ushakov
 * 
 */
public class TagTemplates {

	private static final Logger logger = LoggerFactory
			.getLogger(TagTemplates.class);

	private static Map<String, String> templateRegistry;

	static {
		// do only once
		if (templateRegistry == null) {
			templateRegistry = new HashMap<String, String>();
			// read template files from templates directory on the classpath
			try {
				File dir = new File(TagTemplates.class.getClassLoader()
						.getResource("com/github/dojotags/tags/templates")
						.toURI());
				File[] files = dir.listFiles();
				for (File file : files) {
					String name = file.getName().replaceFirst("\\.\\w+$", "");
					String template = IOUtils.toString(new FileReader(file));
					templateRegistry.put(name, template);
				}
				logger.debug(
						"Initialized temlpates registry with {} templates.",
						files.length);
			} catch (URISyntaxException e) {
				throw new RuntimeException(e);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Replaces all placeholders delimited by {@code delimiterChar} character
	 * with the values of the corresponding attributes.
	 * 
	 * @param text
	 *            template text with placeholders
	 * @param delimiterChar
	 *            delimiter character
	 * @param attrs
	 *            name/value pairs for placeholders
	 * @return text with the corresponding placeholders replaced by values from
	 *         the map
	 */
	public static String replace(String text, char delimiterChar,
			Map<String, Object> attrs) {
		ST st = new ST(text, delimiterChar, delimiterChar);
		for (String key : attrs.keySet()) {
			st.add(key, attrs.get(key));
		}
		return st.render();
	}

	/**
	 * Substitutes attribute values for the placeholders corresponding to the
	 * attribute keys in the specified template. Returns the string result of
	 * substitution.
	 * 
	 * @param templateName
	 *            template file name without extension
	 * @param attrs
	 *            map of attribute key/value pairs
	 * @return string resulting from substituting attribute values for the
	 *         corresponding placeholders in the template
	 * 
	 * @throws IllegalArgumentException
	 *             if template name is null or if there is no template with the
	 *             given name in the registry
	 */
	public static String substitute(String templateName,
			Map<String, Object> attrs) {
		if (templateName == null || !templateRegistry.containsKey(templateName)) {
			throw new IllegalArgumentException(
					"Could not find a template with the name " + templateName);
		}
		ST st = new ST(templateRegistry.get(templateName), '$', '$');
		for (String key : attrs.keySet()) {
			st.add(key, attrs.get(key));
		}
		return st.render();
	}

	/**
	 * Returns the cached template with the given name.
	 * 
	 * @param templateName
	 *            template name
	 * @return template as text
	 * @throws IllegalArgumentException
	 *             if template name is null or if there is no template with the
	 *             given name in the registry
	 */
	public static String getTemplate(String templateName) {
		if (templateName == null || !templateRegistry.containsKey(templateName)) {
			throw new IllegalArgumentException(
					"Could not find a template with the name " + templateName);
		}
		return templateRegistry.get(templateName);
	}
}
