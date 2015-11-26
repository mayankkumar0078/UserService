package com.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Mayank
 * 
 * This class is used for configuring User Account properties.
 * 
 */
@Component
public class UserAccountProperties {

	@Value("${userservice.linksExpirationMinutes}")
	private int linksExpirationMinutes;

	@Value("${userservice.isREST}")
	private boolean isREST;

	@Value("${userservice.accountActivatedEndpointUrl}")
	private String accountActivatedEndpointUrl;

	@Value("${userservice.email.formField}")
	private String authFlowsEmailsFromField;

	public int getLinksExpirationMinutes() {
		return linksExpirationMinutes;
	}

	public void setLinksExpirationMinutes(int linksExpirationMinutes) {
		this.linksExpirationMinutes = linksExpirationMinutes;
	}

	public boolean isREST() {
		return isREST;
	}

	public String getAccountActivatedEndpointUrl() {
		return accountActivatedEndpointUrl;
	}

	public String getAuthFlowsEmailsFromField() {
		return authFlowsEmailsFromField;
	}

}
