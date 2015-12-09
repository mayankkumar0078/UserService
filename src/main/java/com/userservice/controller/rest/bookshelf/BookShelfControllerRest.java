package com.userservice.controller.rest.bookshelf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.entity.BookShelf;
import com.userservice.service.bookshelfservice.BookShelfService;

@RestController
@RequestMapping()
public class BookShelfControllerRest {
	
	@Autowired
	private BookShelfService bookShelfService;
	
	@RequestMapping(value = "/userModule/BookShelves/",method=RequestMethod.POST)
	public final String createBookShelf(@RequestParam("username")String username,@RequestParam("name")String name,@RequestParam("description") String description){
		return bookShelfService.createBookShelf(username, name,description);
	}
	
	@RequestMapping(value = "/userModule/BookShelves/" ,method=RequestMethod.GET,produces = "application/json")
	public final BookShelf findBookShelfUsingUsernameAndShelfName(@RequestParam("username") String username,@RequestParam("name") String shelfName){
		return bookShelfService.getBookShelf(username,shelfName);
	}
	
	@RequestMapping(value = "/userModule/BookShelves/{username}" ,method=RequestMethod.GET,produces = "application/json")
	public final List<BookShelf> findAllBookShelvesForUsername(@PathVariable("username") String username){
		return bookShelfService.getAllBookShelvesForUsername(username);
	}
}
