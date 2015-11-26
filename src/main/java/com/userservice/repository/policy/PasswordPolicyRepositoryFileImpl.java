package com.userservice.repository.policy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.userservice.policy.UserPasswordPolicy;
/**
 * 
 * @author MAYANK
 *
 */
public class PasswordPolicyRepositoryFileImpl implements
		PasswordPolicyRepository {
	
	public static final Logger log=Logger.getLogger(PasswordPolicyRepositoryFileImpl.class);
	
	
	@Value("${userservice.passwordLifeInDays}")
	private String passwordLifeInDays;

	@Value("${userservice.passwordMinLength}")
	private String passwordMinLength;

	@Value("${userservice.passwordMaxLength}")
	private String passwordMaxLength;

	@Value("${userservice.passwordMinUpCaseChars}")
	private String passwordMinUpCaseChars;

	@Value("${userservice.passwordMinLoCaseChars}")
	private String passwordMinLoCaseChars;

	@Value("${userservice.passwordMinDigits}")
	private String passwordMinDigits;

	@Value("${userservice.passwordMinSpecialSymbols}")
	private String passwordMinSpecialSymbols;

	@Value("${userservice.passwordBlackList}")
	private String passwordBlackList;

	@Value("${userservice.rememberMeTokenValidityInDays}")
	private String rememberMeTokenValidityInDays;

	@Value("${userservice.passwordMaxEntryAttempts}")
	private String passwordMaxEntryAttempts;

	public UserPasswordPolicy getDefaultPasswordPolicy() {
		return getPasswordPolicy();
	}

	public UserPasswordPolicy getPasswordPolicy() {
		log.info("passwordMinLength  "+passwordMinLength  +"  password blacklist "+passwordBlackList);
		UserPasswordPolicy policy = new UserPasswordPolicy(passwordMinLength,
				passwordMaxLength, passwordMinUpCaseChars,
				passwordMinLoCaseChars, passwordMinDigits,
				passwordMinSpecialSymbols, passwordBlackList,
				passwordMaxEntryAttempts, passwordLifeInDays,
				rememberMeTokenValidityInDays);

		return policy;
	}

}
