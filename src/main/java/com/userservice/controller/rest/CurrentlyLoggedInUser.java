package com.userservice.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mayank
 * 
 *         This class is used for finding out currently logged in user using the
 *         Token provided by the user.
 * 
 */
@RestController
@RequestMapping
public class CurrentlyLoggedInUser {
	private static final Logger log = Logger
			.getLogger(CurrentlyLoggedInUser.class);

	public CurrentlyLoggedInUser() {
	}

	/**
	 * Retrieves the currently logged in user.
	 * 
	 * @return A transfer containing the username and the roles.
	 */
	@RequestMapping(value = "/getCurentlyLoggedInUser", method = RequestMethod.GET, produces = "application/text")
	public Object getUser() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();
		log.info("authentication is " + authentication);
		log.info("principal is  " + principal);
		if (principal instanceof String
				&& ((String) principal).equals("anonymousUser")) {

		}

		// log.info("user details is "+userDetails);
		return principal;
	}

	private Map<String, Boolean> createRoleMap(UserDetails userDetails) {
		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.put(authority.getAuthority(), Boolean.TRUE);
		}

		return roles;
	}

}
