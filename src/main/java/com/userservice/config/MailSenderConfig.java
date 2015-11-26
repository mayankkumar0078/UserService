package com.userservice.config;

/**
 * @author Mayank
 * This class is used for configuring the MailSender and its properties.
 */
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailSenderConfig {
	@Autowired
	private Environment env;

	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";

	@Bean
	public JavaMailSenderImpl getJavaMailSenderImpl() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(env.getProperty("userservice.mailingHost"));
		javaMailSenderImpl.setUsername(env.getProperty("userservice.username"));
		javaMailSenderImpl.setPort(Integer.parseInt(env
				.getProperty("userservice.port")));
		javaMailSenderImpl.setPassword(env.getProperty("userservice.password"));
		javaMailSenderImpl.setJavaMailProperties(getMailProperties());
		return javaMailSenderImpl;

	}

	private Properties getMailProperties() {
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty(MAIL_SMTP_AUTH,
				env.getProperty("mail.smtp.auth"));
		javaMailProperties.setProperty(MAIL_SMTP_STARTTLS_ENABLE,
				env.getProperty("mail.smtp.starttls.enable"));
		return javaMailProperties;
	}
}
