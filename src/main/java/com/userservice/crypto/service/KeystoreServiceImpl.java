package com.userservice.crypto.service;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.config.crypto.CryptoProperties;
import com.userservice.crypto.interfaces.KeystoreService;

@Service("keystoreService")
public class KeystoreServiceImpl implements InitializingBean, KeystoreService {
	@Autowired
	private CryptoProperties cryptoProperties;

	private PrivateKey privateKey;
	private PublicKey publicKey;

	public void afterPropertiesSet() throws Exception {

	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}
}
