package com.userservice.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;

import com.userservice.dao.UserAccountRepository;
import com.userservice.entity.User;
/**
 * 
 * @author MAYANK
 *
 */
public class TokenAuthenticationService {
	private static final Logger log = Logger
			.getLogger(TokenAuthenticationService.class);
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

	private TokenHandler tokenHandler = null;

	public TokenAuthenticationService(String secret,
			UserAccountRepository userService) {

		tokenHandler = new TokenHandler(secret, userService);
		log.info("token handler hs been initialized " + tokenHandler + "  "
				+ secret + "   " + userService);
	}

	public String addAuthentication(Authentication authentication) {
		final User user = (User) authentication.getPrincipal();
		String token = tokenHandler.createTokenForUser(user);
		return token;
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) {
			final User user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
