package com.userservice.config;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorConfig {
	@Bean
	public Validator validator(){
		Validator validator = new LocalValidatorFactoryBean();
		return validator;
	}
}
