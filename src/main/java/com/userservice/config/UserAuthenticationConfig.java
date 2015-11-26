/*package com.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.userservice.service.TokenAuthenticationService;
import com.userservice.service.UserService;

@Configuration
@EnableWebSecurity
public class UserAuthenticationConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public UserService userDetailsService() {
		return new UserService();
	}

	@Bean
	public TokenAuthenticationService tokenAuthenticationService() {
		return new TokenAuthenticationService("tooManySecrets",
				userDetailsService());
	}
}
*/