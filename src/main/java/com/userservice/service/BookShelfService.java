package com.userservice.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.userservice.constants.UserAccountConstants;
import com.userservice.dao.UserAccountRepository;
import com.userservice.dao.BookShelfRepository;
import com.userservice.dto.BookInBookShelfDTO;
import com.userservice.dto.BookShelfDTO;
import com.userservice.entity.BookInBookShelf;
import com.userservice.entity.BookShelf;
import com.userservice.entity.User;
import com.userservice.exception.BookShelfException;

@Service
public class BookShelfService {
	public static final Logger log = Logger.getLogger(BookShelfService.class);
	
	public static final String BOOK_SHELF_ALREADY_EXISTS="ShelfBook Shelf with same name already exists for this user.";
	public static final String BOOK_SHELF_OR_USER_NOT_FOUND="ShelfBook Shelf or user is not present";
	
	@Autowired
	private BookShelfRepository bookShelfRepository;

	@Autowired(required = true)
	private UserAccountRepository userAccountRepository;

	public BookShelf createBookShelf(BookShelfDTO bookShelfDTO) throws BookShelfException{
		User user = (User) userAccountRepository
				.loadUserByUsername(bookShelfDTO.getUsername());
		BookShelf bookShelf=bookShelfRepository.findByUsernameAndShelfName(user, bookShelfDTO.getBookShelfName());
		
		if(bookShelf!=null){
			throw new BookShelfException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(),BOOK_SHELF_ALREADY_EXISTS);
		}
		
		bookShelf = new BookShelf();
		bookShelf.setBookShelfName(bookShelfDTO.getBookShelfName());
		bookShelf.setActive(true);
		bookShelf.setUserId(user);
		Set<BookInBookShelfDTO> booksInBookShelfDTO = bookShelfDTO
				.getBooksInBookShelf();
		Set<BookInBookShelf> booksInBookShelf = new HashSet<BookInBookShelf>();
		if (booksInBookShelfDTO != null) {
			for (BookInBookShelfDTO bookInBookShelfDTO : booksInBookShelfDTO) {
				BookInBookShelf bookInBookShelf = new BookInBookShelf();
				bookInBookShelf.setBookId(bookInBookShelfDTO.getBookId());
				bookInBookShelf.setCreatedBy(user);
				bookInBookShelf.setCreatedDate(new Date());
				bookInBookShelf.setActive(true);
				//bookInBookShelf.setLastModifiedBy(user);
				bookInBookShelf.setLastModifiedDate(new Date());
				bookInBookShelf.setBookShelfId(bookShelf);
				booksInBookShelf.add(bookInBookShelf);
			}
		}
		bookShelf.setBooksInBookShelves(booksInBookShelf);
		bookShelfRepository.save(bookShelf);
		return bookShelf;
	}

	public BookShelf getBookShelf(String username, String shelfName) throws BookShelfException{
		try{
			User user = (User) userAccountRepository.loadUserByUsername(username);
			return bookShelfRepository.findByUsernameAndShelfName(user, shelfName);
		}catch(UsernameNotFoundException e){
			throw new BookShelfException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(),BOOK_SHELF_OR_USER_NOT_FOUND);
		}
		
	}

	public List<BookShelf> getAllBookShelvesForUsername(String username) throws BookShelfException {
		try{
			User user = (User) userAccountRepository.loadUserByUsername(username);
			return bookShelfRepository.getAllBookShelvesForUsername(user);
		}catch(UsernameNotFoundException e){
			throw new BookShelfException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
					HttpStatus.NOT_ACCEPTABLE.value(),BOOK_SHELF_OR_USER_NOT_FOUND);
		}
		
	}

}
