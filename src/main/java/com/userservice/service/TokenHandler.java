package com.userservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.apache.log4j.Logger;

import com.userservice.dao.UserAccountRepository;
import com.userservice.entity.User;
import com.userservice.util.StringConditions;
/**
 * 
 * @author MAYANK
 *
 */
public final class TokenHandler {
	private static Logger log = Logger.getLogger(TokenHandler.class);
	private String secret = null;
	private UserAccountRepository userAccountRepository = null;

	public TokenHandler(String secret, UserAccountRepository userService) {
		this.secret = StringConditions.checkNotBlank(secret);
		this.userAccountRepository = userService;
	}

	public User parseUserFromToken(String token) {
		String username = Jwts.parser().setSigningKey(secret)
				.parseClaimsJws(token).getBody().getSubject();
		log.info("user name after parsing " + username);
		return (User) userAccountRepository.loadUserByUsername(username);
	}

	public String createTokenForUser(User user) {
		log.info("user is inside token handler " + user);
		return Jwts.builder().setSubject(user.getEmail())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}
