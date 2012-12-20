package com.github.dojotags.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.github.dojotags.web.registry.WidgetsRegistry;

@Configuration
public class WidgetsRegistryConfiguration {
	private static final Logger logger = LoggerFactory
			.getLogger(WidgetsRegistryConfiguration.class);

	@Bean(name = "widgetsRegistry")
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public WidgetsRegistry widgetsRegistry() {
		WidgetsRegistry registry = new WidgetsRegistry();
		logger.debug(
				"Initializing widgets registry as a session scoped bean {}",
				registry);
		return registry;
	}

}
