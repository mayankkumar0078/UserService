package com.userservice.util;
/**
 * 
 * @author MAYANK
 *
 */
public final class StringConditions {
	private StringConditions() {
	}

	public static String checkNotBlank(String string) {
		if (string != null && string.trim().length() > 0)
			return string;

		return "";
	}
}
