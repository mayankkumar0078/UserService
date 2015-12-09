package com.userservice.service.bookshelfservice;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.dao.UserAccountRepository;
import com.userservice.dao.bookshelf.BookShelfRepository;
import com.userservice.entity.BookShelf;
import com.userservice.entity.User;

@Service
public class BookShelfService {
	Logger log=Logger.getLogger(BookShelfService.class);
	@Autowired
	private BookShelfRepository bookShelfRepository;

	@Autowired(required = true)
	private UserAccountRepository repository;

	public String createBookShelf(String username, String shelfName,
			String description) {
		BookShelf bookShelf = new BookShelf();
		bookShelf.setName(shelfName);
		bookShelf.setActive(true);
		bookShelf.setOwner((User) repository.loadUserByUsername(username));
		bookShelf.setDescription(description);
		bookShelfRepository.save(bookShelf);
		return "Success";
	}

	public BookShelf getBookShelf(String username, String shelfName) {
		User user = (User) repository.loadUserByUsername(username);
		return bookShelfRepository.findByUsernameAndShelfName(user, shelfName);
	}

	public List<BookShelf> getAllBookShelvesForUsername(String username) {
		
		User user = (User) repository.loadUserByUsername(username);
		return bookShelfRepository.getAllBookShelvesForUsername(user);
	}

}
