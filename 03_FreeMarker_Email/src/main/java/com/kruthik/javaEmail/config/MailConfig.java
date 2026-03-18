package com.kruthik.javaEmail.config;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

public class MailConfig {
	private static final Session SESSION;
	private static final Properties PROPERTIES = new Properties();

	private MailConfig() {
	}

	static {
		try (var input = MailConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
			if (input != null) {
				PROPERTIES.load(input);

				for (String key : PROPERTIES.stringPropertyNames()) {
					String property = PROPERTIES.getProperty(key);
					if (property != null && property.startsWith("${") && property.endsWith("}")) {
						String envVariable = property.substring(2, property.length() - 1);
						String envValue = System.getenv(envVariable);
						if (envValue != null) {
							PROPERTIES.setProperty(key, envValue);
						} else {
							throw new RuntimeException();
						}
					}
				}
			} else {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Properties smtpProps = getProperties();
		String username = get("mail.username");
		String password = get("mail.password");

		SESSION = Session.getInstance(smtpProps, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}

		});

	}

	private static Properties getProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", get("mail.smtp.host"));
		properties.put("mail.smtp.port", get("mail.smtp.port"));
		properties.put("mail.smtp.auth", get("mail.smtp.auth"));
		properties.put("mail.smtp.starttls.enable", get("mail.smtp.starttls.enable"));
		return properties;
	}

	public static String get(String key) {
		return PROPERTIES.getProperty(key);
	}

	public static Session getSession() {
		return SESSION;
	}
}