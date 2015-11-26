package com.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
/**
 * 
 * @author Mayank
 * 
 * This class is used to configure password policy through property file.
 *
 */
@Configuration
@PropertySource({ "classpath:PasswordPolicy.properties" })
public class PasswordPolicyPropertiesConfig {
	static @Bean
    public PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
    }
}
