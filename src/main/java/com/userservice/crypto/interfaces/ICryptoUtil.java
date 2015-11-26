package com.userservice.crypto.interfaces;

import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/**
 * 
 * @author MAYANK
 *
 */
public interface ICryptoUtil {
	public String encryptAndBase64(byte[] data, Key key);

	public byte[] decryptBase64(String base64andEncrypted, Key key)
			throws IllegalBlockSizeException, BadPaddingException;

	public Key getCryptoKey(String seed);

}
