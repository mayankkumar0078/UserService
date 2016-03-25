package com.userservice.mongo.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.userservice.mongo.document.UserShelfDocument;
import com.userservice.mongo.domain.Shelf;
import com.userservice.mongo.domain.ShelfBook;
import com.userservice.mongo.dto.request.AddBookToShelfReq;
import com.userservice.mongo.dto.request.CreateNewShelfReq;
import com.userservice.mongo.dto.request.DeleteShelfReq;
import com.userservice.mongo.dto.request.RemoveBookFromShelfReq;
import com.userservice.mongo.dto.request.RenameShelfReq;
import com.userservice.mongo.exception.UserShelfServiceException;
import com.userservice.mongo.repository.UserShelfRepository;
import com.userservice.mongo.response.UserShelfResponse;
import com.userservice.mongo.service.UserShelfService;

@Service
public class UserShelfServiceImpl implements UserShelfService{

	@Autowired
	private UserShelfRepository userShelfRepository;

	@Override
	public UserShelfResponse retrieveUserShelf(String userId) throws UserShelfServiceException {

		UserShelfResponse response = new UserShelfResponse();
		try {
			UserShelfDocument userShelfDocument = userShelfRepository.findByUserId(userId);
			response.setUserShelfDocument(userShelfDocument);
		} catch (Exception e) {
			throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Can't retrieve user shelf");
		}
		return response;
	}

	@Override
	public UserShelfResponse createNewShelf(CreateNewShelfReq req) throws UserShelfServiceException {
		UserShelfResponse response = new UserShelfResponse();
		try {
			List<Shelf> shelves = null;
			//create the new shelf
			Shelf shelf = new Shelf();
			shelf.setShelfName(req.getShelfName());
			shelf.setDate(Calendar.getInstance());
			shelf.setBooks(null);

			UserShelfDocument userShelfDocumentDB = userShelfRepository.findByUserId(req.getUser().getUserId());
			//check if the doc is null
			if(userShelfDocumentDB == null){
				//create a  new doc and save it
				userShelfDocumentDB = new UserShelfDocument();
				userShelfDocumentDB.setUser(req.getUser());
				shelves = new ArrayList<>();
				shelves.add(shelf);
				userShelfDocumentDB.setShelves(shelves);

			}else{
				shelves = 	userShelfDocumentDB.getShelves();
				if(shelves.contains(shelf)){
					throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Shelf is already present");
				}else{
					shelves.add(shelf);
					userShelfDocumentDB.setShelves(shelves);
				}
			}

			//save the entry
			userShelfDocumentDB = userShelfRepository.save(userShelfDocumentDB);
			response.setUserShelfDocument(userShelfDocumentDB);
		} catch
		(Exception e) {
			throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Can't save user shelf");
		}
		return response;
	}

	@Override
	public UserShelfResponse updateShelf(UserShelfDocument userShelfDocument) throws UserShelfServiceException {
		UserShelfResponse response = new UserShelfResponse();
		try {
			//find out the document from db
			UserShelfDocument userShelfDocumentSaved = userShelfRepository.findOne(userShelfDocument.getId());
			userShelfDocumentSaved = userShelfRepository.save(userShelfDocument);
			response.setUserShelfDocument(userShelfDocumentSaved);
		} catch
		(Exception e) {
			throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Can't save user shelf");
		}
		return response;
	}

	@Override
	public UserShelfResponse addBookToShelf(AddBookToShelfReq addBookToShelfReq) throws UserShelfServiceException {
		//create the objects
		UserShelfResponse response = new UserShelfResponse();
		String oldShelfName = addBookToShelfReq.getOldShelfName();
		String newShelfName = addBookToShelfReq.getNewShelfName();
		Shelf newShelf = new Shelf();
		Shelf oldShelf = new Shelf();
		
		newShelf.setShelfName(newShelfName);
		oldShelf.setShelfName(oldShelfName);
		try {
			//find out the document from db
			UserShelfDocument userShelfDocumentSaved = userShelfRepository.findByUserId(addBookToShelfReq.getUserId());
			if(userShelfDocumentSaved == null){
				throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "No data present for this user id");
			}
			int oldShelfIndex = -1; //find the indices of both the shelves to use it at later point
			int newShelfIndex = -1;
				oldShelfIndex = userShelfDocumentSaved.getShelves().indexOf(oldShelf);
				newShelfIndex = userShelfDocumentSaved.getShelves().indexOf(newShelf);
			if(newShelfIndex == -1){
				throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "New User shelf is not present");
			}
			//check if there is no user shelf
			if(userShelfDocumentSaved != null && userShelfDocumentSaved.getShelves().size() == 0){
				throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "User doesn't have any shelf");
			}
			//Add the book in the new shelf
			if(!newShelfName.equals("")){
				//get the books from the shelf and on top of that add new book
				List<ShelfBook> books = userShelfDocumentSaved.getShelves().get(newShelfIndex).getBooks();
				if(books == null){
					books = new ArrayList<>();
				}
				if(books.indexOf(addBookToShelfReq.getBook()) != -1){
					throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Book is already there in the shelf");
				}
					//add the date for the book
					books.add(addBookToShelfReq.getBook());
					newShelf.setBooks(books);
					//set the new shelf in the book shelves
					userShelfDocumentSaved.getShelves().set(newShelfIndex, newShelf);
			}
			
			//Delete the book from the old shelf
			if(!oldShelfName.equals("") && !newShelfName.equals("")){
				if(oldShelfIndex == -1){
					throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Old shelf is not present");
				}
				List<ShelfBook> books = userShelfDocumentSaved.getShelves().get(oldShelfIndex).getBooks();
				if(books == null){
					throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "No books are present in the old shelf");
				}
			//find the index of the book from the old shelf
				int bookIndex = books.indexOf(addBookToShelfReq.getBook());
				if(bookIndex == -1){
					throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "This book is not present in the shelf");
				}
				//delete the book from the shelf
				books.remove(bookIndex);
				oldShelf.setBooks(books);
				//set the old shelf in the book shelves
				userShelfDocumentSaved.getShelves().set(oldShelfIndex, oldShelf);
			}
			//save the user shelf document
			userShelfDocumentSaved = userShelfRepository.save(userShelfDocumentSaved);
			response.setUserShelfDocument(userShelfDocumentSaved);
		} catch
		(Exception e) {
			throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Can't save user shelf");
		}
		return response;
	}

	@Override
	public UserShelfResponse removeBookFromShelf(RemoveBookFromShelfReq request)
			throws UserShelfServiceException {
		UserShelfResponse response = new UserShelfResponse();
		UserShelfDocument savedUserShelfDoc = null;
		//make the shelf object
		Shelf shelf = new Shelf();
		ShelfBook shelfBook = new ShelfBook();
		shelfBook.setBookId(request.getBookId());
		shelf.setShelfName(request.getShelfName());
		try {
			savedUserShelfDoc = userShelfRepository.findByUserId(request.getUserId());
			//find the index of shelf from the doc 
			int shelfIndex = savedUserShelfDoc.getShelves().indexOf(shelf);
			//get the book index from the books
			int bookIndex = savedUserShelfDoc.getShelves().get(shelfIndex).getBooks().indexOf(shelfBook);
			if(bookIndex == -1){
				throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Can't remove the book from shelf");
			}
			//remove the book from the book shelves
			savedUserShelfDoc.getShelves().get(shelfIndex).getBooks().remove(bookIndex);
			//save the document 
			savedUserShelfDoc = userShelfRepository.save(savedUserShelfDoc);
		} catch (Exception e) {
			throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"Can't remove the book from the shelf");
		}
		response.setUserShelfDocument(savedUserShelfDoc);
		return response;
	}

	@Override
	public UserShelfResponse deleteShelf(DeleteShelfReq request) throws UserShelfServiceException {
		UserShelfResponse response = new UserShelfResponse();
		UserShelfDocument savedUserShelfDoc = null;
		//shelf object
		Shelf shelf = new Shelf();
		shelf.setShelfName(request.getShelfName());
		try {
			savedUserShelfDoc = userShelfRepository.findByUserId(request.getUserId());
			//find the shelf index
			boolean deleted = savedUserShelfDoc.getShelves().remove(shelf);
			if(!deleted){
				throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(),
						"No shelf found. So can't delete");
			}
			savedUserShelfDoc = userShelfRepository.save(savedUserShelfDoc);
			response.setUserShelfDocument(savedUserShelfDoc);
		} catch (Exception e) {
			throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"Can't delete the shelf from the rack");
		}
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public UserShelfResponse renameShelf(RenameShelfReq request) throws UserShelfServiceException {
		UserShelfResponse response = new UserShelfResponse();
		UserShelfDocument savedUserShelfDoc = null;
		Shelf oldShelf = new Shelf();
		Shelf newShelf = new Shelf();
		oldShelf.setShelfName(request.getOldShelfName());
		newShelf.setShelfName(request.getNewShelfName());
		try {
			savedUserShelfDoc = userShelfRepository.findByUserId(request.getUserId());
			//get the old shelf index
			int oldShelfIndex = savedUserShelfDoc.getShelves().indexOf(oldShelf);
			if(oldShelfIndex == -1){
				throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(),
						"Old Shelf does not exist. So can't rename the shelf");
			}
			savedUserShelfDoc.getShelves().get(oldShelfIndex).setShelfName(request.getNewShelfName());
			savedUserShelfDoc = userShelfRepository.save(savedUserShelfDoc);
		} catch (Exception e) {
			throw new UserShelfServiceException(1, 1, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"Can't rename the shelf from the rack");
		}
		response.setUserShelfDocument(savedUserShelfDoc);
		return response;
	}

}