package com.userservice.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.config.UserAccountProperties;
import com.userservice.crypto.exception.CryptoException;

/**
 * @author Mayank
 * 
 *         This class is used for creating Activate Account Endpoint.
 * 
 */
@RestController
@RequestMapping(value = "/aa")
public class ActivateAccountEndpoint extends UserAccountEndpointsCommon {

	private static final Logger log = Logger
			.getLogger(ActivateAccountEndpoint.class);

	@Autowired
	private UserAccountProperties properties;

	@RequestMapping
	public String activateAccount(HttpServletRequest request)
			throws IOException {

		EmailExtractedData extractedData = null;

		try {
			extractedData = extractEmailData(request);
			log.info("extracted data is " + extractedData);
		} catch (CryptoException cryptoEx) {
			log.error("Could not extract data from URL", cryptoEx);
			return "URL IS CORRUPTED!";
		}

		if (extractedData.expired) {
			return "URL HAS EXPIRED!";
		} else {
			// enable the account
			userSignUpService.setEnabled(extractedData.userEmail);
			userSignUpService.setLoginSuccessForUser(extractedData.userEmail);
			request.getSession().invalidate();
			request.getSession(true);
			SecurityContextHolder.getContext().setAuthentication(null);
			return "SUCCESS";
		}

	}

}
