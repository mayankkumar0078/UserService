package com.userservice.resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.constants.UserAccountConstants;
import com.userservice.dao.UserAccountRepository;
import com.userservice.dto.UserDTO;
import com.userservice.entity.User;
import com.userservice.exception.ChangePasswordException;
import com.userservice.exception.UserServiceException;
import com.userservice.response.UserResponse;
import com.userservice.responsetransformer.UserResponseTransformer;
import com.userservice.service.TokenAuthenticationService;
import com.userservice.service.UserAccountService;
import com.userservice.util.FlowsUtil;
import com.userservice.util.TokenTransfer;

/**
 * @author Mayank
 * 
 *         End point for user
 */
@RestController
@RequestMapping(value = "/userModule/Users")
public class UserResource {
	private static Logger log = Logger.getLogger(UserResource.class);

	@Autowired
	private UserAccountRepository userService;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	@Autowired
	private UserResponseTransformer userResponseTransformer;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, produces = { "application/json" })
	public final UserResponse createUserAccount(@RequestBody UserDTO userDTO,
			HttpServletRequest request) throws IOException {
		log.debug("/userModule/User/: email=" + userDTO.getEmail()
				+ ", firstName=" + userDTO.getFirstName() + ", lastName="
				+ userDTO.getLastName());
		String path = FlowsUtil.getServerPath(request);
		try {
			userResponseTransformer.validateCreateRequest(userDTO);
			User user = userAccountService.createAccount(userDTO, path);
			log.info("" + user.getEmail());
			return userResponseTransformer.transformIntoResponse(Arrays
					.asList(user));
		} catch (UserServiceException e) {
			log.error(e.getMessage());
			return userResponseTransformer.buildExceptionResponse(e);
		}

	}

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody UserResponse getUser(
			@RequestParam("username") String username,
			HttpServletRequest request) {
		log.info("/userModule/User/: email=" + username);

		try {
			
			User user = userAccountService.getUser(username);
			System.out.println("user  :" + user);
			return userResponseTransformer.transformIntoResponse(Arrays
					.asList(user));
		} catch (UserServiceException e) {
			log.error(e.getMessage());
			return userResponseTransformer.buildExceptionResponse(e);
		}

	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = { "application/json" })
	public @ResponseBody UserResponse updateUser(@RequestBody UserDTO userDTO) {
		log.debug("/userModule/User/  PUT: email=" + userDTO.getEmail());
		
		try {
			userResponseTransformer.validateUpdateRequest(userDTO);
			User user = userAccountService.updateUser(userDTO);
			System.out.println("user  :" + user);
			return userResponseTransformer.transformIntoResponse(Arrays
					.asList(user));
		} catch (UserServiceException e) {
			log.error(e.getMessage());
			return userResponseTransformer.buildExceptionResponse(e);
		}

	}
	
	@RequestMapping(method = RequestMethod.DELETE, produces = { "application/json" })
	public @ResponseBody UserResponse deleteUser(@RequestParam("username") String username) {
		log.debug("/userModule/User/  DELETE: email=" + username);
		
		try {
			userAccountService.deleteUser(username);
			return userResponseTransformer.transformIntoResponse(null);
		} catch (UserServiceException e) {
			log.error(e.getMessage());
			return userResponseTransformer.buildExceptionResponse(e);
		}

	}

	@RequestMapping(value = "/all-users", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody UserResponse getUsers() {
		try {
			List<User> users = userAccountService.getUsers();
			return userResponseTransformer.transformIntoResponse(users);
		} catch (UserServiceException e) {
			log.error(e.getMessage());
			return userResponseTransformer.buildExceptionResponse(e);
		}

	}

	@RequestMapping(value = "/changePassword")
	public final String changePassword(
			@RequestParam("currentPassword") String currentPassword,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam(UserAccountConstants.CONFIRM_PASSWORD_PARAM_NAME) String retypedPassword)
			throws ChangePasswordException {

		return null;
	}

	/**
	 * Retrieves the currently logged in user.
	 * 
	 * @return A transfer containing the username and the roles.
	 */

	/*public UserTransfer getUser() {
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
	}*/

	private Map<String, Boolean> createRoleMap(UserDetails userDetails) {
		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.put(authority.getAuthority(), Boolean.TRUE);
		}

		return roles;
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	protected String forgotPasswordPage(
			@RequestParam(UserAccountConstants.EMAIL_PARAM_NAME) String email,
			HttpServletRequest request) throws Exception {

		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put(UserAccountConstants.EMAIL_PARAM_NAME, email);

		String serverPath = FlowsUtil.getServerPath(request);
		try {
			return userAccountService.handleForgotPassword(email, serverPath);
		} catch (UserServiceException afe) {
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
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public TokenTransfer authenticate(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				email, password);
		log.info("authentication token is " + authenticationToken);
		Authentication authentication = this.authManager
				.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		log.info("authentication is " + authentication);
		/*
		 * Reload user as password of authentication principal will be null
		 * after authorization and password is needed for token generation
		 */
		UserDetails userDetails = this.userService.loadUserByUsername(email);
		log.info("user is :" + userDetails);
		return new TokenTransfer(
				tokenAuthenticationService.addAuthentication(authentication));
	}

}
