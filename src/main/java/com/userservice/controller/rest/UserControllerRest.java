package com.userservice.controller.rest;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.constants.UserAccountConstants;
import com.userservice.dao.UserAccountRepository;
import com.userservice.exception.ChangePasswordException;
import com.userservice.exception.signup.SignUpException;
import com.userservice.service.TokenAuthenticationService;
import com.userservice.service.UserAccountService;
import com.userservice.util.FlowsUtil;
import com.userservice.util.TokenTransfer;
/**
 * @author Mayank
 * 
 * End point for user 
 */
@RestController
@RequestMapping(value = "/userModule/User/")
public class UserControllerRest {
	private static Logger log = Logger.getLogger(UserControllerRest.class);
	@Autowired(required = true)
	private UserAccountRepository userService;

	@Autowired(required = true)
	private AuthenticationManager authManager;

	@Autowired(required = true)
	private UserAccountService userAccountService;
	
	@Autowired(required=true)
	private TokenAuthenticationService tokenAuthenticationService;

	@RequestMapping(method = RequestMethod.POST)
	public final String createUserAccount(
			@RequestParam(UserAccountConstants.EMAIL_PARAM_NAME) String email,
			@RequestParam("password") String password,
			@RequestParam(UserAccountConstants.CONFIRM_PASSWORD_PARAM_NAME) String retypedPassword,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		log.debug("/userModule/User/: email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName);

		// PrintWriter writer = response.getWriter();
		String path = FlowsUtil.getServerPath(request);
		try {
			return userAccountService.createAccount(email, password,
					retypedPassword, firstName, lastName, path);
		} catch (SignUpException e) {
			log.error(e.getMessage());

			// writer.println(FlowsUtil.unescapeJaveAndEscapeHtml(e.getMessage()));

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "FAILED";
		}

	}

	@RequestMapping(value = "{userId}", method = RequestMethod.GET)
	public @ResponseBody String create(@PathVariable("userId") String userId) {
		System.out.println(userId);
		/*
		 * User user1=new User(); user1.setEmail("MayankKumar0078@gmail.com");
		 * user1.setPassword("mayank"); return user1;
		 */
		return "login";
	}

	@RequestMapping(value = "/changePassword")
	public final String changePassword(
			@RequestParam("currentPassword") String currentPassword,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam(UserAccountConstants.CONFIRM_PASSWORD_PARAM_NAME) String retypedPassword) throws ChangePasswordException{
		
		
		return null;
	}

	/**
	 * Retrieves the currently logged in user.
	 * 
	 * @return A transfer containing the username and the roles.
	 */

	public UserTransfer getUser() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String
				&& ((String) principal).equals("anonymousUser")) {
			// throw new WebApplicationException(401);
		}
		UserDetails userDetails = (UserDetails) principal;

		return new UserTransfer(userDetails.getUsername(),
				this.createRoleMap(userDetails));
	}

	private Map<String, Boolean> createRoleMap(UserDetails userDetails) {
		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.put(authority.getAuthority(), Boolean.TRUE);
		}

		return roles;
	}
	
	@RequestMapping(value="/forgotPassword",method=RequestMethod.GET)
	protected String forgotPasswordPage(
			@RequestParam(UserAccountConstants.EMAIL_PARAM_NAME) String email,
			HttpServletRequest request) throws Exception {

		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put(UserAccountConstants.EMAIL_PARAM_NAME, email);

		String serverPath = FlowsUtil.getServerPath(request);
		try {
			return userAccountService.handleForgotPassword(email, serverPath);
		} catch (SignUpException afe) {
			log.error(afe.getMessage());

			attributes.put(UserAccountConstants.ERR_MSG, afe.getMessage());
			attributes.put(UserAccountConstants.ERR_HEADER, afe.getMessage());
		}
		return "FAILED";
	}

	
	/**
	 * Authenticates a user and creates an authentication token.
	 * 
	 * @param username
	 *            The name of the user.
	 * @param password
	 *            The password of the user.
	 * @return A transfer containing the authentication token.
	 */
	@RequestMapping(value="/authenticate" ,method=RequestMethod.POST)
	public TokenTransfer authenticate(@RequestParam("email") String email, @RequestParam("password") String password){
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(email, password);
		log.info("authentication token is "+authenticationToken);
		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		log.info("authentication is "+authentication);
		/*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
		UserDetails userDetails = this.userService.loadUserByUsername(email);
		log.info("user is :" +userDetails);
		return new TokenTransfer(tokenAuthenticationService.addAuthentication( authentication));
	}

}
