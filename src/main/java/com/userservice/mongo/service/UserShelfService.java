package com.userservice.mongo.service;

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

public interface UserShelfService {
	UserShelfResponse retrieveUserShelf(String userId) throws UserShelfServiceException;
	UserShelfResponse createNewShelf(CreateNewShelfReq req) throws UserShelfServiceException;
	UserShelfResponse updateShelf(UserShelfDocument userShelfDocument) throws UserShelfServiceException;
	UserShelfResponse addBookToShelf(AddBookToShelfReq addBookToShelfReq) throws UserShelfServiceException;
	UserShelfResponse removeBookFromShelf(RemoveBookFromShelfReq removeBookFromShelfReq) throws UserShelfServiceException;
	UserShelfResponse deleteShelf(DeleteShelfReq deleteShelfReq) throws UserShelfServiceException;
	UserShelfResponse renameShelf(RenameShelfReq renameShelfReq) throws UserShelfServiceException;
	UserShelfResponse addNotesToShelf(AddNotesToShelfReq addNotesToShelfReq) throws UserShelfServiceException;
	UserShelfResponse removeNotesFromShelf(RemoveNotesFromShelfReq request) throws UserShelfServiceException;
}
