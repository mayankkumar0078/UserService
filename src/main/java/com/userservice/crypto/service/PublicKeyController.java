package com.userservice.crypto.service;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.crypto.interfaces.KeystoreService;

@RestController
public class PublicKeyController {
	@Autowired
	private KeystoreService keystoreService;

	@RequestMapping("/publicKey")
	protected String getPublicKey(HttpServletResponse response)
			throws Exception {
		return Base64.encodeBase64String(keystoreService.getPublicKey()
				.getEncoded());
	}
}
