/**
 * 
 */
package com.userservice.exception;

/**
 * @author MAYANK
 *
 */
public class ChangePasswordException extends Exception {

	/**
	 * 
	 */
	public ChangePasswordException() {
	}

	/**
	 * @param message
	 */
	public ChangePasswordException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ChangePasswordException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ChangePasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ChangePasswordException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
