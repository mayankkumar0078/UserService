package com.userservice.policy;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
/**
 * 
 * @author MAYANK
 *
 */
public class UserPasswordPolicy {
	public static final Logger log=Logger.getLogger(UserPasswordPolicy.class);
	
	
	private static final String DB_ITEMS_DELIMITER = ";";

	private int passwordMinLength;

	private int passwordMaxLength;

	private int passwordMinUpCaseChars;

	private int passwordMinLoCaseChars;

	private int passwordMinNumbericDigits;

	private int passwordMinSpecialSymbols;

	private List<String> passwordBlackList;

	private int maxPasswordEntryAttempts;

	private int passwordLifeInDays;

	private int rememberMeTokenValidityInDays;

	@SuppressWarnings("unchecked")
	public UserPasswordPolicy(String passwordMinLength, String passwordMaxLength,
			String passwordMinUpCaseChars, String passwordMinLoCaseChars,
			String passwordMinNumbericDigits, String passwordMinSpecialSymbols,
			String passwordBlackList, String maxPasswordEntryAttempts,
			String passwordLifeInDays, String rememberMeTokenValidityInDays) {
		this.passwordMinLength = Integer.parseInt(passwordMinLength);
		this.passwordMaxLength =Integer.parseInt( passwordMaxLength);
		this.passwordMinUpCaseChars =Integer.parseInt( passwordMinUpCaseChars);
		this.passwordMinLoCaseChars = Integer.parseInt(passwordMinLoCaseChars);
		this.passwordMinNumbericDigits = Integer.parseInt(passwordMinNumbericDigits);
		this.passwordMinSpecialSymbols = Integer.parseInt(passwordMinSpecialSymbols);
		this.maxPasswordEntryAttempts =Integer.parseInt( maxPasswordEntryAttempts);
		this.passwordLifeInDays = Integer.parseInt(passwordLifeInDays);
		this.rememberMeTokenValidityInDays = Integer.parseInt(rememberMeTokenValidityInDays);
		log.info(" password blacklist "+passwordBlackList);
		this.passwordBlackList = CollectionUtils.arrayToList(passwordBlackList
				.split(DB_ITEMS_DELIMITER));

	}

	public int getPasswordMinSpecialSymbols() {
		return passwordMinSpecialSymbols;
	}

	public List<String> getPasswordBlackList() {
		return passwordBlackList;
	}

	public int getPasswordLifeInDays() {
		return passwordLifeInDays;
	}

	public int getPasswordMinLength() {
		return passwordMinLength;
	}

	public int getPasswordMinLoCaseChars() {
		return passwordMinLoCaseChars;
	}

	public int getRememberMeTokenValidityInDays() {
		return rememberMeTokenValidityInDays;
	}

	public int getPasswordMinUpCaseChars() {
		return passwordMinUpCaseChars;
	}

	public int getPasswordMaxLength() {
		return passwordMaxLength;
	}

	public int getPasswordMinNumbericDigits() {
		return passwordMinNumbericDigits;
	}

	public int getMaxPasswordEntryAttempts() {
		return maxPasswordEntryAttempts;
	}

}
