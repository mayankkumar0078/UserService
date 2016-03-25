package com.userservice.mongo.controller;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.mongo.document.UserShelfDocument;
import com.userservice.mongo.dto.request.AddBookToShelfReq;
import com.userservice.mongo.dto.request.CreateNewShelfReq;
import com.userservice.mongo.dto.request.DeleteShelfReq;
import com.userservice.mongo.dto.request.RemoveBookFromShelfReq;
import com.userservice.mongo.dto.request.RenameShelfReq;
import com.userservice.mongo.exception.UserShelfServiceException;
import com.userservice.mongo.response.UserShelfResponse;
import com.userservice.mongo.service.UserShelfService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "user/shelf", produces = {"application/json", "application/xml"})
public class UserShelfController {

	@Resource
	UserShelfService userShelfService;
	
	/**
	 * Retrieve the book review based on the book id provided
	 * @param bookId
	 */
	@ResponseBody
	@RequestMapping( method = RequestMethod.GET, produces = {"application/json", "application/xml"})
	public UserShelfResponse retrieveShelves(@RequestParam("userId") String userId){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.retrieveUserShelf(userId);
			 
		} catch (UserShelfServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	
	/**
	 * Retrieve the book review based on the book id provided
	 * @param bookId
	 */
	@ResponseBody
	@RequestMapping( method = RequestMethod.POST, produces = {"application/json", "application/xml"})
	public UserShelfResponse createNewShelf(@RequestBody CreateNewShelfReq req){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.createNewShelf(req);
			 
		} catch (UserShelfServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}

	/**
	 * Retrieve the book review based on the book id provided
	 * @param bookId
	 */
	@ResponseBody
	@RequestMapping( method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public UserShelfResponse updateShelf(@RequestBody UserShelfDocument userShelfDocument){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.updateShelf(userShelfDocument);
			 
		} catch (UserShelfServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	
	/**
	 * Add book to shelf
	 * @param bookId
	 */
	@ResponseBody
	@RequestMapping(value="/addbook", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public UserShelfResponse addBookToShelf(@RequestBody AddBookToShelfReq addBookToShelfReq){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.addBookToShelf(addBookToShelfReq);
			 
		} catch (UserShelfServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	
	/**
	 * Remove book from shelf
	 * @param bookId
	 */
	@ResponseBody
	@RequestMapping(value="/removebook", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public UserShelfResponse removeBookFromShelf(@RequestBody RemoveBookFromShelfReq request){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.removeBookFromShelf(request);
			 
		} catch (UserShelfServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}

	/**
	 * Delete a shelf
	 * @param bookId
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public UserShelfResponse deleteShelf(@RequestBody DeleteShelfReq request){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.deleteShelf(request);
			 
		} catch (UserShelfServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	
	/**
	 * Delete a shelf
	 * @param bookId
	 */
	@ResponseBody
	@RequestMapping(value = "/rename", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public UserShelfResponse renameShelf(@RequestBody RenameShelfReq request){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.renameShelf(request);
			 
		} catch (UserShelfServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	
}
