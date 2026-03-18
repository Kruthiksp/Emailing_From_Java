package com.kruthik.javaEmail.config;

import java.util.TimeZone;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class TemplateConfig {

	public static final Configuration CONFIGURATION;

	private TemplateConfig() {
	}

	public static Configuration getConfiguration() {
		return CONFIGURATION;
	}

	static {
		CONFIGURATION = new Configuration(Configuration.VERSION_2_3_34);
		CONFIGURATION.setClassLoaderForTemplateLoading(TemplateConfig.class.getClassLoader(), "/templates");
		CONFIGURATION.setDefaultEncoding("UTF-8");
		CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		CONFIGURATION.setLogTemplateExceptions(false);
		CONFIGURATION.setWrapUncheckedExceptions(true);
		CONFIGURATION.setFallbackOnNullLoopVariable(false);
		CONFIGURATION.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
	}
}
