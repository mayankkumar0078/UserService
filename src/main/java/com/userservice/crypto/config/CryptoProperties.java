package com.userservice.crypto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 
 * @author MAYANK
 * This class is used for configuring key store for CryptoService
 *
 */
@Component
public class CryptoProperties {
	@Value("${com.userservice.crypto.keystore}")
	private String simpleKeystore;

	@Value("${com.userservice.crypto.password}")
	private String simplePassword;

	@Value("${com.userservice.crypto.keyAlias}")
	private String keyAlias;

	@Value("${com.userservice.crypto.createKeystoreFileIfNotExist}")
	private String createKeystoreFileIfNotExist;

	public String getSimpleKeystore() {
		return simpleKeystore;
	}

	public void setSimpleKeystore(String simpleKeystore) {
		this.simpleKeystore = simpleKeystore;
	}

	public String getSimplePassword() {
		return simplePassword;
	}

	public void setSimplePassword(String simplePassword) {
		this.simplePassword = simplePassword;
	}

	public String getKeyAlias() {
		return keyAlias;
	}

	public boolean getCreateKeystoreFileIfNotExist() {
		return Boolean.parseBoolean(createKeystoreFileIfNotExist);
	}
}
