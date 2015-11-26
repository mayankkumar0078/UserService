package com.userservice.crypto.interfaces;

import java.security.PrivateKey;
import java.security.PublicKey;
/**
 * 
 * @author MAYANK
 *
 */
public interface KeystoreService {
	public PrivateKey getPrivateKey();

	public PublicKey getPublicKey();
}
