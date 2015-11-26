package com.userservice.controller.rest;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.crypto.exception.CryptoException;

/**
 * @author  Mayank
 */
@RestController
@RequestMapping(value = "/rp")
public class RestorePasswordEndpoint extends UserAccountEndpointsCommon
{
	private static Logger log = Logger.getLogger(RestorePasswordEndpoint.class);

	
	@RequestMapping
	public String restorePassword(HttpServletRequest request) throws IOException {
		EmailExtractedData extractedData= null;
		try{
			extractedData = extractEmailData(request);
		}
		catch (CryptoException cryptoEx){
			log.error("Could not extract data from URL", cryptoEx);
			return  "URL IS INVALID";
		}

		if(extractedData.expired){
			log.error("user " + extractedData.userEmail + " tried to use an expired link");
			
			return "URL IS EXPIRED";
		}
		else{
			Date lastChange = userSignUpService.getPasswordLastChangeDate(extractedData.userEmail);

			Date emailCreationDate = extractedData.emailCreationDate;

			//if password was changed AFTER the email creation (that is AFTER the user initiated "4got password" flow) - 
			//it means the request is irrelevant
			if(lastChange.after(emailCreationDate))
			{
				log.error("user " + extractedData.userEmail + " tried to use an expired link: password was already changed AFTER the timestamp of the link");
				return "CAN'T SET THE PASSWORD, because it was set AFTER the link was created.";
			}
			return "SUCCESS, SET NEW PASSWORD";
		}
	}
}
