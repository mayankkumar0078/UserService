/*package com.userservice.mongo.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.mongo.document.UserShelfDocument;
import com.userservice.mongo.dto.request.AddBookToShelfReq;
import com.userservice.mongo.dto.request.AddNotesToShelfReq;
import com.userservice.mongo.dto.request.CreateNewShelfReq;
import com.userservice.mongo.dto.request.DeleteShelfReq;
import com.userservice.mongo.dto.request.RemoveBookFromShelfReq;
import com.userservice.mongo.dto.request.RemoveNotesFromShelfReq;
import com.userservice.mongo.dto.request.RenameShelfReq;
import com.userservice.mongo.exception.UserShelfServiceException;
import com.userservice.mongo.response.UserShelfResponse;
import com.userservice.mongo.response.transformer.UserShelfResponseTransformer;
import com.userservice.mongo.service.UserShelfService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "user/shelf", produces = {"application/json", "application/xml"})
public class UserShelfController {

	@Resource
	UserShelfService userShelfService;
	
	@Autowired
	private UserShelfResponseTransformer userShelfResponseTransformer;
	
	*//**
	 * Retrieve the shelves based on the user id provided
	 * @param userId
	 *//*
	@ResponseBody
	@RequestMapping( method =  RequestMethod.GET, produces = {"application/json", "application/xml"})
	public UserShelfResponse retrieveShelves(@RequestParam("userId") String userId){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.retrieveUserShelf(userId);
			 
		} catch (UserShelfServiceException e) {
			return (UserShelfResponse) userShelfResponseTransformer.buildExceptionResponse(e, response);
		}
		return (UserShelfResponse)userShelfResponseTransformer.transformIntoSuccessResponse(response);
	}
	
	*//**
	 * Creates a new shelf for a user
	 * @param CreateNewShelfReq
	 *//*
	@ResponseBody
	@RequestMapping( method = RequestMethod.POST, produces = {"application/json", "application/xml"})
	public UserShelfResponse createNewShelf(@RequestBody CreateNewShelfReq req){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.createNewShelf(req);
			 
		} catch (UserShelfServiceException e) {
			return (UserShelfResponse) userShelfResponseTransformer.buildExceptionResponse(e, response);
		}
		return (UserShelfResponse)userShelfResponseTransformer.transformIntoSuccessResponse(response);
	}

	*//**
	 * Updates the shelf
	 * @param UserShelfDocument
	 *//*
	@ResponseBody
	@RequestMapping( method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public UserShelfResponse updateShelf(@RequestBody UserShelfDocument userShelfDocument){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.updateShelf(userShelfDocument);
			 
		} catch (UserShelfServiceException e) {
			return (UserShelfResponse) userShelfResponseTransformer.buildExceptionResponse(e, response);
		}
		return (UserShelfResponse)userShelfResponseTransformer.transformIntoSuccessResponse(response);
	}
	
	*//**
	 * Deletes a shelf
	 * @param DeleteShelfReq
	 *//*
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public UserShelfResponse deleteShelf(@RequestBody DeleteShelfReq request){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.deleteShelf(request);
			 
		} catch (UserShelfServiceException e) {
			return (UserShelfResponse) userShelfResponseTransformer.buildExceptionResponse(e, response);
		}
		return (UserShelfResponse)userShelfResponseTransformer.transformIntoSuccessResponse(response);
	}
	
	*//**
	 * Renames a shelf
	 * @param RenameShelfReq
	 *//*
	@ResponseBody
	@RequestMapping(value = "/rename", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public UserShelfResponse renameShelf(@RequestBody RenameShelfReq request){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.renameShelf(request);
			 
		} catch (UserShelfServiceException e) {
			return (UserShelfResponse) userShelfResponseTransformer.buildExceptionResponse(e, response);
		}
		return (UserShelfResponse)userShelfResponseTransformer.transformIntoSuccessResponse(response);
	}
	
	*//**
	 * Add book to shelf
	 * @param AddBookToShelfReq
	 *//*
	@ResponseBody
	@RequestMapping(value="/add/book", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public UserShelfResponse addBookToShelf(@RequestBody AddBookToShelfReq addBookToShelfReq){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.addBookToShelf(addBookToShelfReq);
			 
		} catch (UserShelfServiceException e) {
			return (UserShelfResponse) userShelfResponseTransformer.buildExceptionResponse(e, response);
		}
		return (UserShelfResponse)userShelfResponseTransformer.transformIntoSuccessResponse(response);
	}
	
	*//**
	 * Add notes to shelf
	 * @param AddNotesToShelfReq
	 *//*
	@ResponseBody
	@RequestMapping(value="/add/notes", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public UserShelfResponse addNotesToShelf(@RequestBody AddNotesToShelfReq addNotesToShelfReq){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.addNotesToShelf(addNotesToShelfReq);
			 
		} catch (UserShelfServiceException e) {
			return (UserShelfResponse) userShelfResponseTransformer.buildExceptionResponse(e, response);
		}
		return (UserShelfResponse)userShelfResponseTransformer.transformIntoSuccessResponse(response);
	}
	
	*//**
	 * Remove book from shelf
	 * @param RemoveBookFromShelfReq
	 *//*
	@ResponseBody
	@RequestMapping(value="/remove/book", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public UserShelfResponse removeBookFromShelf(@RequestBody RemoveBookFromShelfReq request){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.removeBookFromShelf(request);
			 
		} catch (UserShelfServiceException e) {
			return (UserShelfResponse) userShelfResponseTransformer.buildExceptionResponse(e, response);
		}
		return (UserShelfResponse)userShelfResponseTransformer.transformIntoSuccessResponse(response);
	}

	*//**
	 * Remove book from shelf
	 * @param bookId
	 *//*
	@ResponseBody
	@RequestMapping(value="/remove/notes", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public UserShelfResponse removeNotesFromShelf(@RequestBody RemoveNotesFromShelfReq request){
		UserShelfResponse response = new UserShelfResponse();
		try {
			 response = userShelfService.removeNotesFromShelf(request);
			 
		} catch (UserShelfServiceException e) {
			return (UserShelfResponse) userShelfResponseTransformer.buildExceptionResponse(e, response);
		}
		return (UserShelfResponse)userShelfResponseTransformer.transformIntoSuccessResponse(response);
	}
}
*/