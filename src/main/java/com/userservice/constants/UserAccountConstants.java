package com.userservice.constants;

/**
 * @author Mayank
 * 
 *         This class holds constants related to User Accounts.
 * 
 */
public abstract class UserAccountConstants {

	public static final String OK = "OK";
	public static final String ERROR = "ERROR";

	public static final String ENCRYPTED_USERNAME_PARAM_NAME = "e";

	public static final String HASH_PARAM_NAME = "enc";
	public static final String REDIRECT_URI_PARAM_NAME = "redirect_uri";
	public static final long DAY_IN_MILLI = 24 * 60 * 60 * 1000;
	public static final int ETERNAL_PASSWORD = -1;

	public static final String LOGIN_FORMS_DIR = "login";

	public static final String EMAIL_PARAM_NAME = "email";
	public static final String CONFIRM_PASSWORD_PARAM_NAME = "confirm_password";
	public static final String DELIMITER = "|";

	public static final String ERR_MSG = "err_msg";
	public static final String ERR_HEADER = "err_header";

	public static final String ACTIVATE_ACCOUNT_ENDPOINT = "/aa";
	public static final String RESTORE_PASSWORD_ENDPOINT = "/rp";
	public static final String CHANGE_PASSWORD_ENDPOINT = "/cp";

	public class MailMessage {
		public static final String AUTHENTICATION_MAIL_SUBJECT = "CollegeDays Authentication Service: Account Created Successfully";
		public static final String RESTORE_PASSWORD_MAIL_SUBJECT = "CollegeDays  Authentication Service: Password Restore Request";
		public static final String UNLOCK_MAIL_SUBJECT = "CollegeDays Authentication Service: Account has been Locked";

	}

}
