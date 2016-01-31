package com.userservice.resource;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;

import com.userservice.config.UserAccountProperties;
import com.userservice.crypto.exception.CryptoException;
import com.userservice.crypto.service.CryptoService;
import com.userservice.service.UserAccountService;
import com.userservice.util.FlowsUtil;

public abstract class UserAccountResourceCommon {
	@Autowired
	private UserAccountProperties userAccountProperties;

	@Autowired
	private CryptoService cryptoService;

	@Autowired
	protected UserAccountService userSignUpService;

	protected EmailExtractedData extractEmailData(HttpServletRequest request)
			throws CryptoException {

		String encUserAndTimestamp = FlowsUtil
				.getParamsUserAndTimestamp(request);

		EmailExtractedData extractedData = new EmailExtractedData();
		ImmutablePair<Date, String> stringAndDate;

		stringAndDate = cryptoService.extractStringAndDate(encUserAndTimestamp);

		extractedData.userEmail = stringAndDate.getRight();
		extractedData.emailCreationDate = stringAndDate.getLeft();
		extractedData.expired = (System.currentTimeMillis()
				- extractedData.emailCreationDate.getTime() > (userAccountProperties
				.getLinksExpirationMinutes() * 1000 * 60L));
		return extractedData;
	}

	class EmailExtractedData {

		String redirectUri;
		String userEmail;
		Date emailCreationDate;
		boolean expired;
	}
}