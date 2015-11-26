package com.userservice.repository.policy;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/**
 * 
 * @author MAYANK
 *
 */
public class PasswordPolicyFactory {
	private static Logger log=Logger.getLogger(PasswordPolicyFactory.class);
	
	@Value("${userservice.defaultPasswordPolicy}")
	private String defaultPasswordPolicy;
	
	
	
	private Map<String, PasswordPolicyRepository> passwordPoliciesMap = new HashMap<String, PasswordPolicyRepository>();

	public PasswordPolicyRepository getDefaultPasswordPolicyRepository() {
		log.info("default password Policy is "+defaultPasswordPolicy+"   ");
		return passwordPoliciesMap.get(defaultPasswordPolicy);
	}

	public Map<String, PasswordPolicyRepository> getPasswordPoliciesMap() {
		return passwordPoliciesMap;
	}

	public void setPasswordPoliciesMap(
			Map<String, PasswordPolicyRepository> passwordPoliciesMap) {
		this.passwordPoliciesMap = passwordPoliciesMap;
	}
}
