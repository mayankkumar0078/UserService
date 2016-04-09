package com.userservice.service;

import java.sql.Date;
import java.util.Collection;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.Assert;

import com.userservice.constants.AccountState;
import com.userservice.constants.StatusType;
import com.userservice.constants.UserAccountConstants;
import com.userservice.crypto.service.CryptoService;
import com.userservice.dao.UserAccountRepository;
import com.userservice.dto.UserDTO;
import com.userservice.entity.User;
import com.userservice.exception.UserServiceException;
import com.userservice.policy.UserPasswordPolicy;
import com.userservice.repository.policy.PasswordPolicyFactory;
import com.userservice.repository.policy.PasswordPolicyRepository;

@Service("userAccountService")
public class UserAccountService {
	private static Logger log = Logger.getLogger(UserAccountService.class);

	public static final String EMAIL_NOT_VALID = "The e-mail you have entered is not valid.";
	public static final String USER_ALREADY_EXIST = "User already exists. Try with some other User Email";

	private static final String PASSWORD_CANNOT_BE_USED = "Your password is not acceptable by the organizational password policy.";
	private static final String PASSWORD_IS_TOO_LONG = "Password is too long";
	private static final String PASSWORD_IS_TOO_SHORT = "Password is too short";
	private static final String PASSWORD_TOO_FEW_LOWERS = "Password needs to contains at least %d lower-case characters";
	private static final String PASSWORD_TOO_FEW_UPPERS = "Password needs to contains at least %d upper-case characters";
	private static final String PASSWORD_TOO_FEW_NUMERICS = "Password needs to contains at least %d numeric characters";
	private static final String PASSWORD_TOO_FEW_SPECIAL_SYMBOLS = "Password needs to contains at least %d special symbols";
	private static final String SETTING_A_NEW_PASSWORD_HAS_FAILED_PLEASE_NOTE_THE_PASSWORD_POLICY_AND_TRY_AGAIN_ERROR_MESSAGE = "Setting a new password has failed. Please note the password policy and try again. Error message: ";
	public static final String ACCOUNT_CREATION_HAS_FAILED_PASSWORDS_DO_NOT_MATCH = "Account creation has failed. These passwords don't match";

	private static final String ACCOUNT_LOCKED_OR_DOES_NOT_EXIST = "Either User Account is Locked or Non existant or Not Active";

	private static final String LINK_HAS_EXPIRED = "link has expired";

	private static final String CHANGE_PASSWORD_FAILED_NEW_PASSWORD_SAME_AS_OLD_PASSWORD = "CHANGE PASSWORD FAILED: New Password is same as Old Password.";
	private static final String CHANGE_PASSWORD_BAD_OLD_PASSWORD = "CHANGE PASSWORD Failed: Bad Old Password.";

	@Autowired(required = true)
	private UserAccountRepository repository;

	@Autowired(required = true)
	private PasswordPolicyRepository policyRepo;

	@Autowired(required = true)
	private PasswordEncoder passwordEncoder;

	@Autowired(required = true)
	private PasswordPolicyFactory passwordPolicyFactory;

	@Autowired(required = true)
	private MailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private CryptoService cryptoService;

	public User createAccount(UserDTO userDTO, String path)
			throws UserServiceException {

		UserPasswordPolicy userPasswordPolicy = getPasswordPolicy();

		validateEmail(userDTO.getEmail());

		//validateRetypedPassword(userDTO.getPassword(), userDTO.getConfirmPassword());
		//validatePassword(userDTO.getPassword(), userPasswordPolicy);
		return internalCreateAccount(userDTO.getEmail(), encodeString(userDTO.getPassword()), userDTO.getFirstName(),
				userDTO.getLastName(), path);
	}

	private UserPasswordPolicy getPasswordPolicy() {
		return passwordPolicyFactory.getDefaultPasswordPolicyRepository()
				.getPasswordPolicy();
	}

	private void validateEmail(String email) throws UserServiceException { // need to
																		// modify
																		// this
																		// method
		if (!email.contains("@")) {
			throw new UserServiceException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(),EMAIL_NOT_VALID);
		}
	}

	private void validateRetypedPassword(String password, String retypedPassword)
			throws UserServiceException {
		if (!password.equals(retypedPassword)) {
			throw new UserServiceException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(),
					ACCOUNT_CREATION_HAS_FAILED_PASSWORDS_DO_NOT_MATCH);
		}
	}

	private void validatePassword(String password,
			UserPasswordPolicy userPasswordPolicy) throws UserServiceException {
		//validateAgainstBlacklistedPasswords(password, userPasswordPolicy);

		//validatePasswordMaxLength(password, userPasswordPolicy);

		//validatePasswordMinLength(password, userPasswordPolicy);

		//validateCharactersTypeCounts(password, userPasswordPolicy);

	}

	private void validateCharactersTypeCounts(String password,
			UserPasswordPolicy userPasswordPolicy) throws UserServiceException {
		int uppersCounter = 0;
		int lowersCounter = 0;
		int numericCounter = 0;
		int specialSymbolCounter = 0;
		char[] dst = new char[password.length()];
		password.getChars(0, password.length(), dst, 0);
		for (int i = 0; i < password.length(); ++i) {
			if (Character.isUpperCase(dst[i])) {
				++uppersCounter;
			} else if (Character.isLowerCase(dst[i])) {
				++lowersCounter;
			} else if (Character.isDigit(dst[i])) {
				++numericCounter;
			} else {
				// not digit and not a letter - consider it as a 'special
				// symbol':
				++specialSymbolCounter;
			}
		}

		Formatter formatter = new Formatter();

		String retVal = "";

		if (uppersCounter < userPasswordPolicy.getPasswordMinUpCaseChars()) {
			retVal = formatter.format(PASSWORD_TOO_FEW_UPPERS,
					userPasswordPolicy.getPasswordMinUpCaseChars()).toString();
		}
		if (lowersCounter < userPasswordPolicy.getPasswordMinLoCaseChars()) {
			retVal = formatter.format(PASSWORD_TOO_FEW_LOWERS,
					userPasswordPolicy.getPasswordMinLoCaseChars()).toString();
		}
		if (numericCounter < userPasswordPolicy.getPasswordMinNumbericDigits()) {
			retVal = formatter.format(PASSWORD_TOO_FEW_NUMERICS,
					userPasswordPolicy.getPasswordMinNumbericDigits())
					.toString();
		}
		if (specialSymbolCounter < userPasswordPolicy
				.getPasswordMinSpecialSymbols()) {
			retVal = formatter.format(PASSWORD_TOO_FEW_SPECIAL_SYMBOLS,
					userPasswordPolicy.getPasswordMinSpecialSymbols())
					.toString();
		}

		formatter.close();

		if (!retVal.isEmpty()) {
			throw new UserServiceException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(),
					SETTING_A_NEW_PASSWORD_HAS_FAILED_PLEASE_NOTE_THE_PASSWORD_POLICY_AND_TRY_AGAIN_ERROR_MESSAGE
							+ "; " + retVal);
		}
	}

	private void validatePasswordMinLength(String password,
			UserPasswordPolicy userPasswordPolicy) throws UserServiceException {
		if (password.length() < userPasswordPolicy.getPasswordMinLength()) {
			throw new UserServiceException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(),
					SETTING_A_NEW_PASSWORD_HAS_FAILED_PLEASE_NOTE_THE_PASSWORD_POLICY_AND_TRY_AGAIN_ERROR_MESSAGE
							+ "; " + PASSWORD_IS_TOO_SHORT);
		}
	}

	private void validatePasswordMaxLength(String password,
			UserPasswordPolicy userPasswordPolicy) throws UserServiceException {
		if (password.length() > userPasswordPolicy.getPasswordMaxLength()) {
			throw new UserServiceException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(),
					SETTING_A_NEW_PASSWORD_HAS_FAILED_PLEASE_NOTE_THE_PASSWORD_POLICY_AND_TRY_AGAIN_ERROR_MESSAGE
							+ "; " + PASSWORD_IS_TOO_LONG);
		}
	}

	private void validateAgainstBlacklistedPasswords(String password,
			UserPasswordPolicy userPasswordPolicy) throws UserServiceException {
		List<String> blackList = userPasswordPolicy.getPasswordBlackList();
		if (blackList != null) {
			for (String forbidenPswd : blackList) {
				if (password.equalsIgnoreCase(forbidenPswd)) {
					throw new UserServiceException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
							HttpStatus.NOT_ACCEPTABLE.value(),
							SETTING_A_NEW_PASSWORD_HAS_FAILED_PLEASE_NOTE_THE_PASSWORD_POLICY_AND_TRY_AGAIN_ERROR_MESSAGE
									+ "; " + PASSWORD_CANNOT_BE_USED);
				}
			}
		}
	}

	private String encodeString(String rawPass) {
		// encoding the password:
		String encodedPassword = passwordEncoder.encode(rawPass);
		return encodedPassword;
	}

	private User internalCreateAccount(String email, String encodedPassword,
			String firstName, String lastName, String serverPath)
			throws UserServiceException {
		email = email.toLowerCase();
		log.info("createAccount() for user " + email);
		User user=null;
		try {
			User oauthUser = null;

			try {
				oauthUser = (User) repository.loadUserByUsername(email);
			} catch (UsernameNotFoundException unfe) {
				// basically do nothing - we expect user not to be found.
			}

			// if user exist, but not activated - we allow re-registration:
			if (oauthUser != null) {
				if (!oauthUser.isEnabled()) {
					repository.deleteUser(email);
				} else {
					log.error("cannot create account - user " + email
							+ " already exist.");
					throw new UserServiceException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
							HttpStatus.NOT_ACCEPTABLE.value(),USER_ALREADY_EXIST);
				}
			}

			Collection<? extends GrantedAuthority> authorities = setAuthorities(); // set
																					// authorities
			user = new User(encodedPassword, email, firstName, lastName,
					new Date(System.currentTimeMillis()), passwordPolicyFactory
							.getDefaultPasswordPolicyRepository()
							.getDefaultPasswordPolicy()
							.getMaxPasswordEntryAttempts());
			//user.setStatus(StatusType.ACTIVE);
			repository.createUser(user);
		}

		catch (DataIntegrityViolationException e) {
			Throwable root = e.getRootCause();
			String msg = root.getMessage();
			Assert.isTrue(msg.contains("Duplicate entry"));

			log.error(msg);
			throw new UserServiceException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(),USER_ALREADY_EXIST);
		}

		log.info("Manager: sending registration email to " + email + "...");

		String activationUrl = serverPath
				+ UserAccountConstants.ACTIVATE_ACCOUNT_ENDPOINT
				+ "?"
				+ "uts="
				+ cryptoService.createEncodedContent(
						new Date(System.currentTimeMillis()), email);

		log.info("Activation url is " + activationUrl);
		try {
			sendMail(
					firstName,
					email,
					UserAccountConstants.MailMessage.AUTHENTICATION_MAIL_SUBJECT,
					"authentication.vm", activationUrl);
		} catch (MailException me) {
			log.error(me.getMessage());
			throw new UserServiceException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(),me.getMessage());
		}
		return user;
	}

	private void sendMail(String firstName, String email, String mailSubject,
			String msgTextFileName, String urlInMessage) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("firstName", firstName);
		model.put("username", email);
		model.put("url", urlInMessage);
		String mailBody = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, getResourcePath(msgTextFileName), "UTF-8",
				model);

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject(mailSubject);
		msg.setText(mailBody);

		mailSender.send(msg);
	}

	private static String getResourcePath(String name) {
		return "mailTemplates/" + Locale.getDefault().getLanguage() + "/"
				+ name;
	}

	private Collection<? extends GrantedAuthority> setAuthorities() {
		Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
		GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_USER");
		set.add(auth);
		return set;
	}

	public void setEnabled(String userEmail) {
		repository.setEnabled(userEmail);
	}

	public boolean setLoginSuccessForUser(String username) {
		log.debug("setting login success for user " + username);
		repository.setAttemptsLeft(username, getPasswordPolicy()
				.getMaxPasswordEntryAttempts());

		return isPasswordChangeRequired(username);
	}

	private boolean isPasswordChangeRequired(String username) {
		Date passwordLastChangeDate = repository
				.getPasswordLastChangeDate(username);

		// in case of 'demo' user (when the oauth client invokes actions like
		// create account), the user will not be found in the DB:
		if (null == passwordLastChangeDate) {
			// error, technically:
			return false;
		}

		long passwordLastChange = passwordLastChangeDate.getTime();
		UserPasswordPolicy policy = getPasswordPolicy();
		int passwordLifeInDays = policy.getPasswordLifeInDays();
		if (passwordLifeInDays == UserAccountConstants.ETERNAL_PASSWORD) {
			return false;
		}

		long passwordLifeInMilisecs = passwordLifeInDays
				* UserAccountConstants.DAY_IN_MILLI;
		Date passwordLimitDate = new Date(passwordLastChange
				+ passwordLifeInMilisecs);
		Date current = new Date(System.currentTimeMillis());

		// if current is after the pass-limit, then user must change his
		// password.
		boolean passChangeRequired = current.after(passwordLimitDate);
		if (passChangeRequired) {
			log.info("password expired for user " + username);
			// log.info("getPasswordLifeInDays(): " +
			// policy.getPasswordLifeInDays() +
			// " passwordLastChangeDate: " + passwordLastChangeDate +
			// " passwordLimitDate: " + passwordLimitDate +
			// " passwordLifeInMilisecs: " + passwordLifeInMilisecs);
		}

		return passChangeRequired;
	}

	public String handleForgotPassword(String email, String serverPath)
			throws UserServiceException {
		validateEmail(email);

		// if account is already locked, no need to ask the user the secret
		// question:
		AccountState accountState = getAccountState(email);
		log.info("Account Current state is : " + accountState);
		if (accountState != AccountState.OK) {
			throw new UserServiceException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(),ACCOUNT_LOCKED_OR_DOES_NOT_EXIST);
		}

		return sendPasswordRestoreMail(email, serverPath);
	}

	public AccountState getAccountState(String email) {
		return repository.isAccountLocked(email);
	}

	private String sendPasswordRestoreMail(String email, String serverPath) {
		String passwordRestoreUrl = serverPath
				+ UserAccountConstants.RESTORE_PASSWORD_ENDPOINT
				+ "?"
				+ "uts="
				+ cryptoService.createEncodedContent(
						new Date(System.currentTimeMillis()), email);

		sendMail(null, email,
				UserAccountConstants.MailMessage.RESTORE_PASSWORD_MAIL_SUBJECT,
				"restorePassword.vm", passwordRestoreUrl);
		return "success";
	}

	public Date getPasswordLastChangeDate(String email) {
		return repository.getPasswordLastChangeDate(email);
	}
	
	public User getUser(String username) throws UserServiceException{
		try{
			return (User)repository.loadUserByUsername(username);
		}catch(UsernameNotFoundException e){
			log.error(e.getMessage());
			throw new UserServiceException(1, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(), ACCOUNT_LOCKED_OR_DOES_NOT_EXIST);
		}
	}

	public List<User> getUsers() throws UserServiceException{
		return repository.findAll();
	}

	public User updateUser(UserDTO userDTO) throws UserServiceException{
		User user=null;
		try{
			user= (User)repository.loadUserByUsername(userDTO.getEmail());
		}catch(UsernameNotFoundException e){
			log.error(e.getMessage());
			throw new UserServiceException(1, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(), ACCOUNT_LOCKED_OR_DOES_NOT_EXIST);
		}
		
		if(null==user.getStatus()){
			throw new UserServiceException(1, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(), ACCOUNT_LOCKED_OR_DOES_NOT_EXIST);
		}
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		repository.updateUser(user);
		return user;
		
	}

	public void deleteUser(String username) throws UserServiceException {
		try{
			repository.deleteUser(username);
		}catch(UsernameNotFoundException e){
			log.error(e.getMessage());
			throw new UserServiceException(1, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(), ACCOUNT_LOCKED_OR_DOES_NOT_EXIST);
		}
		
		
	}

}
